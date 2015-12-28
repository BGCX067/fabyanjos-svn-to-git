package com.alfa.tracking.spring.helper;

import java.io.Serializable;

import com.alfa.tracking.model.Objeto;

public class ObjectItem implements Serializable,LeafNode{

	private static final long serialVersionUID = 5350896232435662458L;

	private Objeto objeto;
	
	public ObjectItem(Objeto objeto) {
		this.objeto = objeto;
	}
	
	public boolean isLeaf() {
		return false;
	}
	
	public String getId(){
		return this.objeto.getId().toString();
	}
	
	public String getText(){
		if(objeto == null){
			return "";
		}
		return objeto.getDescription();
	}
}
