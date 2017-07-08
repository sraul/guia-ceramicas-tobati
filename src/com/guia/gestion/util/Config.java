package com.guia.gestion.util;

import org.zkoss.zk.ui.Sessions;

public class Config {

	public static final String SESION_USUARIO = "USUARIO";
	public static final String SESION_PROPIETARIO = "PROPIETARIO";
	
	public static final String SRC_NO_IMAGE = "/images/articulos/no_image.jpg";	
	public static final String SRC_IMAGES_WEB = "/images/articulos/";
	public static final String SRC_IMAGES = Sessions.getCurrent().getWebApp().getRealPath("images/articulos")	+ "/";

}
