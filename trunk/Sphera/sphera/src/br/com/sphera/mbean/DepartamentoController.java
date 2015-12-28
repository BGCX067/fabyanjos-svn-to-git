package br.com.sphera.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.sphera.entities.Departamento;
import br.com.sphera.fake.dao.DepartamentoDAO;
import br.com.sphera.global.mbean.AbstractCrudBean;
import br.com.sphera.util.JSFUtil;

public class DepartamentoController extends AbstractCrudBean{

	private DepartamentoDAO dao;
	private Departamento departamento;
	private List<Departamento> departamentos;
	
	public DepartamentoController() {
		departamento = new Departamento();
	}
	
	/**
	 * Propriedade a ser injetada nas configurações do faces-config
	 * @param dao
	 */
	public void setDao(DepartamentoDAO dao) {
		this.dao = dao;
	}
	
	public List<Departamento> getDepartamentos() {
		departamentos = dao.listAll();
		return departamentos;
	}
	
	public List<SelectItem> getComboList(){
		List<Departamento> list = getDepartamentos();
		List<SelectItem> itens = new ArrayList<SelectItem>();
		itens.add(new SelectItem(null,"Selecione..."));
		for (Departamento dept : list) {
			itens.add(new SelectItem(dept,dept.getNome()));
		}
		return itens;
	}
	
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	public Departamento getDepartamento() {
		return departamento;
	}
	/**
	 * Salva um departamento no banco de dados
	 */
	public String save(){
		dao.save(departamento);
		JSFUtil.addInfoMessage("sucessSavedDept", null);
		return "departamentos";
	}
		
	/**
	 * lista todos os departamentos contidos no banco de dados
	 */
	public String list(){
		return "departamentos";
	}
	/**
	 * Remove os departamentos 
	 */
	public String delete(){
		System.out.println("Excluido "+departamento.getNome());
		dao.delete(departamento);
		JSFUtil.addInfoMessage("sucessDeleteDept", null);
		return null;
	}
	
}
