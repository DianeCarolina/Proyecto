/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.dao;

import ec.edu.uce.medicina.seguimiento.modelo.PerfilPaginas;
import java.util.List;
import javax.ejb.Local;

/**
 * <b>
 * Descripción de la clase.
 * </b>
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@Local
public interface PerfilPaginasDao {

    void create(PerfilPaginas perfilPaginas);

    void edit(PerfilPaginas perfilPaginas);

    void remove(PerfilPaginas perfilPaginas);

    PerfilPaginas find(Object id);

    List<PerfilPaginas> findAll();

    List<PerfilPaginas> findRange(int[] range);

    int count();
    
}
