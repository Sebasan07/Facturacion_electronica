package co.edu.ufps.facturacion.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import co.edu.ufps.facturacion.connection.Conexion;
import co.edu.ufps.facturacion.entities.*;

public class UsuarioDAO extends Conexion<Usuario> implements GenericDAO<Usuario> {

	public UsuarioDAO() {
		super(Usuario.class);
	}

	public boolean logear(Usuario usuario) {
		boolean isLog = true;
		Query query = null;
		try {
			if (usuario.getCorreo() != null && usuario.getContrasena() != null) {
				query = getEm().createNamedQuery(Usuario.class.getSimpleName() + ".logAdmin", Usuario.class);
				query.setParameter("correo", usuario.getCorreo());
				query.setParameter("contrasena", usuario.getContrasena());
				query.getSingleResult();
			}
		} catch (NoResultException e) {
			isLog = false;
		}

		return isLog;
	}
}
