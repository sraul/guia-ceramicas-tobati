package com.guia.gestion.menu;

import java.util.List;

import org.zkoss.bind.annotation.Init;

import com.guia.domain.Ceramica;
import com.guia.domain.RegisterDomain;

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
}
