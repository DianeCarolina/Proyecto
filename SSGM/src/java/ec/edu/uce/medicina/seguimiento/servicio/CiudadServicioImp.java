/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.dao.CiudadDao;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoAbstractFactory;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoFactory;
import ec.edu.uce.medicina.seguimiento.modelo.Ciudad;
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
public class CiudadServicioImp implements CiudadServicio {

    @EJB
    private CiudadDao ciudadDao = null;

    public CiudadServicioImp() {
        DaoFactory factoria = DaoAbstractFactory.getInstance();
        this.ciudadDao = factoria.getCiudadDao();
    }

    @Override
    public List<Ciudad> listarCiudad() {
        return ciudadDao.findAll();
    }

    @Override
    public Ciudad buscarCiudadPorId(Object id) {
        return ciudadDao.find(id);
    }

    @Override
    public List<SelectItem> oneMenuCiudad(List<Ciudad> listaCiudad) {
        List<SelectItem> itemsOneMenu = new ArrayList<SelectItem>();
        for (Ciudad u : listaCiudad) {
            SelectItem tmp = new SelectItem(u.getIdCiudad(), u.getNomCiudad());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;
    }

    @Override
    public List<Ciudad> buscarCiudadesPorProvincia(int idProvincia) {
        return ciudadDao.buscarCiudadesPorProvincia(idProvincia);
    }

}
