package br.com.sphera.mbean.helper.beans;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.richfaces.model.TreeNode;

import br.com.sphera.entities.Fase;
import br.com.sphera.entities.Tarefa;

public class FaseNode implements TreeNode{

	private Fase fase;
	private TreeNode parent;
	private Map<Object, TarefaNode> tarefas;
	
	public FaseNode() {
		tarefas = new HashMap<Object, TarefaNode>();
	}
	public void setFase(Fase fase) {
		this.fase = fase;
		if(fase.getTarefas()==null)
			return;
		for(Tarefa tarefa : fase.getTarefas()){
			TarefaNode node = new TarefaNode();
			node.setTarefa(tarefa);
			addChild(tarefa.getId(), node);
		}
	}
	
	@Override
	public void addChild(Object identifier, TreeNode child) {
		child.setParent(this);
		tarefas.put(identifier, (TarefaNode) child);
	}

	@Override
	public TreeNode getChild(Object identifier) {
		return tarefas.get(identifier);
	}

	@Override
	public Iterator getChildren() {
		return tarefas.entrySet().iterator();
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
		return fase.getTarefas().isEmpty();
	}

	@Override
	public void removeChild(Object identifier) {
		tarefas.remove(identifier);
	}

	@Override
	public void setData(Object value) {
		
	}

	@Override
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public String getType() {
		return "fase";
	}
	public Fase getFase() {
		return fase;
	}
}
