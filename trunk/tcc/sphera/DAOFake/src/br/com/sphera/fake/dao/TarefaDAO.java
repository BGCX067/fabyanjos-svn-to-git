package br.com.sphera.fake.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.sphera.entities.Tarefa;
import br.com.sphera.entities.Usuario;

public class TarefaDAO {

	private HashMap<Long, Tarefa> tarefas;
	private HashMap<Long, Tarefa> lixo;
	private Long id = 1L;
	private UsuarioDAO daoUsuario;
	
	
	public TarefaDAO() {
		tarefas = new HashMap<Long, Tarefa>();
		lixo = new HashMap<Long, Tarefa>();
	}
	
	public void setDaoUsuario(UsuarioDAO daoUsuario) {
		this.daoUsuario = daoUsuario;
	}
	
	public Long save(Tarefa tarefa) {
		if(tarefa.getId() == null || tarefa.getId() == 0){
			tarefa.setId(id++);
		}
		tarefas.put(tarefa.getId(), tarefa);
		return tarefa.getId();
	}
	public Tarefa load(Long key) {
		return tarefas.get(key);
	}
	public List<Tarefa> listAll(){
		List<Tarefa> list = new ArrayList<Tarefa>(tarefas.values());
		return list;
	}
	public boolean delete(Tarefa projeto){
		lixo.put(projeto.getId(),tarefas.remove(projeto.getId()));
		return true;
	}
	public Tarefa assignTaskToUser(Tarefa task,Long userId){
		Usuario toAssign = daoUsuario.load(userId);
		task.setExecutor(toAssign);
		save(task);
		return task;
	}
}
