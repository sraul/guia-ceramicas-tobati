package com.guia.gestion.login;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;

import com.coreweb.domain.Usuario;
import com.guia.domain.Ceramica;
import com.guia.domain.Propietario;
import com.guia.domain.RegisterDomain;
import com.guia.gestion.util.Config;

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
			rr.saveObject(this.nvoUsuario, "");
			
			Ceramica cm = new Ceramica();
			cm.setNombre("SIN NOMBRE");
			cm.setDireccion("SIN DIRECCION");
			cm.setTelefono("SIN TELEFONO");
			rr.saveObject(cm, "");
			
			// enlaza el propietario con el usuario y asigna una ceramica por defecto..
			this.nvoPropietario.setUsuario(this.nvoUsuario);
			this.nvoPropietario.setCeramica(cm);
			rr.saveObject(nvoPropietario, "");
			
			this.guardarSesion(this.nvoUsuario);
			
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
		Usuario usuario = rr.getUsuario(this.user, this.password);
		if ( usuario == null) {
			out = false;
			this.mensaje += "\n - Usuario o password incorrecto..";
		}
		
		if (out == true) {
			this.guardarSesion(usuario);
		}
		
		return out;
	}
	
	/**
	 * guarda datos en la session..
	 */
	private void guardarSesion(Usuario usuario) throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		Sessions.getCurrent().setAttribute(Config.SESION_USUARIO, usuario);
		Propietario prop = rr.getPropietarioByUsuario(usuario.getId());
		Sessions.getCurrent().setAttribute(Config.SESION_PROPIETARIO, prop);
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
