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
	
	/**
	 * @return la lista de ceramicas registradas..
	 */
	public List<Ceramica> getCeramicas() throws Exception {
		String query = "select c from Ceramica c order by c.nombre";
		return this.hql(query);
	}
	
	public static void main(String[] args) {
		try {
			RegisterDomain rr = RegisterDomain.getInstance();
			for (int i = 0; i < 10; i++) {
				Ceramica c = new Ceramica();
				c.setNombre("CERAMICA " + (i + 1));
				c.setTelefono("TEL " + (i + 1));
				c.setDireccion("DIRECCION " + (i + 1));
				rr.saveObject(c, "sys");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
