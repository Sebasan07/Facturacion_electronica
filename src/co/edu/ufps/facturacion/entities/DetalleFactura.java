package co.edu.ufps.facturacion.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the detalle_factura database table.
 * 
 */
@Entity
@Table(name="detalle_factura")
@NamedQuery(name="DetalleFactura.findAll", query="SELECT d FROM DetalleFactura d")
public class DetalleFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_detalle")
	private int idDetalle;

	private int cantidad;

	//bi-directional many-to-one association to Factura
	@ManyToOne
	private Factura factura;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	private Producto producto;

	public DetalleFactura() {
	}

	public int getIdDetalle() {
		return this.idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Factura getFactura() {
		return this.factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}