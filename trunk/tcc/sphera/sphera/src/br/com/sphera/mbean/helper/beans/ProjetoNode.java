package br.com.sphera.mbean.helper.beans;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.richfaces.model.TreeNode;

import br.com.sphera.entities.Fase;
import br.com.sphera.entities.Projeto;
import br.com.sphera.mbean.helper.ArvoreProjeto;

public class ProjetoNode implements TreeNode{

	private Projeto projeto;
	private Map<Object,FaseNode> fases;
	private ArvoreProjeto parent;
	
	public ProjetoNode() {
		fases = new HashMap<Object, FaseNode>();
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
		if(projeto.getFases()==null)
			return;
		for(Fase fase : projeto.getFases()){
			FaseNode faseNode = new FaseNode();
			faseNode.setFase(fase);
			addChild(fase.getId(), faseNode);
		}
	}
	
	@Override
	public void addChild(Object identifier, TreeNode child) {
		child.setParent(this);
		fases.put(identifier, (FaseNode) child);
	}

	@Override
	public TreeNode getChild(Object Identifier) {
		return fases.get(Identifier);
	}

	@Override
	public Iterator getChildren() {
		return fases.entrySet().iterator();
	}

	@Override
	public Object getData() {
		return this;
	}

	@Override
	public TreeNode getParent() {
		return parent;
	}

	@Override
	public boolean isLeaf() {
		return fases.isEmpty();
	}

	@Override
	public void removeChild(Object identifier) {
		fases.remove(identifier);
	}

	@Override
	public void setData(Object value) {
		
	}

	@Override
	public void setParent(TreeNode parent) {
		this.parent = (ArvoreProjeto) parent; 
	}

	public String getType() {
		return "projeto";
	}
	public Projeto getProjeto() {
		return projeto;
	}
}
