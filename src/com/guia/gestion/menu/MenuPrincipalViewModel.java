package com.guia.gestion.menu;

import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;

import com.guia.domain.Ceramica;
import com.guia.domain.Propietario;
import com.guia.domain.RegisterDomain;
import com.guia.gestion.util.Config;

public class MenuPrincipalViewModel {
	
	private Ceramica selectedCeramica;
	
	@Init
	public void init() {
		this.selectedCeramica = this.getPropietarioLogueado().getCeramica();
	}
	
	@Command
	public void guardarCeramica() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		rr.saveObject(this.selectedCeramica, "sys");
		Clients.showNotification("REGISTRO GUARDADO..");
	}
	
	/**
	 * @return la lista de ceramicas..
	 */
	public List<Ceramica> getCeramicas() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		return rr.getCeramicas();
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
