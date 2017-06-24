package com.guia.domain;

import com.coreweb.domain.Domain;

@SuppressWarnings("serial")
public class Ceramica extends Domain {

	private String nombre;
	private String direccion;
	private String telefono;
	
	private double latitud;
	private double longitud;
	
	@Override
	public int compareTo(Object arg0) {
		return -1;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

}
