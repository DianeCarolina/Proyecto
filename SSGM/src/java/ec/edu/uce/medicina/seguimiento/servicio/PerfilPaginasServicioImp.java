/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.dao.PerfilPaginasDao;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoAbstractFactory;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoFactory;
import ec.edu.uce.medicina.seguimiento.modelo.PerfilPaginas;
import javax.ejb.EJB;
import javax.ejb.Stateless;

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
public class PerfilPaginasServicioImp implements PerfilPaginasServicio {

    @EJB
    private PerfilPaginasDao perfilPaginasDao = null;

    public PerfilPaginasServicioImp() {
        DaoFactory factoria = DaoAbstractFactory.getInstance();
        this.perfilPaginasDao = factoria.getPerfilPaginasDao();
    }

    @Override
    public void insertarPerfilPaginas(PerfilPaginas perfilPaginas) {
        perfilPaginasDao.create(perfilPaginas);
    }

    @Override
    public void eliminarPerfilPaginas(PerfilPaginas perfilPaginas) {
        perfilPaginasDao.remove(perfilPaginas);
    }

    @Override
    public void actualizarPerfilPaginas(PerfilPaginas perfilPaginas) {
        perfilPaginasDao.edit(perfilPaginas);
    }

}
