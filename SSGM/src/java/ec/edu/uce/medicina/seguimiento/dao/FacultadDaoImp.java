/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.dao;

import ec.edu.uce.medicina.seguimiento.fachada.AbstractFacade;
import ec.edu.uce.medicina.seguimiento.modelo.Facultad;
import ec.edu.uce.medicina.seguimiento.modelo.Universidad;
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
public class FacultadDaoImp extends AbstractFacade<Facultad> implements FacultadDao {

    @PersistenceContext(unitName = Constantes.UNIDAD_PERSISTENCIA)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacultadDaoImp() {
        super(Facultad.class);
    }

    @Override
    public List<Facultad> buscarFacultadPorUniversidad(int idUniversidad1) {
        Query q = em.createQuery("SELECT f FROM Facultad f WHERE f.idUniversidad.idUniversidad= :idUniversidad");
        q.setParameter("idUniversidad", idUniversidad1);
        return q.getResultList();
    }

    @Override
    public boolean buscarRegistroPorUniveridad(Universidad iUniversidad) {
        boolean respuesta = false;
        Query q = em.createQuery("SELECT f FROM Facultad f WHERE f.idUniversidad= :idUniversidad");
        q.setParameter("idUniversidad", iUniversidad);
        List<Facultad> lista = q.getResultList();
        if (!lista.isEmpty()) {
            respuesta = true;
        }
        return respuesta;

    }
}
