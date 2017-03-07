/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.dao;

import ec.edu.uce.medicina.seguimiento.fachada.AbstractFacade;
import ec.edu.uce.medicina.seguimiento.modelo.Persona;
import ec.edu.uce.medicina.seguimiento.modelo.Resultado;
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
public class ResultadoDaoImp extends AbstractFacade<Resultado> implements ResultadoDao {

    @PersistenceContext(unitName = Constantes.UNIDAD_PERSISTENCIA)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResultadoDaoImp() {
        super(Resultado.class);
    }

    @Override
    public boolean buscarRegistroPorPersona(Persona iPersona) {
        boolean respuesta = false;
        Query q = em.createQuery("SELECT r FROM Resultado r WHERE r.idPersona= :persona");
        q.setParameter("persona", iPersona);
        List<Resultado> lista = q.getResultList();
        if (!lista.isEmpty()) {
            respuesta = true;
        }
        return respuesta;
    }

    @Override
    public List<Resultado> listaResultadoPorPregunta(Integer idPregunta, Integer idPersona) {
        Query q = em.createQuery("SELECT r FROM Resultado r WHERE r.idPregunta.idPregunta= :pre"
                + " and r.idPersona.idPersona= :per");
        q.setParameter("pre", idPregunta);
        q.setParameter("per", idPersona);
        return q.getResultList();
    }

    @Override
    public Resultado obtenerResultado(Integer idPregunta, String resultado, Integer idPersona) {
        Query q = em.createQuery("SELECT r FROM Resultado r WHERE r.idPregunta.idPregunta= :pre and r.resultado= :res and r.idPersona.idPersona= :per");
        q.setParameter("res", resultado);
        q.setParameter("pre", idPregunta);
        q.setParameter("per", idPersona);
        return (Resultado) q.getSingleResult();
    }

}
