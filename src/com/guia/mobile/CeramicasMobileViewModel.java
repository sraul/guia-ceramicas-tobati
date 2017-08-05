package com.guia.mobile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Vbox;

import com.coreweb.util.AutoNumeroControl;
import com.guia.domain.Articulo;
import com.guia.domain.Ceramica;
import com.guia.domain.Pedido;
import com.guia.domain.PedidoDetalle;
import com.guia.domain.RegisterDomain;

public class CeramicasMobileViewModel {
	
	private Ceramica selectedCeramica;
	private Articulo selectedArticulo;
	private List<PedidoDetalle> detalles = new ArrayList<PedidoDetalle>();
	private Pedido selectedPedido = new Pedido();
	private PedidoDetalle selectedDetalle = new PedidoDetalle();
	private String numero = "";
	
	@Command
	@NotifyChange("selectedDetalle")
	public void setArticulo() {
		this.selectedDetalle.setArticulo(this.selectedArticulo);
	}
	
	@Command
	@NotifyChange("*")
	public void agregarItem(@BindingParam("comp1") Vbox comp1, @BindingParam("comp2") Vbox comp2) {
		this.detalles.add(this.selectedDetalle);
		this.selectedDetalle = new PedidoDetalle();
		comp1.setVisible(false);
		comp2.setVisible(true);
	}
	
	@Command
	@NotifyChange("*")
	public void generarPedido(@BindingParam("comp1") Vbox comp1, @BindingParam("comp2") Vbox comp2) throws Exception {		
		this.numero = AutoNumeroControl.getAutoNumero(this.selectedCeramica.getId() + "", 5);
		this.detalles.add(this.selectedDetalle);
		comp1.setVisible(false);
		comp2.setVisible(true);
	}
	
	@Command
	@NotifyChange("*")
	public void confirmarPedido(@BindingParam("comp1") Hlayout comp1, @BindingParam("comp2") Vbox comp2) throws Exception {
		this.selectedPedido.setFecha(new Date());
		this.selectedPedido.setNumero(this.numero);
		this.selectedPedido.setCeramica(this.selectedCeramica);
		this.selectedPedido.getDetalles().addAll(this.detalles);
		RegisterDomain rr = RegisterDomain.getInstance();
		rr.saveObject(this.selectedPedido, "mobile");
		Clients.showNotification("PEDIDO CORRECTAMENTE REALIZADO");
		comp1.setVisible(false);
		comp2.setVisible(true);
	}

	/**
	 * @return la lista de ceramicas..
	 */
	public List<Ceramica> getCeramicas() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		return rr.getCeramicas();
	}
	
	/**
	 * GETS / SETS
	 */
	public Ceramica getSelectedCeramica() {
		return selectedCeramica;
	}

	public void setSelectedCeramica(Ceramica selectedCeramica) {
		this.selectedCeramica = selectedCeramica;
	}

	public Articulo getSelectedArticulo() {
		return selectedArticulo;
	}


	public void setSelectedArticulo(Articulo selectedArticulo) {
		this.selectedArticulo = selectedArticulo;
	}


	public List<PedidoDetalle> getDetalles() {
		return detalles;
	}


	public void setDetalles(List<PedidoDetalle> detalles) {
		this.detalles = detalles;
	}

	public PedidoDetalle getSelectedDetalle() {
		return selectedDetalle;
	}

	public void setSelectedDetalle(PedidoDetalle selectedDetalle) {
		this.selectedDetalle = selectedDetalle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Pedido getSelectedPedido() {
		return selectedPedido;
	}

	public void setSelectedPedido(Pedido selectedPedido) {
		this.selectedPedido = selectedPedido;
	}	
}
