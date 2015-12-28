package br.com.sphera.mbean;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import br.com.sphera.entities.Usuario;
import br.com.sphera.fake.dao.UsuarioDAO;
import br.com.sphera.global.mbean.AbstractCrudBean;
import br.com.sphera.mbean.helper.beans.UserFilter;
import br.com.sphera.util.JSFUtil;

public class UsuarioController extends AbstractCrudBean{

	private Usuario usuario;
	private List<Usuario> usuarios;
	private UsuarioDAO dao;
	private String confirmSenha;
	private boolean creating = true;
	private String name;
	private UserFilter filter;
	
	public UsuarioController() {
		usuario = new Usuario();
		filter = new UserFilter();
		usuarios = new ArrayList<Usuario>();
	}
	
	public List<Usuario> autocomplete(Object suggest){
		return dao.findUserNameStartWith(String.valueOf(suggest));
	}
	public void setDao(UsuarioDAO dao) {
		this.dao = dao;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public List<SelectItem> getComboList(){
		List<Usuario> users = dao.listAll();
		List<SelectItem> itens = new ArrayList<SelectItem>();
		for (Usuario user : users) {
			itens.add(new SelectItem(user,user.getNome()));
		}
		return itens;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String save(){
		if (creating && !usuario.getSenha().equals(confirmSenha)){
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle rb = context.getApplication().getResourceBundle(context, "i18n");
			JSFUtil.addErrorMessage("valueNotEqual", "user:senhaConf", rb.getString("lblConfSenha"),rb.getString("lblSenha"));
			return null;
		}else if(!creating){
			usuario.setSenha(name);
		}
		
		this.dao.save(usuario);
		JSFUtil.addInfoMessage("sucessSavedUser", null);
		goToList();
		setCreating(false);
		return "usuarios";
	}
	public String list(){
		usuarios = dao.listAll();
		return "usuarios";
	}
	public String delete(){
		System.out.println("delete user: "+usuario.getNome());
		dao.delete(usuario);
		goToList();
		JSFUtil.addInfoMessage("sucessDeletedUser", null);
		return null;
	}
	public void edit(ActionEvent event){
		System.out.println("edit user: "+usuario.getNome());
		setSelectedTab(super.CREATE_TAB);
		setCreating(false);
		setName(usuario.getSenha());
	}
	
	public String getConfirmSenha() {
		return confirmSenha;
	}
	public void setConfirmSenha(String confirmEmail) {
		this.confirmSenha = confirmEmail;
	}
	public void viewUser(){
		System.out.println("view user: "+usuario.getNome());
	}
	public boolean isCreating() {
		return creating;
	}
	public void setCreating(boolean isEdit) {
		this.creating = isEdit;
	}
	public String getName() {
		return name;
	}
	public void setName(String hidden) {
		this.name = hidden;
	}
	
	public String login() {
		Usuario user = dao.readByLogin(this.usuario);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);			
		return "login";
	}
	
	public String logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.invalidate();
		return "logout";
	}

	public void filtrar(){
		usuarios = dao.findByFilter(filter);
	}
	
	public UserFilter getFilter() {
		return filter;
	}

	public void setFilter(UserFilter filter) {
		this.filter = filter;
	}
	public void cleanFields(){
		setCreating(true);
		usuario = new Usuario();
	}
	public void goToList(){
		setSelectedTab(super.LIST_TAB);
		usuarios = dao.listAll();
	}

}
