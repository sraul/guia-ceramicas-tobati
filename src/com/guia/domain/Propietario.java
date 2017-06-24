package com.guia.domain;

import com.coreweb.domain.Domain;
import com.coreweb.domain.Usuario;

@SuppressWarnings("serial")
public class Propietario extends Domain {
	
	private String nombre;
	private String cedula;
	private String telefono;
	
	private Ceramica ceramica;
	private Usuario usuario;

	@Override
	public int compareTo(Object o) {
		return -1;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Ceramica getCeramica() {
		return ceramica;
	}

	public void setCeramica(Ceramica ceramica) {
		this.ceramica = ceramica;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
