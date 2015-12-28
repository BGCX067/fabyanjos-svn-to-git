package br.com.sphera.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.sphera.entities.Cargo;
import br.com.sphera.fake.dao.CargoDAO;
import br.com.sphera.global.mbean.AbstractCrudBean;
import br.com.sphera.util.JSFUtil;

public class CargoController extends AbstractCrudBean{

	private Cargo cargo;
	private List<Cargo> cargos;
	private CargoDAO dao;
	//tab selecionada, tabList ou tabCreate
	private String selectedTab = "tabList";
	
	public CargoController() {
		cargo = new Cargo();
	}
	public void setDao(CargoDAO dao) {
		this.dao = dao;
	}
	public String getSelectedTab() {
		return selectedTab;
	}
	public void setSelectedTab(String selectedTab) {
		this.selectedTab = selectedTab;
	}
	public List<Cargo> getCargos() {
		return cargos;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public List<SelectItem> getComboList(){
		cargos = dao.listAll();
		List<SelectItem> itens = new ArrayList<SelectItem>();
		for (Cargo cargo : cargos) {
			/*	
			 * primeiro argumento do select item deve ser o objeto inteiro
			 *	a convesão do valor que será agregado nos optios, ou 
			 *	SelectItens será feita no conversor
			*/ 
			itens.add(new SelectItem(cargo,cargo.getNome()));
		}
		return itens;
	}
	
	/**
	 * lista os cargos presentes
	 */
	public String list(){
		goToList();
		return "cargos";
	}
	/**salva o cargo no banco de Dados
	 * @return
	 */
	public String save(){
		dao.save(cargo);
		goToList();
		JSFUtil.addInfoMessage("sucessSavedCargo", null);
		return null;
	}
	
	/**
	 *Exclui um cargo recebido da view 
	 */
	public String delete(){
		JSFUtil.addInfoMessage("sucessDeletedCargo", null);
		//deletar do dao
		dao.delete(cargo);
		//para evitar buscar do banco de dados de novo, remove da lista
		goToList();
		return null;
	}
	public void goToList(){
		setSelectedTab(super.LIST_TAB);
		cargos = dao.listAll();
	}
	public void cleanFields(){
		cargo = new Cargo();
	}
	
}
