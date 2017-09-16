package com.guia.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.coreweb.domain.Domain;

@SuppressWarnings("serial")
public class Pedido extends Domain {

	private Date fecha;
	private String numero;
	private String nombreApellido;
	private String cedula;
	private String direccion;
	private String telefono;
	
	private Ceramica ceramica;
	private Set<PedidoDetalle> detalles = new HashSet<PedidoDetalle>();
	
	@Override
	public int compareTo(Object o) {
		return -1;
	}
	
	/**
	 * @return el importe total del pedido..
	 */
	public double getTotalImporteGs() {
		double out = 0;
		for (PedidoDetalle det : this.detalles) {
			out += det.getImporte();
		}
		return out;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Ceramica getCeramica() {
		return ceramica;
	}

	public void setCeramica(Ceramica ceramica) {
		this.ceramica = ceramica;
	}

	public Set<PedidoDetalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(Set<PedidoDetalle> detalles) {
		this.detalles = detalles;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
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
}
