package br.com.sphera.mbean.helper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.richfaces.model.TreeNode;

import br.com.sphera.entities.Projeto;
import br.com.sphera.mbean.helper.beans.ProjetoNode;

public class ArvoreProjeto implements TreeNode{

	private Map<Object, ProjetoNode> children;
	private Projeto projeto;	 
	
	
	public ArvoreProjeto() {
		children = new HashMap<Object, ProjetoNode>();
	}
	
	@Override
	public void addChild(Object identifier, TreeNode child) {
		child.setParent(this);
		children.put(identifier, (ProjetoNode) child);
	}

	@Override
	public TreeNode<ProjetoNode> getChild(Object identifier) {
		return children.get(identifier);
	}

	@Override
	public Iterator<Entry<Object, ProjetoNode>> getChildren() {
		return children.entrySet().iterator();
	}

	@Override
	public Object getData() {
		return this;
	}

	@Override
	public TreeNode<ProjetoNode> getParent() {
		return null;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public void removeChild(Object identifier) {
		children.remove(identifier);
	}

	@Override
	public void setData(Object arg0) {
		
	}

	@Override
	public void setParent(TreeNode arg0) {
		
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
		ProjetoNode rootNode = new ProjetoNode();
		rootNode.setProjeto(this.projeto);
		addChild(projeto.getId(), rootNode);
	}
}
