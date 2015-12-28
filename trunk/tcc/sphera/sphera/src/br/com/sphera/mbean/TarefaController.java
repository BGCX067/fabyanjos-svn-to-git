package br.com.sphera.mbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.ajax4jsf.component.html.HtmlAjaxOutputPanel;
import org.richfaces.component.Dropzone;
import org.richfaces.event.DropEvent;
import org.richfaces.event.DropListener;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import br.com.sphera.entities.Tarefa;
import br.com.sphera.entities.Usuario;
import br.com.sphera.fake.dao.TarefaDAO;
import br.com.sphera.global.mbean.AbstractCrudBean;
import br.com.sphera.mbean.helper.Arquivo;
import br.com.sphera.util.JSFUtil;

public class TarefaController extends AbstractCrudBean implements DropListener{

	private Tarefa tarefa;
	private List<Tarefa> tarefas;
	private TarefaDAO dao;
	private List<Arquivo> files;
	private Usuario toAssociate;
	private HtmlAjaxOutputPanel respPanel;
	
	public TarefaController() {
		tarefa = new Tarefa();
		files = new ArrayList<Arquivo>();
	}
	
	public void setDao(TarefaDAO dao) {
		this.dao = dao;
	}
	public void setToAssociate(Usuario toAssociate) {
		this.toAssociate = toAssociate;
	}
	public Usuario getToAssociate() {
		return toAssociate;
	}
	public void setRespPanel(HtmlAjaxOutputPanel respPanel) {
		this.respPanel = respPanel;
	}
	public HtmlAjaxOutputPanel getRespPanel() {
		return respPanel;
	}
	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	public Tarefa getTarefa() {
		return tarefa;
	}
	public List<Tarefa> getTarefas() {
		return tarefas;
	}
	
	@Override
	public String delete() {
		dao.delete(tarefa);
		JSFUtil.addInfoMessage("sucessDeletedTask", null);
		return null;
	}

	@Override
	public String list() {
		tarefas = dao.listAll();
		return "tarefas";
	}

	@Override
	public String save() {
		try{
		processValidations();
		}catch (ValidatorException e) {
			return null;
		}
		dao.save(tarefa);
		JSFUtil.addInfoMessage("sucessSavedTask", null);
		return "tarefas";
	}
	public String novaTarefa(){
		setSelectedTab("tabCreate");
		return "tarefas";
	}
	@Override
	public String view() {
		return "tarefa";
	}
	public void listener(UploadEvent event){
		UploadItem item = event.getUploadItem();
	    Arquivo file = new Arquivo();
	    file.setLength(item.getData().length);
	    file.setName(item.getFileName());
	    file.setData(item.getData());
	    files.add(file);
	}
	
	private void processValidations() {
		if(tarefa.getInicioPlanejado().after(tarefa.getFimPlanejado())){
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle rb = context.getApplication().getResourceBundle(context, "i18n");
			FacesMessage msg = JSFUtil.addErrorMessage("dateHigher", "tarefa:dtIni", rb.getString("lblIniPlanejado"),rb.getString("lblFimPlanejado"));
			throw new ValidatorException(msg);
		}if(tarefa.getInicioPlanejado().before(tarefa.getFase().getInicioPlanejado())){
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle rb = context.getApplication().getResourceBundle(context, "i18n");
			FacesMessage msg = JSFUtil.addErrorMessage("dateInicioLessThanFase", "tarefa:dtIni", rb.getString("lblIniPlanejado"));
			throw new ValidatorException(msg);
		}if(tarefa.getInicioPlanejado().before(tarefa.getFase().getInicioPlanejado())){
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle rb = context.getApplication().getResourceBundle(context, "i18n");
			FacesMessage msg = JSFUtil.addErrorMessage("dateFimHigherThanFase", "tarefa:dtFim",rb.getString("lblFimPlanejado"));
			throw new ValidatorException(msg);
		}if(tarefa.getCustoPlanejadoMaterial() > tarefa.getFase().getCustoPlanejadoMaterial()){
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle rb = context.getApplication().getResourceBundle(context, "i18n");
			FacesMessage msg = JSFUtil.addErrorMessage("custHigherThanFase", "tarefa:custoMat",rb.getString("lblCustoPlanejadoMaterial"));
			throw new ValidatorException(msg);
		}if(tarefa.getCustoPlanejadoRH() > tarefa.getFase().getCustoPlanejadoRH()){
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle rb = context.getApplication().getResourceBundle(context, "i18n");
			FacesMessage msg = JSFUtil.addErrorMessage("custHigherThanFase", "tarefa:custoRH",rb.getString("lblCustoPlanejadoRH"));
			throw new ValidatorException(msg);
		}
	}
	
	public void processDrop(DropEvent event) {
		Dropzone dropzone = (Dropzone) event.getComponent();
		Object dragValue = event.getDragValue();
		Object dropValue = event.getDropValue();
		if(dragValue instanceof Tarefa){
			setToAssociate((Usuario) dropValue);
			setTarefa((Tarefa) dragValue);
			System.out.println("setted");
			delegar();
		}
		
		System.out.println("Droped");		
	}
	public void delegar(){
		dao.assignTaskToUser(tarefa, toAssociate.getId());
	}
}
