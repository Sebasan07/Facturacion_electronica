package co.edu.ufps.facturacion.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the rango_numeracion database table.
 * 
 */
@Entity
@Table(name="rango_numeracion")
@NamedQuery(name="RangoNumeracion.findAll", query="SELECT r FROM RangoNumeracion r")
public class RangoNumeracion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_numeracion")
	private int idNumeracion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_resolucion")
	private Date fechaResolucion;

	@Column(name="numero_actual")
	private int numeroActual;

	@Column(name="numero_desde")
	private int numeroDesde;

	@Column(name="numero_hasta")
	private int numeroHasta;

	@Column(name="numero_resolucion")
	private int numeroResolucion;

	private String prefijo;

	//bi-directional many-to-one association to Factura
	@OneToMany(mappedBy="rangoNumeracionBean")
	private List<Factura> facturas;

	public RangoNumeracion() {
	}

	public int getIdNumeracion() {
		return this.idNumeracion;
	}

	public void setIdNumeracion(int idNumeracion) {
		this.idNumeracion = idNumeracion;
	}

	public Date getFechaResolucion() {
		return this.fechaResolucion;
	}

	public void setFechaResolucion(Date fechaResolucion) {
		this.fechaResolucion = fechaResolucion;
	}

	public int getNumeroActual() {
		return this.numeroActual;
	}

	public void setNumeroActual(int numeroActual) {
		this.numeroActual = numeroActual;
	}

	public int getNumeroDesde() {
		return this.numeroDesde;
	}

	public void setNumeroDesde(int numeroDesde) {
		this.numeroDesde = numeroDesde;
	}

	public int getNumeroHasta() {
		return this.numeroHasta;
	}

	public void setNumeroHasta(int numeroHasta) {
		this.numeroHasta = numeroHasta;
	}

	public int getNumeroResolucion() {
		return this.numeroResolucion;
	}

	public void setNumeroResolucion(int numeroResolucion) {
		this.numeroResolucion = numeroResolucion;
	}

	public String getPrefijo() {
		return this.prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	public List<Factura> getFacturas() {
		return this.facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Factura addFactura(Factura factura) {
		getFacturas().add(factura);
		factura.setRangoNumeracionBean(this);

		return factura;
	}

	public Factura removeFactura(Factura factura) {
		getFacturas().remove(factura);
		factura.setRangoNumeracionBean(null);

		return factura;
	}

}