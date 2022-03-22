package com.trudmin.api.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.trudmin.api.dao.IUsuarioDao;
import com.trudmin.api.model.Usuario;

@Transactional
@Repository
public class UsuarioDaoImpl implements IUsuarioDao{
	
	private static final Logger LOG = Logger.getLogger(UsuarioDaoImpl.class.getName());

    @PersistenceContext //Se encarga de administrar las entidades y las transacciones
    EntityManager entityManager;

    @Transactional
    @Override
    public List<Usuario> obtenerUsuario() {
        final String LISTAR_COMPRADORES = "Select u From Usuario u where u.estatus = true";
        LOG.info(LISTAR_COMPRADORES);
        return entityManager.createQuery(LISTAR_COMPRADORES, Usuario.class).getResultList();
    }

    @Override
    public Usuario obtenerUsuarioId(long id) {
    	Usuario usuario = new Usuario();
    	usuario = entityManager.find(Usuario.class, id);
        return usuario;
    }

    @Override
    public Usuario registrarUsuario(Usuario comprador) {
        comprador.setEstatus(true);
        entityManager.merge(comprador);
        return comprador;
    }

    @Override
    public Usuario actualizarUsuario(Usuario comprador) {
        entityManager.merge(comprador);
        return comprador;
    }

    @Override
    public void eliminarUsuario(long id) {
        final String DELETE_USUARIO = "UPDATE Usuario c SET c.estatus = false where c.id = :id";
        entityManager.createQuery(DELETE_USUARIO).setParameter("id", id).executeUpdate();
    }
    
    @Override
    public Usuario findByUserName(String nombreUsuario) {
    	final String USUARIO = "From Usuario u where u.nombreUsuario = :nombreUsuario";
    	Usuario usuario = new Usuario();
    	usuario = entityManager.createQuery(USUARIO,Usuario.class).setParameter("nombreUsuario", nombreUsuario).getSingleResult();
        return usuario;
    }

}
