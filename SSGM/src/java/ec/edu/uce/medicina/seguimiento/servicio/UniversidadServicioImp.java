/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.dao.UniversidadDao;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoAbstractFactory;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoFactory;
import ec.edu.uce.medicina.seguimiento.modelo.Universidad;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class UniversidadServicioImp implements UniversidadServicio {

    private Map<String, String> tipoUniversidad;

    @EJB
    private UniversidadDao universidadDao = null;

    public UniversidadServicioImp() {
        DaoFactory factoria = DaoAbstractFactory.getInstance();
        this.universidadDao = factoria.getUniversidadDao();
    }

    @Override
    public void insertarUniversidad(Universidad universidad) {
        universidadDao.create(universidad);
    }

    @Override
    public void eliminarUniversidad(Universidad universidad) {
        universidadDao.remove(universidad);
    }

    @Override
    public void actualizarUniversidad(Universidad universidad) {
        universidadDao.edit(universidad);
    }

    @Override
    public List<Universidad> listarUniversidad() {
        return universidadDao.findAll();
    }

    @Override
    public Universidad buscarUniversidadPorId(Object id) {
        return universidadDao.find(id);
    }

    public Map<String, String> tipoUniversidades() {
        tipoUniversidad = new HashMap<>();
        tipoUniversidad.put("INSTITUCIÓN PRIVADA", "INSTITUCIÓN PRIVADA");
        tipoUniversidad.put("INSTITUCIÓN ESTATAL", "INSTITUCIÓN ESTATAL");
        return tipoUniversidad;
    }

    @Override
    public List<SelectItem> oneMenuUniversidad(List<Universidad> listaUniversidad) {

        List<SelectItem> itemsOneMenu = new ArrayList<SelectItem>();
        for (Universidad u : listaUniversidad) {
            SelectItem tmp = new SelectItem(u.getIdUniversidad(), u.getNomUniversidad());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;

    }

}
