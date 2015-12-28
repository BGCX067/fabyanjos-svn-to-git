package br.com.sphera.fake.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import br.com.sphera.entities.Usuario;


public class UsuarioDAO {
	private HashMap<Long, Usuario> usuarios;
	private HashMap<Long, Usuario> lixo;
	private Long id = 1L;
	
	public UsuarioDAO() {
		usuarios = new HashMap<Long, Usuario>();
		lixo = new HashMap<Long, Usuario>();
	}
	
	public Long save(Usuario user) {
		if(user.getId() == null || user.getId() == 0){
			user.setId(id++);
		}
		usuarios.put(user.getId(), user);
		return user.getId();
	}
	public Usuario load(Long key) {
		return usuarios.get(key);
	}
	public List<Usuario> listAll(){
		List<Usuario> list = new ArrayList<Usuario>(usuarios.values());
		return list;
	}
	public boolean delete(Usuario user){
		lixo.put(user.getId(),usuarios.remove(user.getId()));
		return true;
	}
	public List<Usuario> findUserNameStartWith(String begin){
		List<Usuario> result = new ArrayList<Usuario>();
		List<Usuario> list = listAll();
		for (Usuario usuario : list) {
			if(usuario.getNome().startsWith(begin)){
				result.add(usuario);
			}
		}
		return result;
	}
	public Usuario findByLogin(String login){
		List<Usuario> list = listAll();
		for (Usuario usuario : list) {
			if(usuario.getLogin().equals(login)){
				return usuario;
			}
		}
		return null;
	}
	@PostConstruct
	public void criaDados(){
		for (int i = 1; i < 60; i++) {
			Usuario user = new Usuario("User "+i,"fulano"+i,"12345","email"+i+"@domain.com",(double)i);
			save(user);
		}
	}
	
	public Usuario readByLogin(Usuario login) {
		for(Usuario user : usuarios.values()) {
			System.out.println(user.getLogin() + " " + user.getSenha());
			if(user.getLogin().trim().equals(login.getLogin().trim()) && user.getSenha().trim().equals(login.getSenha().trim()))
				return user;
		}
		return null;
	}
}
