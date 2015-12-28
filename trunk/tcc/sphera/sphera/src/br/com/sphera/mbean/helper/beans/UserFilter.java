package br.com.sphera.mbean.helper.beans;

import java.util.List;

import br.com.sphera.entities.Cargo;
import br.com.sphera.entities.Departamento;
import br.com.sphera.entities.Projeto;

public class UserFilter {

	private Projeto projeto;
	private List<Departamento> departamentos;
	private List<Cargo> cargos;
	
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	public List<Departamento> getDepartamentos() {
		return departamentos;
	}
	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}
	public List<Cargo> getCargos() {
		return cargos;
	}
	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
	
}
