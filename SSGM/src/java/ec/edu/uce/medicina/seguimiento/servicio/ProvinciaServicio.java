/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import java.util.List;
import ec.edu.uce.medicina.seguimiento.modelo.Provincia;
import javax.ejb.Local;
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
@Local
public interface ProvinciaServicio {

    void insertarProvincia(Provincia provincia);

    void eliminarProvincia(Provincia provincia);

    void actualizarProvincia(Provincia provincia);

    List<Provincia> listarProvincia();

    List<SelectItem> oneMenuProvincia(List<Provincia> listaProvincia);

    List<Provincia> buscarProvinciasPorPais(int idPais);

}
