package com.guia.gestion.mapa;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;

import com.guia.domain.Ceramica;
import com.guia.domain.RegisterDomain;

public class MapaViewModel {

	@Init
	public void init() {
	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireEventListeners(view, this);
	}
	
	/**
	 * @return las ubicaciones de las ceramicas..
	 */
	public List<String[]> getUbicacionesCeramicas() throws Exception {
		List<String[]> out = new ArrayList<String[]>();		
		RegisterDomain rr = RegisterDomain.getInstance();
		List<Ceramica> ceramicas = rr.getCeramicas();
		for (Ceramica ceramica : ceramicas) {
			if (ceramica.getLatitud() != null || !ceramica.getLatitud().isEmpty()) {
				out.add(new String[]{ ceramica.getLatitud(), ceramica.getLongitud(), ceramica.getNombre() });
			}
		}
		return out;
	}	
}
