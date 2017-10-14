package com.guia.gestion.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import com.coreweb.domain.Usuario;
import com.coreweb.extras.reporte.DatosColumnas;
import com.coreweb.util.Misc;
import com.guia.domain.Ceramica;
import com.guia.domain.Pedido;
import com.guia.domain.PedidoDetalle;
import com.guia.domain.Propietario;
import com.guia.domain.RegisterDomain;
import com.guia.gestion.util.Config;
import com.guia.gestion.util.ReporteGCT;

import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;

public class MenuPrincipalViewModel {
	
	private Ceramica selectedCeramica;
	private Pedido selectedPedido;
	
	private Window w;
	
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
	public void guardarCeramica(@BindingParam("latitud") Label lat, 
			@BindingParam("longitud") Label lon) throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		this.selectedCeramica.setLatitud(lat.getValue());
		this.selectedCeramica.setLongitud(lon.getValue());
		rr.saveObject(this.selectedCeramica, "sys");
		Clients.showNotification("REGISTRO GUARDADO.. " + lat.getValue() + " / " + lon.getValue());
	}
	
	@Command
	public void verArticulos() {
		Executions.sendRedirect("articulos.zul");
	}
	
	@Command
	public void salir() throws Exception {
		if (Messagebox.show("Esta seguro que desea Salir del Menu Principal?", "Question",
				Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.OK) {
			Executions.sendRedirect("/");
			this.anularSesion();
		}		
	}
	
	@Command
	public void reportePedido() {
		List<Object[]> list = new ArrayList<Object[]>();
		for (PedidoDetalle det : this.selectedPedido.getDetalles()) {
			Object[] data = new Object[] { det.getArticulo().getDescripcion(), det.getArticulo().getPrecio(), det.getCantidad(),
					det.getImporte() };
			list.add(data);
		}

		ReportePedido rep = new ReportePedido(this.selectedPedido);
		rep.setDatosReporte(list);
		this.showReporte(rep);
	}
	
	@Command
	@NotifyChange("*")
	public void confirmarPedido() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		this.selectedPedido.setDbEstado(Pedido.ESTADO_CONFIRMADO);
		rr.saveObject(this.selectedPedido, this.getUsuarioLogueado().getNombre());
		
		this.selectedPedido = null;
		Clients.showNotification("PEDIDO CONFIRMADO..");
	}
	
	@Command
	@NotifyChange("*")
	public void cancelarPedido() throws Exception {
		RegisterDomain rr = RegisterDomain.getInstance();
		rr.deleteObject(this.selectedPedido);
		
		this.selectedPedido = null;
		Clients.showNotification("PEDIDO CANCELADO..");
	}
	
	/**
	 * muestra el reporte..
	 */
	private void showReporte(ReportePedido rep) {

		// genera el pdf en el directorio de reportes
		rep.setDirectorioBase(Executions.getCurrent().getDesktop().getWebApp().getRealPath("/") + "/reportes");
		rep.setUsuario(this.getPropietarioLogueado().getNombre());
		rep.setEmpresa(this.getPropietarioLogueado().getCeramica().getNombre());
		rep.ejecutar(false);

		String urlPdf = "/reportes/" + rep.getArchivoSalida();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reporte", rep);
		map.put("titulo", rep.getTitulo());
		map.put("pdf", urlPdf);
		map.put("anchoWindows", "800px");
		map.put("altoWindows", "600px");
		map.put("altoReporte", "500px");
		map.put("control", this);

		Window window = (Window) Executions.createComponents("/reportes/ViewPdf.zul", null, map);

		this.w = window;
		this.w.setPosition("center");
		this.w.doModal();
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
	
	/**
	 * @return el usuario logueado..
	 */
	public Usuario getUsuarioLogueado() {
		Usuario user = (Usuario) Sessions.getCurrent().getAttribute(Config.SESION_USUARIO);
		return user;
	}
	
	/**
	 * anula la session..
	 */
	private void anularSesion() throws Exception {
		Sessions.getCurrent().setAttribute(Config.SESION_USUARIO, null);
		Sessions.getCurrent().setAttribute(Config.SESION_PROPIETARIO, null);
	}

	public Ceramica getSelectedCeramica() {
		return selectedCeramica;
	}

	public void setSelectedCeramica(Ceramica selectedCeramica) {
		this.selectedCeramica = selectedCeramica;
	}

	public Pedido getSelectedPedido() {
		return selectedPedido;
	}

	public void setSelectedPedido(Pedido selectedPedido) {
		this.selectedPedido = selectedPedido;
	}
}

/**
 * Reporte de Pedidos..
 */
class ReportePedido extends ReporteGCT {

	static List<DatosColumnas> cols = new ArrayList<DatosColumnas>();
	static DatosColumnas col0 = new DatosColumnas("Descripcion", TIPO_STRING);
	static DatosColumnas col1 = new DatosColumnas("Precio", TIPO_DOUBLE, 60);
	static DatosColumnas col2 = new DatosColumnas("Cantidad", TIPO_INTEGER, 60);
	static DatosColumnas col3 = new DatosColumnas("Importe", TIPO_DOUBLE, 60, true);
	
	private Pedido pedido;
	private Misc misc = new Misc();

	public ReportePedido(Pedido pedido) {
		this.pedido = pedido;
	}

	static {
		cols.add(col0);
		cols.add(col1);
		cols.add(col2);
		cols.add(col3);
	}

	@Override
	public void informacionReporte() {
		this.setTitulo("Pedido de Venta");
		this.setDirectorio("pedidos");
		this.setNombreArchivo("Pedido-");
		this.setTitulosColumnas(cols);
		this.setBody(this.getCuerpo());
	}

	/**
	 * cabecera del reporte..
	 */
	@SuppressWarnings("rawtypes")
	private ComponentBuilder getCuerpo() {

		VerticalListBuilder out = cmp.verticalList();
		out.add(cmp.horizontalFlowList()
				.add(this.textoParValor("Fecha", misc.dateToString(this.pedido.getFecha(), "dd-MM-yyyy")))
				.add(this.textoParValor("Número", this.pedido.getNumero())));
		out.add(cmp.horizontalFlowList().add(this.texto("")));
		out.add(cmp.horizontalFlowList()
				.add(this.textoParValor("Nombre", this.pedido.getNombreApellido()))
				.add(this.textoParValor("Cedula", this.pedido.getCedula())));
		out.add(cmp.horizontalFlowList().add(this.texto("")));
		out.add(cmp.horizontalFlowList()
				.add(this.textoParValor("Direccion", this.pedido.getDireccion()))
				.add(this.textoParValor("Telefono", this.pedido.getTelefono())));
		out.add(cmp.horizontalFlowList().add(this.texto("")));

		return out;
	}
}