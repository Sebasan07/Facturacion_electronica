package co.edu.ufps.facturacion.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigo;

	private String descripcion;

	private byte estado;

	private double iva;

	private String nombre;

	@Column(name="porcentaje_descuento")
	private double porcentajeDescuento;

	@Column(name="unidad_media")
	private String unidadMedia;

	@Column(name="valor_unitario")
	private double valorUnitario;

	//bi-directional many-to-one association to DetalleFactura
	@OneToMany(mappedBy="producto")
	private List<DetalleFactura> detalleFacturas;

	public Producto() {
	}

	public Producto(int codigo, String descripcion, byte estado, double iva, String nombre, double porcentajeDescuento,
			String unidadMedia, double valorUnitario) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.estado = estado;
		this.iva = iva;
		this.nombre = nombre;
		this.porcentajeDescuento = porcentajeDescuento;
		this.unidadMedia = unidadMedia;
		this.valorUnitario = valorUnitario;
		detalleFacturas = new ArrayList<>();
	}



	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public byte getEstado() {
		return this.estado;
	}

	public void setEstado(byte estado) {
		this.estado = estado;
	}

	public double getIva() {
		return this.iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPorcentajeDescuento() {
		return this.porcentajeDescuento;
	}

	public void setPorcentajeDescuento(double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public String getUnidadMedia() {
		return this.unidadMedia;
	}

	public void setUnidadMedia(String unidadMedia) {
		this.unidadMedia = unidadMedia;
	}

	public double getValorUnitario() {
		return this.valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public List<DetalleFactura> getDetalleFacturas() {
		return this.detalleFacturas;
	}

	public void setDetalleFacturas(List<DetalleFactura> detalleFacturas) {
		this.detalleFacturas = detalleFacturas;
	}

	public DetalleFactura addDetalleFactura(DetalleFactura detalleFactura) {
		getDetalleFacturas().add(detalleFactura);
		detalleFactura.setProducto(this);

		return detalleFactura;
	}

	public DetalleFactura removeDetalleFactura(DetalleFactura detalleFactura) {
		getDetalleFacturas().remove(detalleFactura);
		detalleFactura.setProducto(null);

		return detalleFactura;
	}

}