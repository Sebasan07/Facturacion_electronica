package co.edu.ufps.facturacion.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String correo;

	private String apellido;

	private String contrasena;

	private byte estado;

	private String nombre;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	private Empresa empresa;

	//bi-directional many-to-one association to RolUsuario
	@ManyToOne
	@JoinColumn(name="rol_usuario")
	private RolUsuario rolUsuarioBean;

	public Usuario() {
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public byte getEstado() {
		return this.estado;
	}

	public void setEstado(byte estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public RolUsuario getRolUsuarioBean() {
		return this.rolUsuarioBean;
	}

	public void setRolUsuarioBean(RolUsuario rolUsuarioBean) {
		this.rolUsuarioBean = rolUsuarioBean;
	}

}