package br.com.sphera.mbean;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import br.com.sphera.entities.Fase;
import br.com.sphera.fake.dao.FaseDao;
import br.com.sphera.global.mbean.AbstractCrudBean;
import br.com.sphera.mbean.helper.File;
import br.com.sphera.util.JSFUtil;

public class FaseController extends AbstractCrudBean{

	private Fase fase;
	private FaseDao dao;
	private List<Fase> fases;
	private List<File> files;

	public FaseController() {
		fase = new Fase();
	}
	
	public List<Fase> getFases() {
		fases = dao.listAll();
		return fases;
	}
	
	public void setFase(Fase fase) {
		this.fase = fase;
	}

	public Fase getFase() {
		return fase;
	}

	public void setDao(FaseDao dao) {
		this.dao = dao;
	}
	@Override
	public String view() {
		return "fase";
	}

	@Override
	public String delete() {
		dao.delete(fase);
		JSFUtil.addInfoMessage("sucessDeletdPhase", null);
		return null;
	}

	@Override
	public String list() {
		return "fases";
	}

	@Override
	public String save() {
		try{
			processValidations();
		}catch (ValidatorException e) {
			return null;
		}
		dao.save(fase);
		JSFUtil.addInfoMessage("sucessSavedPhase", null);
		return "fases";
	}

	private void processValidations() {
		if(fase.getInicioPlanejado().after(fase.getFimPlanejado())){
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle rb = context.getApplication().getResourceBundle(context, "i18n");
			FacesMessage msg = JSFUtil.addErrorMessage("dateHigher", "fase:dtIni", rb.getString("lblIniPlanejado"),rb.getString("lblFimPlanejado"));
			throw new ValidatorException(msg);
		}if(fase.getInicioPlanejado().before(fase.getProjeto().getInicioPlanejado())){
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle rb = context.getApplication().getResourceBundle(context, "i18n");
			FacesMessage msg = JSFUtil.addErrorMessage("dateInicioLessThanProj", "fase:dtIni", rb.getString("lblIniPlanejado"));
			throw new ValidatorException(msg);
		}if(fase.getInicioPlanejado().before(fase.getProjeto().getInicioPlanejado())){
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle rb = context.getApplication().getResourceBundle(context, "i18n");
			FacesMessage msg = JSFUtil.addErrorMessage("dateFimHigherThanProj", "fase:dtFim",rb.getString("lblFimPlanejado"));
			throw new ValidatorException(msg);
		}if(fase.getCustoPlanejadoMaterial() > fase.getProjeto().getCustoPlanejadoMaterial()){
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle rb = context.getApplication().getResourceBundle(context, "i18n");
			FacesMessage msg = JSFUtil.addErrorMessage("custHigherThanProj", "fase:custoMat",rb.getString("lblCustoPlanejadoMaterial"));
			throw new ValidatorException(msg);
		}if(fase.getCustoPlanejadoRH() > fase.getProjeto().getCustoPlanejadoRH()){
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle rb = context.getApplication().getResourceBundle(context, "i18n");
			FacesMessage msg = JSFUtil.addErrorMessage("custHigherThanProj", "fase:custoRH",rb.getString("lblCustoPlanejadoRH"));
			throw new ValidatorException(msg);
		}
	}
	
	public void listener(UploadEvent event){
		UploadItem item = event.getUploadItem();
	    File file = new File();
	    file.setLength(item.getData().length);
	    file.setName(item.getFileName());
	    file.setData(item.getData());
	    files.add(file);
	}
	public List<Fase> autocomplete(Object suggest){
		return dao.findFaseNameStartWith(String.valueOf(suggest));
	}
	public String novaFase(){
		setSelectedTab("tabCreate");
		return "fases";
	}
	
}
