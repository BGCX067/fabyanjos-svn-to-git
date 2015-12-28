package br.com.sphera.global.mbean;

import javax.faces.event.ActionEvent;

public abstract class AbstractCrudBean {

	//tab selecionada, tabList ou tabCreate
	private String selectedTab = "tabList";

	public String getSelectedTab() {
		return selectedTab;
	}

	public void setSelectedTab(String selectedTab) {
		this.selectedTab = selectedTab;
	}
	
	/**
	 * Edita uma entidade recuperado na view
	 * @return
	 */
	public void edit(ActionEvent event){
		setSelectedTab("tabCreate");
	}
	
	/**
	 * Visuliza os dados que estão na tabela num modalPanel
	 */
	public String view(){	//Caso seja necessário, sobrescreva o método
		return null;
	}
	
	/**
	 *Exclui uma entidade 
	 */
	public abstract String delete(); //método deve ser sobrescrito
	
	/**
	 * salva uma entidade
	 * @return
	 */
	public abstract String save(); //método deve ser sobrescrito
	
	/**
	 * lista todas as entidades presentes
	 * @return
	 */
	public abstract String list(); //método deve ser sobrescrito
	
}
