package com.alfa.tracking.spring.helper;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ObjectItemDetails implements Serializable, LeafNode{

	private static final long serialVersionUID = 3250971712873012577L;
	
	private String text;
	
	private String id;
	
	
	public ObjectItemDetails(String label, String value,String id) {
		this.id = id;
		this.text = label + " - "+ value;
	}
	
	public ObjectItemDetails(String label, Date value,String id) {
		this.id = id;
		if(value!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			this.text = label + " - "+ sdf.format(value);
		}else{
			this.text = label + " - "+ "";
		}
	}
	
	public ObjectItemDetails(String label, boolean value,String id) {
		this.id = id;
		if(value){
			this.text = label + " - Ativo";
		}else{
			this.text = label + " - Inativo";
		}
	}

	public boolean isLeaf() {
		return true;
	}
	
	public String getText() {
		return text;
	}
	
	public String getId(){
		return id;
	}
	
}
