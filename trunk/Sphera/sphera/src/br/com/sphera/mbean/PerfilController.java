package br.com.sphera.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import br.com.sphera.entities.Perfil;
import br.com.sphera.fake.dao.PerfilDao;
import br.com.sphera.util.JSFUtil;

public class PerfilController {

	private Perfil perfil;
	private PerfilDao dao;
	private String selectedTab = "tabList";
	private List<Perfil> perfis;

	public PerfilController() {
		perfil = new Perfil();
	}

	public void setProjeto(Perfil perfil) {
		this.perfil = perfil;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setDao(PerfilDao dao) {
		this.dao = dao;
	}

	public String save() {
		dao.save(perfil);
		JSFUtil.addInfoMessage("sucessSavedPerfil", null);
		return "perfil";
	}

	public List<SelectItem> getComboList(){
		List<Perfil> perfis = dao.listAll();
		List<SelectItem> itens = new ArrayList<SelectItem>();
		for(Perfil p : perfis){
			itens.add(new SelectItem(p,p.getNome()));
		}
		return itens;
	}
	
	/**
	 * lista todos os perfis contidos no banco de dados
	 */
	public String list(){
		return "perfil";
	}
	
	public String getSelectedTab() {
		return selectedTab;
	}
	public void setSelectedTab(String selectedTab) {
		this.selectedTab = selectedTab;
	}
	public String delete(){
		System.out.println("delete perfil: "+perfil.getNome());
		dao.delete(perfil);
		JSFUtil.addInfoMessage("sucessDeletedPerfil", null);
		return null;
	}
	public void edit(ActionEvent event){
		System.out.println("edit perfil: "+perfil.getNome());
		selectedTab = "tabCreate";
	}

	public List<Perfil> getPerfis() {
		perfis = dao.listAll();
		return perfis;
	}
}
