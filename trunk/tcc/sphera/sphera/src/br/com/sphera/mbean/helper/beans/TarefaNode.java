package br.com.sphera.mbean.helper.beans;

import java.util.Iterator;

import org.richfaces.model.TreeNode;

import br.com.sphera.entities.Tarefa;

public class TarefaNode implements TreeNode{

	private Tarefa tarefa;
	private FaseNode parent;
	public TarefaNode() {
	}
	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
	@Override
	public void addChild(Object arg0, TreeNode arg1) {
		throw new UnsupportedOperationException("Tarefas não possuem filhos");
	}

	@Override
	public TreeNode getChild(Object arg0) {
		throw new UnsupportedOperationException("Tarefas não possuem filhos");
	}

	@Override
	public Iterator getChildren() {
		return null;
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
		return true;
	}

	@Override
	public void removeChild(Object arg0) {
		throw new UnsupportedOperationException("Tarefas não possuem filhos");
	}

	@Override
	public void setData(Object value) {
		tarefa = (Tarefa) value;
	}

	@Override
	public void setParent(TreeNode node) {
		this.parent = (FaseNode) node;
	}
	public String getType() {
		return "tarefa";
	}
	public Tarefa getTarefa() {
		return tarefa;
	}
}
