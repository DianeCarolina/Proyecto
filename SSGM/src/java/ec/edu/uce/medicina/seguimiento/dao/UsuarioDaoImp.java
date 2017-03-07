/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.dao;

import ec.edu.uce.medicina.seguimiento.fachada.AbstractFacade;
import ec.edu.uce.medicina.seguimiento.modelo.Usuario;
import ec.edu.uce.medicina.seguimiento.util.Constantes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * <b>
 * Descripción de la clase.
 * </b>
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@Stateless
public class UsuarioDaoImp extends AbstractFacade<Usuario> implements UsuarioDao {

    @PersistenceContext(unitName = Constantes.UNIDAD_PERSISTENCIA)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioDaoImp() {
        super(Usuario.class);
    }

    @Override
    public Usuario buscarUsuarioPass(String user, String pass) {
        Usuario usuario = null;
        boolean estado=true;
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.nomUser= :nomUser and u.contrasenia= :contrasenia and u.estado= :estado");
        query.setParameter("nomUser", user);
        query.setParameter("contrasenia", pass);
        query.setParameter("estado", estado);
        List<Usuario> lista = query.getResultList();
        if (!lista.isEmpty()) {
            usuario = lista.get(0);
        }
        return usuario;
    }

}
