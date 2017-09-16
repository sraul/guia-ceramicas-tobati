package com.guia.gestion.util;

import org.zkoss.zk.ui.Executions;

import com.coreweb.extras.reporte.DatosReporte;
import com.coreweb.util.Misc;

public abstract class ReporteGCT extends DatosReporte {

	public Misc m = new Misc();
	@Override
	public void setDatosReportes() {
		
		String logo = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/") + "/images"+"/logo.png";
		
		//this.setEmpresa("Guía Cerámicas Tobati");
		this.setLogoEmpresa(logo, 50, 30);
		
		this.informacionReporte();
	}
	
	
	public abstract void informacionReporte();
	
}
