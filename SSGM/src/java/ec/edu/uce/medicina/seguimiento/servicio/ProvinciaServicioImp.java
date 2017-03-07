/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.dao.ProvinciaDao;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoAbstractFactory;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoFactory;
import ec.edu.uce.medicina.seguimiento.modelo.Provincia;
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
public class ProvinciaServicioImp implements ProvinciaServicio {

    @EJB
    private ProvinciaDao provinciaDao = null;

    public ProvinciaServicioImp() {
        DaoFactory factoria = DaoAbstractFactory.getInstance();
        this.provinciaDao = factoria.getProvinciaDao();
    }

    @Override
    public void insertarProvincia(Provincia provincia) {
        provinciaDao.create(provincia);
    }

    @Override
    public void eliminarProvincia(Provincia provincia) {
        provinciaDao.remove(provincia);
    }

    @Override
    public void actualizarProvincia(Provincia provincia) {
        provinciaDao.edit(provincia);
    }

    @Override
    public List<Provincia> listarProvincia() {
        return provinciaDao.findAll();
    }

    @Override
    public List<SelectItem> oneMenuProvincia(List<Provincia> listaProvincia) {
        List<SelectItem> itemsOneMenu = new ArrayList<>();
        for (Provincia u : listaProvincia) {
            SelectItem tmp = new SelectItem(u.getIdProvincia(), u.getNomProvincia());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;
    }

    @Override
    public List<Provincia> buscarProvinciasPorPais(int idPais) {
        return provinciaDao.buscarProvinciasPorPais(idPais);
    }

}
