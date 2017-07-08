package com.guia.gestion.articulos;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.io.Files;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Popup;

import com.guia.domain.Articulo;
import com.guia.domain.Ceramica;
import com.guia.domain.Propietario;
import com.guia.domain.RegisterDomain;
import com.guia.gestion.util.Config;

public class ArticulosViewModel {

	private Ceramica selectedCeramica;
	
	private Articulo nuevoArticulo = new Articulo();
	private Articulo selectedArticulo;
	
	@Init
	public void init() {
		this.selectedCeramica = this.getPropietarioLogueado().getCeramica();
	}
	
	@Command
	@NotifyChange("*")
	public void agregarArticulo(@BindingParam("comp") Popup comp) throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		this.selectedCeramica.getArticulos().add(this.nuevoArticulo);
		rr.saveObject(this.selectedCeramica, "sys");
		comp.close();
		this.nuevoArticulo = new Articulo();
		Clients.showNotification("REGISTRO AGREGADO..");
	}
	
	@Command
	@NotifyChange("*")
	public void uploadImage(@BindingParam("event") UploadEvent event) {
		try {
			this.subirImagen(event);
		} catch (IOException e) {
			Clients.showNotification("Hubo un error al intentar subir la imagen..", Clients.NOTIFICATION_TYPE_ERROR,
					null, null, 0);
			e.printStackTrace();
		}
	}
	
	/**
	 * upload de la imagen..
	 */
	private void subirImagen(UploadEvent event) throws IOException {
		String fileName = this.selectedCeramica.getId() + "_" + this.selectedArticulo.getId();
		String folder = Config.SRC_IMAGES;
		this.uploadFile(folder, fileName, event);
	}
	
	/**
	 * Este método sirve para cuando queremos subir archivos al servidor lo que
	 * hace es recibir como parametro el evento tipo upload la ruta del
	 * directorio y el nombre del archivo y tambien el tipo de archivo para
	 * controlar si se quieren subir imagenes o docs..
	 */
	public void uploadFile(String folder, String fileName, UploadEvent event) throws IOException {
		InputStream file = event.getMedia().getStreamData();
		String destino = folder + fileName + ".jpg";
		this.copiarArchivo(file, destino);
		this.setImageSrc(Config.SRC_IMAGES_WEB + fileName + ".jpg");
	}
	
	/**
	 * Recibe un archivo y lo copia a un directorio destino..
	 */
	private void copiarArchivo(InputStream file, String destino) throws IOException {
		File dst = new File(destino);
		Files.copy(dst, file);
	}
	
	/**
	 * setea el atributo imagen del articulo y graba en la bd..
	 */
	private void setImageSrc(String destino) {
		try {
			RegisterDomain rr = RegisterDomain.getInstance();
			this.selectedArticulo.setImagen(destino);
			rr.saveObject(this.selectedArticulo, "sys");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * GETS / SETS
	 */
	
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

	public Articulo getNuevoArticulo() {
		return nuevoArticulo;
	}

	public void setNuevoArticulo(Articulo nuevoArticulo) {
		this.nuevoArticulo = nuevoArticulo;
	}

	public Articulo getSelectedArticulo() {
		return selectedArticulo;
	}

	public void setSelectedArticulo(Articulo selectedArticulo) {
		this.selectedArticulo = selectedArticulo;
	}
}
