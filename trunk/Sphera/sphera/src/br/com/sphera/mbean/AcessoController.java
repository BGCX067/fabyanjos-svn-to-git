package br.com.sphera.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.sphera.entities.Acesso;
import br.com.sphera.fake.dao.AcessoDao;

public class AcessoController {

	private Acesso acesso;
	private AcessoDao dao;

	public AcessoController() {
		acesso = new Acesso();
	}

	public void setProjeto(Acesso acesso) {
		this.acesso = acesso;
	}

	public Acesso getAcesso() {
		return acesso;
	}

	public void setDao(AcessoDao dao) {
		this.dao = dao;
	}

	public String save() {

		return "acesso";
	}

	public List<SelectItem> getComboList(){
		List<Acesso> acessos = dao.listAll();
		List<SelectItem> itens = new ArrayList<SelectItem>();
		for(Acesso p : acessos){
			itens.add(new SelectItem(p, p.getNome()));
		}
		return itens;
	}
}
