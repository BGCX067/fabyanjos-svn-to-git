package br.com.sphera.mbean.helper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.richfaces.model.TreeNode;

import br.com.sphera.entities.Fase;
import br.com.sphera.mbean.helper.beans.FaseNode;

public class ArvoreFase implements TreeNode{

	private Fase fase;
	private Map<Object,FaseNode> fases;
	
	public ArvoreFase() {
		fases = new HashMap<Object, FaseNode>();
	}
	
	public void setFase(Fase fase) {
		this.fase = fase;
		FaseNode rootNode = new FaseNode();
		rootNode.setFase(fase);
		addChild(fase.getId(), rootNode);
	}
	
	@Override
	public void addChild(Object identifier, TreeNode child) {
		child.setParent(this);
		fases.put(identifier, (FaseNode) child);
	}
	
	@Override
	public TreeNode getChild(Object identifier) {
		return fases.get(identifier);
	}
	@Override
	public Iterator<Entry<Object, FaseNode>> getChildren() {
		return fases.entrySet().iterator();
	}
	@Override
	public Object getData() {
		return this;
	}
	@Override
	public TreeNode getParent() {
		return null;
	}
	@Override
	public boolean isLeaf() {
		return false;
	}
	@Override
	public void removeChild(Object identifier) {
		fases.remove(identifier);
	}
	@Override
	public void setData(Object arg0) {
		
	}
	@Override
	public void setParent(TreeNode arg0) {
		
	}

	public br.com.sphera.entities.Fase getFase() {
		return fase;
	}
	
	
	
}
