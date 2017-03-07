/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.dao.TipoPreguntaDao;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoAbstractFactory;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoFactory;
import ec.edu.uce.medicina.seguimiento.modelo.TipoPregunta;
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
public class TipoPreguntaServicioImp implements TipoPreguntaServicio {

    @EJB
    private TipoPreguntaDao tipoPreguntaDao = null;

    public TipoPreguntaServicioImp() {
        DaoFactory factoria = DaoAbstractFactory.getInstance();
        this.tipoPreguntaDao = factoria.getTipoPreguntaDao();
    }

    @Override
    public void insertarTipoPregunta(TipoPregunta tipoPregunta) {
        tipoPreguntaDao.create(tipoPregunta);
    }

    @Override
    public void actualizarTipoPregunta(TipoPregunta tipoPregunta) {
        tipoPreguntaDao.edit(tipoPregunta);
    }

    @Override
    public List<TipoPregunta> listarTipoPregunta() {
        return tipoPreguntaDao.findAll();
    }

    @Override
    public List<SelectItem> oneMenuTipoPregunta(List<TipoPregunta> listaTipoPregunta) {
        List<SelectItem> itemsOneMenu = new ArrayList<SelectItem>();
        for (TipoPregunta u : listaTipoPregunta) {
            SelectItem tmp = new SelectItem(u.getIdTipopregunta(), u.getNomTipopregunta());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;

    }

    @Override
    public TipoPregunta buscarTipoPreguntaPorId(Object id) {
        return tipoPreguntaDao.find(id);
    }
}
