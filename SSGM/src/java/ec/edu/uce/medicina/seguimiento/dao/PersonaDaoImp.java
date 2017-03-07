/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.dao;

import ec.edu.uce.medicina.seguimiento.fachada.AbstractFacade;
import ec.edu.uce.medicina.seguimiento.modelo.Carrera;
import ec.edu.uce.medicina.seguimiento.modelo.Persona;
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
public class PersonaDaoImp extends AbstractFacade<Persona> implements PersonaDao {

    @PersistenceContext(unitName = Constantes.UNIDAD_PERSISTENCIA)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaDaoImp() {
        super(Persona.class);
    }

    @Override
    public Persona buscarPersonaPorCedula(String cedula) {
        Persona personaEncontrada = null;
        Query q = em.createQuery("SELECT p FROM Persona p WHERE p.numeroIdentificacion= :numeroIdentificacion");
        q.setParameter("numeroIdentificacion", cedula);
        List<Persona> lista = q.getResultList();
        if (!lista.isEmpty()) {
            personaEncontrada = lista.get(0);
        }
        return personaEncontrada;
    }

    @Override
    public boolean buscarRegistroPorCarrera(Carrera iCarrera) {
        boolean respuesta = false;
        Query q = em.createQuery("SELECT p FROM Persona p WHERE p.idCarrera= :carrera");
        q.setParameter("carrera", iCarrera);
        List<Persona> lista = q.getResultList();
        if (!lista.isEmpty()) {
            respuesta = true;
        }
        return respuesta;
    }

    @Override
    public List<Persona> listaPersonaPorCarrera(Integer idCarrera) {
        Query q = em.createQuery("SELECT p FROM Persona p WHERE p.idCarrera.idCarrera= :idCarrera");
        q.setParameter("idCarrera", idCarrera);
        return q.getResultList();
    }
}
