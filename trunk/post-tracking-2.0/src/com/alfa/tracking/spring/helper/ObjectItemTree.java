package com.alfa.tracking.spring.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alfa.tracking.model.Objeto;

public class ObjectItemTree implements Serializable,Tree{

	
	private static final long serialVersionUID = -6083066738826365142L;
	
	private List<LeafNode> children;
	
	public ObjectItemTree(Objeto objeto) {
		this.children = new ArrayList<LeafNode>();
		this.children.add(new ObjectItemDetails("Id", objeto.getId().toString(),"id-"+objeto.getNumero()));
		this.children.add(new ObjectItemDetails("Numero", objeto.getNumero(),"numero-"+objeto.getNumero()));
		this.children.add(new ObjectItemDetails("Descricao", objeto.getDescription(),"desc-"+objeto.getNumero()));
		this.children.add(new ObjectItemDetails("Data de Cadastro", objeto.getCreateDate(),"dtCad-"+objeto.getNumero()));
		this.children.add(new ObjectItemDetails("Data de Status", objeto.getLastStatusDate(),"dtSt-"+objeto.getNumero()));
		this.children.add(new ObjectItemDetails("Status", objeto.isOpen(),"sta-"+objeto.getNumero()));
	}
	
	public List<LeafNode> getChildren() {
		return children;
	}
	
}
