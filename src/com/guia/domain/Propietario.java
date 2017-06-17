package com.guia.domain;

import com.coreweb.domain.Domain;

@SuppressWarnings("serial")
public class Propietario extends Domain {
	
	private String nombre;
	private String cedula;
	private String telefono;

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
}
