/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.modelo.TipoPregunta;
import java.util.List;
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
public interface TipoPreguntaServicio {

    void insertarTipoPregunta(TipoPregunta tipoPregunta);

    void actualizarTipoPregunta(TipoPregunta tipoPregunta);

    List<TipoPregunta> listarTipoPregunta();

    List<SelectItem> oneMenuTipoPregunta(List<TipoPregunta> listaTipoPregunta);

    TipoPregunta buscarTipoPreguntaPorId(Object id);

}
