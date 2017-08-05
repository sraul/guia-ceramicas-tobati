package com.guia.gestion.menu;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;

import com.guia.domain.Ceramica;
import com.guia.domain.Pedido;
import com.guia.domain.Propietario;
import com.guia.domain.RegisterDomain;
import com.guia.gestion.util.Config;

public class MenuPrincipalViewModel {
	
	private Ceramica selectedCeramica;
	
	@Init
	public void init() {
		if (this.getPropietarioLogueado() == null) {
			Executions.sendRedirect("/");
		} else {
			this.selectedCeramica = this.getPropietarioLogueado().getCeramica();
		}
	}
	
	@Command
	@NotifyChange("ceramicas")
	public void guardarCeramica() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		rr.saveObject(this.selectedCeramica, "sys");
		Clients.showNotification("REGISTRO GUARDADO..");
	}
	
	@Command
	public void verArticulos() {
		Executions.sendRedirect("articulos.zul");
	}
	
	@Command
	public void salir() {
		if (Messagebox.show("Esta seguro que desea Salir del Menu Principal?", "Question",
				Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK) {
			Executions.sendRedirect("/");
		}		
	}
	
	/**
	 * @return la lista de ceramicas..
	 */
	public List<Ceramica> getCeramicas() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		return rr.getCeramicas();
	}
	
	/**
	 * @return la lista de pedidos de la ceramica seleccionada..
	 */
	public List<Pedido> getPedidos() throws Exception {
		if (this.selectedCeramica == null) {
			return new ArrayList<Pedido>();
		}
		RegisterDomain rr = RegisterDomain.getInstance();
		return rr.getPedidos(this.selectedCeramica.getId());
	}
	
	/**
	 * @return el propietario logueado..
	 */
	public Propietario getPropietarioLogueado() {
		Propietario prop = (Propietario) Sessions.getCurrent().getAttribute(Config.SESION_PROPIETARIO);
		return prop;
	}

	public Ceramica getSelectedCeramica() {
		return selectedCeramica;
	}

	public void setSelectedCeramica(Ceramica selectedCeramica) {
		this.selectedCeramica = selectedCeramica;
	}
}
