package br.com.sphera.mbean;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import br.com.sphera.entities.Fase;
import br.com.sphera.entities.Projeto;
import br.com.sphera.entities.Tarefa;
import br.com.sphera.entities.Usuario;
import br.com.sphera.entities.enums.ProjetoStatus;
import br.com.sphera.fake.dao.ProjetoDAO;
import br.com.sphera.fake.dao.UsuarioDAO;
import br.com.sphera.global.mbean.AbstractCrudBean;
import br.com.sphera.mbean.helper.Arquivo;
import br.com.sphera.mbean.helper.beans.UserFilter;
import br.com.sphera.util.JSFUtil;

public class ProjetoController extends AbstractCrudBean{

	private Projeto projeto;
	private ProjetoDAO dao;
	private List<Projeto> projetos;
	private List<Arquivo> files;
	private List<Usuario> related;
	private UsuarioDAO daoUsuario;
	private UserFilter filter;
	
	public ProjetoController() {
		projeto = new Projeto();
		files = new ArrayList<Arquivo>();
	}
	
	public void setDaoUsuario(UsuarioDAO daoUsuario) {
		this.daoUsuario = daoUsuario;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public void setDao(ProjetoDAO dao) {
		this.dao = dao;
	}
	public List<Projeto> getProjetos() {
		return projetos;
	}
		
	public String delete() {
		JSFUtil.addInfoMessage("sucessDeletdProj", null);
		dao.delete(projeto);
		goToList();
		return null;
	}

	public String list() {
		goToList();
		return "projetos";
	}

	public String save() {
		try{
			processValidations();
		}catch (ValidatorException e) {
			return null;
		}
		dao.save(projeto);
		JSFUtil.addInfoMessage("sucessSavedProj", null);
		//limpa os arquivos
		files.clear();
		goToList();
		return null;
	}

	

	/**
	 * valida os dados recebidos
	 * 
	 */
	private void processValidations() {
		if(projeto.getInicioPlanejado().after(projeto.getFimPlanejado())){
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle rb = context.getApplication().getResourceBundle(context, "i18n");
			FacesMessage msg = JSFUtil.addErrorMessage("dateHigher", "proj:dtIni", rb.getString("lblIniPlanejado"),rb.getString("lblFimPlanejado"));
			throw new ValidatorException(msg);
		}
	}
	public void limpar(){
		projeto = new Projeto();
	}
	@Override
	public String view() {
		filtrarUsuarios(); 
		return "project";
	}

	public void filtrarUsuarios() {
		filter = new UserFilter();
		filter.setProjeto(this.projeto);
		related = daoUsuario.findByFilter(filter);
	}
	
	/**
	 * Método que faz o upload dos arquivos 
	 * @param event
	 */
	public void listener(UploadEvent event){
		UploadItem item = event.getUploadItem();
	    Arquivo file = new Arquivo();
	    file.setLength(item.getData().length);
	    file.setName(item.getFileName());
	    file.setData(item.getData());
	    files.add(file);
	}

	/**
	 * Método que busca um projeto de acordo com o dado do suggest recebido
	 * @param suggest
	 * @return
	 */
	public List<Projeto> autocomplete(Object suggest){
		return dao.findProjetoNameStartWith(String.valueOf(suggest));
	}
	
	public List<Arquivo> getFiles() {
		return files;
	}
	public void setFiles(List<Arquivo> files) {
		this.files = files;
	}

	/**
	 * Gera o Gant chart do projeto
	 * @return
	 */
	public IntervalCategoryDataset getGanttChart(){
		TaskSeriesCollection project = new TaskSeriesCollection();
		if(projeto.getFases()!=null){
			for(Fase fase: projeto.getFases()){
				TaskSeries phase = new TaskSeries(fase.getNome());
				if(phase.getTasks()!=null){
					for(Tarefa tarefa : fase.getTarefas()){
						phase.add(new Task(tarefa.getNome(),tarefa.getInicioPlanejado(),tarefa.getFimPlanejado()));
					}
					project.add(phase);
				}
			}
		}
		return project;
	}
	public String editar(){
		setSelectedTab("tabCreate");
		return "projetos";
	}

	public List<Usuario> getRelated() {
		return related;
	}

	public void setRelated(List<Usuario> related) {
		this.related = related;
	}

	public UserFilter getFilter() {
		return filter;
	}

	public void setFilter(UserFilter filter) {
		this.filter = filter;
	}
	/**
	 * Autoria um projeto
	 * 
	 */
	public void autorizar(){
		this.projeto.setStatus(ProjetoStatus.INPROGRESS);
		this.dao.save(projeto);
	}
	/**
	 * limpa os dados de projeto
	 */
	public void cleanFields(){
		projeto = new Projeto();
	}
	/**
	 *Busca os dados para a listagem 
	 */
	public void goToList(){
		setSelectedTab(super.LIST_TAB);
		projetos = dao.listAll();
	}
	public boolean isShowButtonAutorizar(){
		if(projeto.getStatus().equals(ProjetoStatus.AGUARDANDO)){
			return true;
		}
		return false;
	}
	public Integer getProgressValue(){
		return 0;
	}
	public boolean isProgressBarShow(){
		return !projeto.getStatus().equals(ProjetoStatus.AGUARDANDO);
	}
}
