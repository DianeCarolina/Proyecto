/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.dao.EncuestaDao;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoAbstractFactory;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoFactory;
import ec.edu.uce.medicina.seguimiento.modelo.Carrera;
import ec.edu.uce.medicina.seguimiento.modelo.Encuesta;
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
public class EncuestaServicioImp implements EncuestaServicio {

    @EJB
    private EncuestaDao encuestaDao = null;

    public EncuestaServicioImp() {
        DaoFactory factoria = DaoAbstractFactory.getInstance();
        this.encuestaDao = factoria.getEncuestaDao();
    }

    @Override
    public List<Encuesta> listarEncuesta() {
        return encuestaDao.findAll();
    }

    @Override
    public Encuesta findEncuestaById(Integer encuestaId) {
        return encuestaDao.find(encuestaId);
    }

    @Override
    public boolean buscarRegistroPorCarrera(Carrera iCarrera) {
        return encuestaDao.buscarRegistroPorCarrera(iCarrera);
    }

    @Override
    public void insertarEncuesta(Encuesta encuesta) {
        encuestaDao.create(encuesta);
    }

    @Override
    public List<SelectItem> oneMenuEncuesta(List<Encuesta> listaEncuesta) {
        List<SelectItem> itemsOneMenu = new ArrayList<SelectItem>();
        for (Encuesta u : listaEncuesta) {
            SelectItem tmp = new SelectItem(u.getIdEncuesta(), u.getTituloEncuesta());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;

    }

    @Override
    public List<Encuesta> listaEncuestaPorCarrera(Integer id) {
        return encuestaDao.listaEncuestaPorCarrera(id);
    }

    @Override
    public Encuesta buscarEncuestaPorCarrera(Integer idCarrera) {
        return encuestaDao.buscarEncuestaPorCarrera(idCarrera);
    }

    @Override
    public void actualizarEncuesta(Encuesta encuesta) {
        encuestaDao.edit(encuesta);
    }

}
