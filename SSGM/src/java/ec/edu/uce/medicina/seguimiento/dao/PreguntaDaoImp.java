/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.dao;

import ec.edu.uce.medicina.seguimiento.dto.PreguntaDTO;
import ec.edu.uce.medicina.seguimiento.fachada.AbstractFacade;
import ec.edu.uce.medicina.seguimiento.modelo.Pregunta;
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
public class PreguntaDaoImp extends AbstractFacade<Pregunta> implements PreguntaDao {

    @PersistenceContext(unitName = Constantes.UNIDAD_PERSISTENCIA)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PreguntaDaoImp() {
        super(Pregunta.class);
    }

    @Override
    public List<PreguntaDTO> listaPreguntaPorCategoriaAdministrador(Integer id) {
        List<PreguntaDTO> preguntas = new ArrayList<>();
        List<Pregunta> listaPregunta = new ArrayList<>();
        Query q = em.createQuery("SELECT p FROM Pregunta p JOIN P.idCategoria as pre WHERE pre.idCategoria= :idCategoria");
        q.setParameter("idCategoria", id);
        listaPregunta = q.getResultList();
        if (!listaPregunta.isEmpty()) {
            for (Pregunta pregunta : listaPregunta) {
                preguntas.add(new PreguntaDTO(pregunta));
            }
        }

        return preguntas;
    }

    @Override
    public List<Pregunta> listaPreguntaPorCategoriaCliente(Integer id) {
        List<Pregunta> listaPregunta = new ArrayList<>();
        Query q = em.createQuery("SELECT p FROM Pregunta p JOIN P.idCategoria as pre WHERE pre.idCategoria= :idCategoria ORDER BY p.orden");
        q.setParameter("idCategoria", id);
        listaPregunta = q.getResultList();

        return listaPregunta;
    }

    @Override
    public Integer codigoPorEncuesta(Integer idEncuesta) {
        Integer codigo=0;
        Query q = em.createQuery("SELECT pg.idPregunta\n"
                + "FROM Encuesta e, Carrera c, Categoria ca, Pregunta pg\n"
                + "Where c.idCarrera= e.idCarrera.idCarrera\n"
                + "and e.idEncuesta=ca.idEncuesta.idEncuesta\n"
                + "and ca.idCategoria=pg.idCategoria.idCategoria\n"
                + "and ((pg.texto='Fecha de la encuesta')OR (pg.texto='Fecha')OR(pg.texto='Fecha de encuesta')OR (pg.texto='FECHA DE LA ENCUESTA'))\n"
                + "and e.idEncuesta= :idEncuesta");
        q.setParameter("idEncuesta", idEncuesta);
        codigo = (Integer) q.getSingleResult();
        return codigo;
    }

}
