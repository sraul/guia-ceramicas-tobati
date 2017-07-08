package com.guia.domain;

import com.coreweb.domain.Domain;
import com.guia.gestion.util.Config;

@SuppressWarnings("serial")
public class Articulo extends Domain {

	private String nombre = "";
	private String descripcion = "";
	private double precio;
	
	private String imagen = "";
	
	@Override
	public int compareTo(Object o) {
		return -1;
	}
	
	/**
	 * @return el url de la imagen..
	 */
	public String getImageSRC() {
		return this.imagen.isEmpty() ? Config.SRC_NO_IMAGE : this.imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
}
