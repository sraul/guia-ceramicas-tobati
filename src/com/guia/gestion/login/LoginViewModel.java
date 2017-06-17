package com.guia.gestion.login;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;

import com.coreweb.domain.Usuario;
import com.guia.domain.Propietario;
import com.guia.domain.RegisterDomain;

public class LoginViewModel {
	
	private Propietario nvoPropietario = new Propietario();
	private Usuario nvoUsuario = new Usuario();
	
	private String user = "";
	private String password = "";
	
	private String mensaje = "";

	@Init
	public void init() {
	}
	
	@Command
	public void loguearse() throws Exception {
		if (validarLogin()) {
			Executions.sendRedirect("/gestion/menuprincipal.zul");
		} else {
			Clients.showNotification(this.mensaje, Clients.NOTIFICATION_TYPE_ERROR, null, null, 0);
		}
	}
	
	@Command
	public void realizarRegistro() throws Exception {
		if (validarRegistro()) {
			RegisterDomain rr = RegisterDomain.getInstance();
			rr.saveObject(nvoUsuario, "");
			rr.saveObject(nvoPropietario, "");
			Clients.showNotification("Registro realizado correctamente..");
			Executions.sendRedirect("/gestion/menuprincipal.zul");
		} else {
			Clients.showNotification(this.mensaje, Clients.NOTIFICATION_TYPE_ERROR, null, null, 0);
		}
	}
	
	/**
	 * @return true si el registro es valido..
	 */
	private boolean validarRegistro() throws Exception {
		boolean out = true;
		this.mensaje = "No se puede completar el registro debido a:";
		
		RegisterDomain rr = RegisterDomain.getInstance();
		if (rr.getUsuarioRegistrado(nvoUsuario.getLogin()) != null) {
			out = false;
			this.mensaje += "\n - Ya existe un usuario con el mismo nombre..";
		}
		
		return out;
	}
	
	/**
	 * @return true si el login es valido..
	 */
	private boolean validarLogin() throws Exception {
		boolean out = true;
		this.mensaje = "No se puede completar el registro debido a:";
		
		RegisterDomain rr = RegisterDomain.getInstance();
		if (rr.getUsuario(this.user, this.password) == null) {
			out = false;
			this.mensaje += "\n - Usuario o password incorrecto..";
		}
		
		return out;
	}
	
	
	/**
	 * GETS / SETS
	 */

	public Propietario getNvoPropietario() {
		return nvoPropietario;
	}

	public void setNvoPropietario(Propietario nvoPropietario) {
		this.nvoPropietario = nvoPropietario;
	}

	public Usuario getNvoUsuario() {
		return nvoUsuario;
	}

	public void setNvoUsuario(Usuario nvoUsuario) {
		this.nvoUsuario = nvoUsuario;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
