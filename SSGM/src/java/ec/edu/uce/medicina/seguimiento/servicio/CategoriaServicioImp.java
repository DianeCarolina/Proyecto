/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.dao.CategoriaDao;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoAbstractFactory;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoFactory;
import ec.edu.uce.medicina.seguimiento.modelo.Categoria;
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
public class CategoriaServicioImp implements CategoriaServicio {

    @EJB
    private CategoriaDao categoriaDao = null;

    public CategoriaServicioImp() {
        DaoFactory factoria = DaoAbstractFactory.getInstance();
        this.categoriaDao = factoria.getCtegoriaDao();
    }

    @Override
    public void insertarCategoria(Categoria categoria) {
        categoriaDao.create(categoria);
    }

    @Override
    public void eliminarCategoria(Categoria categoria) {
        categoriaDao.remove(categoria);
    }

    @Override
    public void actualizarCategoria(Categoria categoria) {
        categoriaDao.edit(categoria);
    }

    @Override
    public List<Categoria> listarCategoria() {
        return categoriaDao.findAll();
    }

    @Override
    public List<SelectItem> oneMenuCategoria(List<Categoria> listaCategoria) {
        List<SelectItem> itemsOneMenu = new ArrayList<SelectItem>();
        for (Categoria u : listaCategoria) {
            SelectItem tmp = new SelectItem(u.getIdCategoria(), u.getNomCategoria());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;

    }

    @Override
    public Categoria buscarCategoriaPorId(Object id) {
        return categoriaDao.find(id);
    }

    @Override
    public List<Categoria> buscarCategoriaPorEncuesta(int idEncuesta) {
        return categoriaDao.buscarCategoriaPorEncuesta(idEncuesta);
    }

    @Override
    public List<Categoria> listaCategoriaPorEncuesta(Integer id) {
        return categoriaDao.listaCategoriaPorEncuesta(id);
    }
}
