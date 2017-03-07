/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.dao.PreguntaDao;
import ec.edu.uce.medicina.seguimiento.dto.PreguntaDTO;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoAbstractFactory;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoFactory;
import ec.edu.uce.medicina.seguimiento.modelo.Pregunta;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;

/**
 * <b>
 * Descripcion de la clase.
 * </b>
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@Stateless
public class PreguntaServicioImp implements PreguntaServicio {

    @EJB
    private PreguntaDao preguntaDao = null;

    public PreguntaServicioImp() {
        DaoFactory factoria = DaoAbstractFactory.getInstance();
        this.preguntaDao = factoria.getPreguntaDao();
    }

    @Override
    public void insertarPregunta(Pregunta pregunta) {
        preguntaDao.create(pregunta);
    }

    @Override
    public void eliminarPregunta(Pregunta pregunta) {
        preguntaDao.remove(pregunta);
    }

    @Override
    public void actualizarPregunta(Pregunta pregunta) {
        preguntaDao.edit(pregunta);
    }

    @Override
    public List<Pregunta> listarPregunta() {
        return preguntaDao.findAll();
    }

    @Override
    public List<SelectItem> oneMenuPregunta(List<Pregunta> listaPregunta) {
        List<SelectItem> itemsOneMenu = new ArrayList<SelectItem>();
        for (Pregunta u : listaPregunta) {
            SelectItem tmp = new SelectItem(u.getIdPregunta(), u.getDescripcion());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;

    }

    @Override
    public Pregunta buscarPreguntaPorId(Object id) {
        return preguntaDao.find(id);
    }

    @Override
    public List<PreguntaDTO> listaPreguntaPorCategoriaAdministrador(Integer id) {
        return preguntaDao.listaPreguntaPorCategoriaAdministrador(id);
    }

    @Override
    public List<Pregunta> listaPreguntaPorCategoriaCliente(Integer id) {
        return preguntaDao.listaPreguntaPorCategoriaCliente(id);
    }

    @Override
    public Integer codigoPorEncuesta(Integer idEncuesta) {
        return preguntaDao.codigoPorEncuesta(idEncuesta);
    }

}
