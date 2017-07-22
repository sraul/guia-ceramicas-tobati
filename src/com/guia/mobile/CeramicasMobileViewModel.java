package com.guia.mobile;

import java.util.List;

import com.guia.domain.Ceramica;
import com.guia.domain.RegisterDomain;

public class CeramicasMobileViewModel {

	/**
	 * @return la lista de ceramicas..
	 */
	public List<Ceramica> getCeramicas() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		return rr.getCeramicas();
	}
	
}
