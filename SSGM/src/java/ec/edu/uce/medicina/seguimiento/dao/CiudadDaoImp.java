/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.dao;

import ec.edu.uce.medicina.seguimiento.fachada.AbstractFacade;
import ec.edu.uce.medicina.seguimiento.modelo.Ciudad;
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
public class CiudadDaoImp extends AbstractFacade<Ciudad> implements CiudadDao {

    @PersistenceContext(unitName = Constantes.UNIDAD_PERSISTENCIA)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CiudadDaoImp() {
        super(Ciudad.class);
    }

    @Override
    public List<Ciudad> buscarCiudadesPorProvincia(int idProvincia) {
        Query q = em.createQuery("SELECT c FROM Ciudad c WHERE c.idProvincia.idProvincia= :idProvincia");
        q.setParameter("idProvincia", idProvincia);
        return q.getResultList();
    }
}
