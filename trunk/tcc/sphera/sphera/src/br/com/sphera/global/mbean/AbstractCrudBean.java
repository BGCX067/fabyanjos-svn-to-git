package br.com.sphera.global.mbean;

import javax.faces.event.ActionEvent;

public abstract class AbstractCrudBean {

	//tab selecionada, tabList ou tabCreate
	private String selectedTab = "tabList";

	protected final String LIST_TAB = "tabList";
	protected final String CREATE_TAB = "tabCreate";
	
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
		setSelectedTab(CREATE_TAB);
	}
	
	/**
	 * Visuliza os dados que est�o na tabela num modalPanel
	 */
	public String view(){	//Caso seja necess�rio, sobrescreva o m�todo
		return null;
	}
	
	/**
	 *Exclui uma entidade 
	 */
	public abstract String delete(); //m�todo deve ser sobrescrito
	
	/**
	 * salva uma entidade
	 * @return
	 */
	public abstract String save(); //m�todo deve ser sobrescrito
	
	/**
	 * lista todas as entidades presentes
	 * @return
	 */
	public abstract String list(); //m�todo deve ser sobrescrito
	
}
