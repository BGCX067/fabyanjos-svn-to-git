package br.com.sphera.fake.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import br.com.sphera.entities.Cargo;
import br.com.sphera.entities.Departamento;
import br.com.sphera.entities.Fase;
import br.com.sphera.entities.Projeto;
import br.com.sphera.entities.Tarefa;
import br.com.sphera.entities.Usuario;
import br.com.sphera.mbean.helper.beans.UserFilter;


public class UsuarioDAO {
	private HashMap<Long, Usuario> usuarios;
	private HashMap<Long, Usuario> lixo;
	private Long id = 1L;
	private CargoDAO daoCargo;
	private DepartamentoDAO daoDepartamento;
	private PerfilDao daoPerfil;
	
	public UsuarioDAO() {
		usuarios = new HashMap<Long, Usuario>();
		lixo = new HashMap<Long, Usuario>();
	}
	
	public void setDaoCargo(CargoDAO daoCargo) {
		this.daoCargo = daoCargo;
	}
	public void setDaoDepartamento(DepartamentoDAO daoDepartamento) {
		this.daoDepartamento = daoDepartamento;
	}
	public void setDaoPerfil(PerfilDao daoPerfil) {
		this.daoPerfil = daoPerfil;
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
		
		
		Usuario user = new Usuario("Alexandre Santana Campelo","ale.campelo","12345","aleqi200@gmail.com",50.0);
		user.setDepartamento(daoDepartamento.load(1L));
		user.setCargo(daoCargo.load(1L));
		user.setPerfis(daoPerfil.listAll());
		save(user);
		
		
		user = new Usuario("Fabiana Anjos da S. Campelo","fabyanjos","12345","fabyanjos@gmail.com",50.0);
		user.setDepartamento(daoDepartamento.load(1L));
		user.setCargo(daoCargo.load(1L));
		user.setPerfis(daoPerfil.listAll());
		save(user);
		
		user = new Usuario("Luis Carlos Oliveira da Silva","luis.co.silva","12345","luis.co.silva@gmail.com",50.0);
		user.setDepartamento(daoDepartamento.load(1L));
		user.setCargo(daoCargo.load(2L));
		user.setPerfis(daoPerfil.listAll());
		save(user);
		
		user = new Usuario("Mariane Nogueira Ferroni","marianeferroni","12345","marianeferroni@gmail.com",50.0);
		user.setDepartamento(daoDepartamento.load(1L));
		user.setCargo(daoCargo.load(3L));
		user.setPerfis(daoPerfil.listAll());
		save(user);
		
		user = new Usuario("Roberta Aragon Bento","aragonbento","12345","aragonbento@gmail.com",50.0);
		user.setDepartamento(daoDepartamento.load(1L));
		user.setCargo(daoCargo.load(4L));
		user.setPerfis(daoPerfil.listAll());
		save(user);
	}
	
	public Usuario readByLogin(Usuario login) {
		for(Usuario user : usuarios.values()) {
			System.out.println(user.getLogin() + " " + user.getSenha());
			if(user.getLogin().trim().equals(login.getLogin().trim()) && user.getSenha().trim().equals(login.getSenha().trim()))
				return user;
		}
		return null;
	}
	public List<Usuario> findByFilter(UserFilter filter){
		List<Usuario> found = new ArrayList<Usuario>();
		for(Usuario user : usuarios.values()) {
			if(isInProj(filter.getProjeto(), user)){
				found.add(user);
			}else if(isInDpt(filter.getDepartamentos(),user)){
				found.add(user);
			}else if(isFromCargo(filter.getCargos(),user)){
				found.add(user);
			}
		}
		return found;
	}
	private boolean isFromCargo(List<Cargo> cargos, Usuario user) {
		if(cargos == null)
			return false;
		for(Cargo c:cargos){
			if(c.equals(user.getCargo())){
				return true;
			}
		}
		return false;
	}

	private boolean isInDpt(List<Departamento> departamentos, Usuario user) {
		if(departamentos==null)
			return false;
		for(Departamento d: departamentos){
			if(user.getDepartamento().equals(d)){
				return true;
			}
		}
		return false;
	}

	private boolean isInProj(Projeto projeto,Usuario user){
		if(projeto == null)
			return false;
		if(projeto.getResponsavel().equals(user)){
			return true;
		}
		if(projeto.getFases()==null)
			return false;
		for(Fase f :projeto.getFases()){
			if(f.getTarefas()==null){
				return false;
			}
			for(Tarefa t: f.getTarefas()){
				if(user.equals(t.getExecutor())){
					return true;
				}
			}
		}
		return false;
	}
}
