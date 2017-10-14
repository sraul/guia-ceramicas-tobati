package com.guia.domain;

import java.util.HashSet;
import java.util.Set;

import com.coreweb.domain.Domain;

@SuppressWarnings("serial")
public class Ceramica extends Domain {

	private String nombre = "";
	private String direccion = "";
	private String telefono = "";
	
	private String latitud = "";
	private String longitud = "";
	
	private Set<Articulo> articulos = new HashSet<Articulo>();
	
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

	public Set<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(Set<Articulo> articulos) {
		this.articulos = articulos;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
}
