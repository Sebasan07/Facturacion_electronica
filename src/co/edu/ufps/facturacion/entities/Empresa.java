package co.edu.ufps.facturacion.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the empresa database table.
 * 
 */
@Entity
@NamedQuery(name="Empresa.findAll", query="SELECT e FROM Empresa e")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int nit;

	@Column(name="correo_empresa")
	private String correoEmpresa;

	private String departamento;

	private String direccion;

	private String documento;

	@Lob
	@Column(name="logo_empresa")
	private String logoEmpresa;

	private String municipio;

	@Column(name="nombre_representante")
	private String nombreRepresentante;

	@Column(name="numero_documento")
	private String numeroDocumento;

	@Column(name="razon_social")
	private String razonSocial;

	private String telefono;

	//bi-directional many-to-one association to TipoDocumento
	@ManyToOne
	@JoinColumn(name="tipo_documento")
	private TipoDocumento tipoDocumentoBean;

	//bi-directional many-to-one association to Factura
	@OneToMany(mappedBy="empresa")
	private List<Factura> facturas;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="empresa")
	private List<Usuario> usuarios;

	public Empresa() {
	}

	public int getNit() {
		return this.nit;
	}

	public void setNit(int nit) {
		this.nit = nit;
	}

	public String getCorreoEmpresa() {
		return this.correoEmpresa;
	}

	public void setCorreoEmpresa(String correoEmpresa) {
		this.correoEmpresa = correoEmpresa;
	}

	public String getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getLogoEmpresa() {
		return this.logoEmpresa;
	}

	public void setLogoEmpresa(String logoEmpresa) {
		this.logoEmpresa = logoEmpresa;
	}

	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getNombreRepresentante() {
		return this.nombreRepresentante;
	}

	public void setNombreRepresentante(String nombreRepresentante) {
		this.nombreRepresentante = nombreRepresentante;
	}

	public String getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public TipoDocumento getTipoDocumentoBean() {
		return this.tipoDocumentoBean;
	}

	public void setTipoDocumentoBean(TipoDocumento tipoDocumentoBean) {
		this.tipoDocumentoBean = tipoDocumentoBean;
	}

	public List<Factura> getFacturas() {
		return this.facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Factura addFactura(Factura factura) {
		getFacturas().add(factura);
		factura.setEmpresa(this);

		return factura;
	}

	public Factura removeFactura(Factura factura) {
		getFacturas().remove(factura);
		factura.setEmpresa(null);

		return factura;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setEmpresa(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setEmpresa(null);

		return usuario;
	}

}