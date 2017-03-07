/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.dao;

import ec.edu.uce.medicina.seguimiento.dto.GraficoBarrasCaPersona;
import ec.edu.uce.medicina.seguimiento.dto.GraficoEsCarrera;
import ec.edu.uce.medicina.seguimiento.fachada.AbstractFacade;
import ec.edu.uce.medicina.seguimiento.modelo.Carrera;
import ec.edu.uce.medicina.seguimiento.modelo.Facultad;
import ec.edu.uce.medicina.seguimiento.util.Constantes;
import java.util.ArrayList;
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
public class CarreraDaoImp extends AbstractFacade<Carrera> implements CarreraDao {

    @PersistenceContext(unitName = Constantes.UNIDAD_PERSISTENCIA)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarreraDaoImp() {
        super(Carrera.class);
    }

    @Override
    public boolean buscarRegistroPorFacultad(Facultad iFacultad) {
        boolean respuesta = false;
        Query q = em.createQuery("SELECT c FROM Carrera c WHERE c.idFacultad= :facultad");
        q.setParameter("facultad", iFacultad);
        List<Carrera> lista = q.getResultList();
        if (!lista.isEmpty()) {
            respuesta = true;
        }
        return respuesta;
    }

    @Override
    public List<GraficoEsCarrera> listaGraficoEstudianteCarrera() {
        List<GraficoEsCarrera> listaRespuesta = new ArrayList<GraficoEsCarrera>();
        Query q = em.createQuery("SELECT c.nomCarrera, count(p) FROM Carrera c, Persona p WHERE c.idCarrera=p.idCarrera.idCarrera group by c.nomCarrera order by c.nomCarrera");
        List<Object[]> lista = q.getResultList();
        if (!lista.isEmpty()) {
            for (Object[] items : lista) {
                listaRespuesta.add(new GraficoEsCarrera(items[0].toString(), Integer.parseInt(items[1].toString())));
            }
        }
        return listaRespuesta;
    }

    @Override
    public List<GraficoBarrasCaPersona> listaGraficoEnEsCarrera() {
        List<GraficoBarrasCaPersona> listaRespuesta = new ArrayList<GraficoBarrasCaPersona>();
        Query q = em.createQuery("SELECT c.nomCarrera, count(DISTINCT p) FROM Carrera c, Persona p, Categoria ca, Resultado r, Pregunta pg, Encuesta e\n"
                + "WHERE c.idCarrera=p.idCarrera.idCarrera\n"
                + "and c.idCarrera=e.idCarrera.idCarrera\n"
                + "and e.idEncuesta=ca.idEncuesta.idEncuesta\n"
                + "and ca.idCategoria =pg.idCategoria.idCategoria\n"
                + "and pg.idPregunta=r.idPregunta.idPregunta\n"
                + "and p.idPersona= r.idPersona.idPersona\n"
                + "group by c.nomCarrera order by c.nomCarrera");
        List<Object[]> lista = q.getResultList();
        if (!lista.isEmpty()) {
            for (Object[] items : lista) {
                listaRespuesta.add(new GraficoBarrasCaPersona(items[0].toString(), Integer.parseInt(items[1].toString())));
            }
        }
        return listaRespuesta;
    }

    @Override
    public List<Carrera> buscarCarreraPorFacultad(int idFacultad) {
        Query q = em.createQuery("SELECT c FROM Carrera c WHERE c.idFacultad.idFacultad= :idFacultad");
        q.setParameter("idFacultad", idFacultad);
        return q.getResultList();
    }
}
