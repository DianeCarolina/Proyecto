/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.dao;

import ec.edu.uce.medicina.seguimiento.modelo.Paginas;
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
public interface PaginasDao {

    void create(Paginas paginas);

    void edit(Paginas paginas);

    void remove(Paginas paginas);

    Paginas find(Object id);

    List<Paginas> findAll();

    List<Paginas> findRange(int[] range);

    int count();
    
}
