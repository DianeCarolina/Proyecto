/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.dao;

import ec.edu.uce.medicina.seguimiento.fachada.AbstractFacade;
import ec.edu.uce.medicina.seguimiento.modelo.Categoria;
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
public class CategoriaDaoImp extends AbstractFacade<Categoria> implements CategoriaDao {

    @PersistenceContext(unitName = Constantes.UNIDAD_PERSISTENCIA)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaDaoImp() {
        super(Categoria.class);
    }

    @Override
    public List<Categoria> buscarCategoriaPorEncuesta(int idEncuesta) {
        Query q = em.createQuery("SELECT c FROM Categoria c WHERE c.idEncuesta.idEncuesta= :idEncuesta");
        q.setParameter("idEncuesta", idEncuesta);
        return q.getResultList();
    }

//    @Override
//    public boolean buscarPorEncuesta(Encuesta iEncuesta) {
//        boolean respuesta = false;
//        Query q = em.createQuery("SELECT c FROM Categoria c WHERE c.idEncuesta= :encuesta");
//        q.setParameter("encuesta", iEncuesta);
//        List<Encuesta> lista = q.getResultList();
//        if (!lista.isEmpty()) {
//            respuesta = true;
//        }
//        return respuesta;
//    }
    @Override
    public List<Categoria> listaCategoriaPorEncuesta(Integer id) {
        Query q = em.createQuery("SELECT c FROM Categoria c JOIN C.idEncuesta as cat WHERE cat.idEncuesta= :idEncuesta ORDER BY c.orden");
        q.setParameter("idEncuesta", id);
        return q.getResultList();
    }

}
