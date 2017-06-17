package com.guia.domain;

import com.coreweb.domain.Register;
import com.coreweb.domain.Usuario;

public class RegisterDomain extends Register {

	private RegisterDomain() {
		super();
	}

	public synchronized static RegisterDomain getInstance() {
		return (RegisterDomain) Register.getInstanceCore(RegisterDomain.class.getName());
	}
	
	public static void main(String[] args) {
		try {
			RegisterDomain rr = RegisterDomain.getInstance();
			Usuario user = new Usuario();
			user.setLogin("adan");
			user.setClave("test");
			rr.saveObject(user, "sys");
		} catch (Exception e) {
			
		}
	}
}
