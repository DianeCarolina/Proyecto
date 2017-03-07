/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.dao;

import ec.edu.uce.medicina.seguimiento.modelo.Facultad;
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
public interface FacultadDao {

    void create(Facultad facultad);

    void edit(Facultad facultad);

    void remove(Facultad facultad);

    Facultad find(Object id);

    List<Facultad> findAll();

    List<Facultad> findRange(int[] range);

    int count();

    List<Facultad> buscarFacultadPorUniversidad(int idUniversidad);
    
    boolean buscarRegistroPorUniveridad(Universidad iUniversidad);
}
