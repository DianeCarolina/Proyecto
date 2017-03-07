/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.dao;

import ec.edu.uce.medicina.seguimiento.modelo.Universidad;
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
public interface UniversidadDao {

    void create(Universidad universidad);

    void edit(Universidad universidad);

    void remove(Universidad universidad);

    Universidad find(Object id);

    List<Universidad> findAll();

    List<Universidad> findRange(int[] range);

    int count();
    
}
