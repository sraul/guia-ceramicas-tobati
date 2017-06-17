package com.guia.domain;

import java.util.List;

import com.coreweb.domain.Register;
import com.coreweb.domain.Usuario;

@SuppressWarnings("unchecked")
public class RegisterDomain extends Register {

	private RegisterDomain() {
		super();
	}

	public synchronized static RegisterDomain getInstance() {
		return (RegisterDomain) Register.getInstanceCore(RegisterDomain.class.getName());
	}
	
	/**
	 * @return el usuario segun el login y el password..
	 */
	
	public Usuario getUsuario(String login, String password) throws Exception {
		String query = "select u from Usuario u where u.login = '" + login + "' and u.clave = '" + password + "'";
		List<Usuario> list = this.hql(query);
		
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * @return el usuario segun el login..
	 */
	public Usuario getUsuarioRegistrado(String login) throws Exception {
		String query = "select u from Usuario u where u.login = '" + login + "'";
		List<Usuario> list = this.hql(query);
		
		return list.size() > 0 ? list.get(0) : null;
	}
}
