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
	
	/**
	 * @return la lista de pedidos segun ceramica..
	 */
	public List<Pedido> getPedidos(long idCeramica) throws Exception {
		String query = "select p from Pedido p where p.ceramica.id = " + idCeramica + " "
				+ " and p.dbEstado != '" + Pedido.ESTADO_CONFIRMADO +  "' order by p.numero";
		return this.hql(query);
	}
	
	/**
	 * @return el propietario a partir del usuario..
	 */
	public Propietario getPropietarioByUsuario(long idUsuario) throws Exception {
		String query = "select p from Propietario p where p.usuario.id = "+ idUsuario +"";
		List<Propietario> list = this.hql(query);
		
		return list.size() > 0 ? list.get(0) : null;
	}
	
	public static void main(String[] args) {
		try {
			RegisterDomain rr = RegisterDomain.getInstance();
			Propietario prop = (Propietario) rr.getObject(Propietario.class.getName(), 1);
			prop.setCeramica(rr.getCeramicas().get(0));
			prop.setUsuario(rr.getAllUsuarios().get(0));
			
			rr.saveObject(prop, "sys");
						
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
