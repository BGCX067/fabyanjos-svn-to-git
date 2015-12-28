package br.com.sphera.mbean;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import br.com.sphera.entities.Projeto;
import br.com.sphera.fake.dao.ProjetoDAO;
import br.com.sphera.global.mbean.AbstractCrudBean;
import br.com.sphera.mbean.helper.File;
import br.com.sphera.util.JSFUtil;

public class ProjetoController extends AbstractCrudBean{

	private Projeto projeto;
	private ProjetoDAO dao;
	private List<Projeto> projetos;
	private List<File> files;
	
	public ProjetoController() {
		projeto = new Projeto();
		files = new ArrayList<File>();
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
		projetos = dao.listAll();
		return projetos;
	}
		
	public String delete() {
		JSFUtil.addInfoMessage("sucessDeletdProj", null);
		dao.delete(projeto);
		return null;
	}

	public String list() {
		projeto = new Projeto();
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
		files.clear();
		return "projetos";
	}

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
		return "project";
	}
	
	public void listener(UploadEvent event){
		UploadItem item = event.getUploadItem();
	    File file = new File();
	    file.setLength(item.getData().length);
	    file.setName(item.getFileName());
	    file.setData(item.getData());
	    files.add(file);
	}

	public List<Projeto> autocomplete(Object suggest){
		return dao.findProjetoNameStartWith(String.valueOf(suggest));
	}
	
	public List<File> getFiles() {
		return files;
	}
	public void setFiles(List<File> files) {
		this.files = files;
	}
	
}
