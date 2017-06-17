package com.guia.gestion.login;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.util.Clients;

import com.coreweb.domain.Usuario;
import com.guia.domain.Propietario;
import com.guia.domain.RegisterDomain;

public class LoginViewModel {
	
	private Propietario nvoPropietario = new Propietario();
	private Usuario nvoUsuario = new Usuario();
	
	private String mensaje = "";

	@Init
	public void init() {
	}
	
	@Command
	public void realizarRegistro() throws Exception {
		if (validarRegistro()) {
			RegisterDomain rr = RegisterDomain.getInstance();
			rr.saveObject(nvoUsuario, "");
			rr.saveObject(nvoPropietario, "");
			Clients.showNotification("Registro realizado correctamente..");
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
}
