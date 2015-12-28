package br.com.sphera.mbean;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.sphera.entities.Tarefa;
import br.com.sphera.fake.dao.TarefaDAO;
import br.com.sphera.global.mbean.AbstractCrudBean;
import br.com.sphera.util.JSFUtil;

public class TarefaController extends AbstractCrudBean{

	private Tarefa tarefa;
	private List<Tarefa> tarefas;
	private TarefaDAO dao;
	
	public TarefaController() {
		tarefa = new Tarefa();
	}
	
	public void setDao(TarefaDAO dao) {
		this.dao = dao;
	}
	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	public Tarefa getTarefa() {
		return tarefa;
	}
	public List<Tarefa> getTarefas() {
		tarefas = dao.listAll();
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
}
