/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.dao.PerfilDao;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoAbstractFactory;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoFactory;
import ec.edu.uce.medicina.seguimiento.modelo.Perfil;
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
public class PerfilServicioImp implements PerfilServicio {

    @EJB
    private PerfilDao perfilDao = null;

    public PerfilServicioImp() {
        DaoFactory factoria = DaoAbstractFactory.getInstance();
        this.perfilDao = factoria.getPerfilDao();

    }

    @Override
    public void insertarPerfil(Perfil perfil) {
        perfilDao.create(perfil);
    }

    @Override
    public void eliminarPerfil(Perfil perfil) {
        perfilDao.remove(perfil);
    }

    @Override
    public void actualizarPerfil(Perfil perfil) {
        perfilDao.edit(perfil);
    }

    @Override
    public List<Perfil> listarPerfil() {
        return perfilDao.findAll();
    }

    @Override
    public List<SelectItem> oneMenuPerfil(List<Perfil> listaPerfil) {
        List<SelectItem> itemsOneMenu = new ArrayList<SelectItem>();
        for (Perfil u : listaPerfil) {
            SelectItem tmp = new SelectItem(u.getIdPerfil(), u.getTipoPerfil());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;

    }

    @Override
    public Perfil buscarPerfilPorId(Object id) {
        return perfilDao.find(id);
    }
}
