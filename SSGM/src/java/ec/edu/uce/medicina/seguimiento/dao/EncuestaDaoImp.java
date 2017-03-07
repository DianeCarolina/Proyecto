/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.dao;

import ec.edu.uce.medicina.seguimiento.fachada.AbstractFacade;
import ec.edu.uce.medicina.seguimiento.modelo.Carrera;
import ec.edu.uce.medicina.seguimiento.modelo.Encuesta;
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
public class EncuestaDaoImp extends AbstractFacade<Encuesta> implements EncuestaDao {

    @PersistenceContext(unitName = Constantes.UNIDAD_PERSISTENCIA)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EncuestaDaoImp() {
        super(Encuesta.class);
    }

    @Override
    public boolean buscarRegistroPorCarrera(Carrera iCarrera) {
        boolean respuesta = false;
        Query q = em.createQuery("SELECT e FROM Encuesta e WHERE e.idCarrera= :carrera");
        q.setParameter("carrera", iCarrera);
        List<Carrera> lista = q.getResultList();
        if (!lista.isEmpty()) {
            respuesta = true;
        }
        return respuesta;
    }

    @Override
    public List<Encuesta> listaEncuestaPorCarrera(Integer id) {
        Query q = em.createQuery("SELECT e FROM Encuesta e JOIN E.idCarrera as enc WHERE enc.idCarrera= :idCarrera");
        q.setParameter("idCarrera", id);
        return q.getResultList();
    }

  
    @Override
    public Encuesta buscarEncuestaPorCarrera(Integer idCarrera) {
        boolean estado = true;
        Encuesta encuestaEncontrada = null;
        Query q = em.createQuery("SELECT e FROM Encuesta e WHERE e.idCarrera.idCarrera= :carrera AND e.activo= :estado");
        q.setParameter("carrera", idCarrera);
        q.setParameter("estado", estado);

        List<Encuesta> lista = q.getResultList();
        if (!lista.isEmpty()) {
            encuestaEncontrada = lista.get(0);
        }
        return encuestaEncontrada;

    }

}
