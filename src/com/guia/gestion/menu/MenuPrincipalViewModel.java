package com.guia.gestion.menu;

import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Sessions;

import com.guia.domain.Ceramica;
import com.guia.domain.Propietario;
import com.guia.domain.RegisterDomain;
import com.guia.gestion.util.Config;

public class MenuPrincipalViewModel {
	
	@Init
	public void init() {
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
}
