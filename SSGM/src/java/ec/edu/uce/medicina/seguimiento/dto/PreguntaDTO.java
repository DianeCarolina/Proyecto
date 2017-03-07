/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.dto;

import ec.edu.uce.medicina.seguimiento.modelo.Pregunta;
import ec.edu.uce.medicina.seguimiento.util.Constantes;

/**
 *
 * @author CAROLINE
 */
public class PreguntaDTO {
    private Pregunta pregunta;
    private String estado;

    public PreguntaDTO(Pregunta pregunta) {
        this.pregunta = pregunta;
        if (this.pregunta.getIdTipopregunta().getNomTipopregunta().equals("OPCIÓN MÚLTIPLE")||this.pregunta.getIdTipopregunta().getNomTipopregunta().equals("CASILLAS DE VERIFICACIÓN")||this.pregunta.getIdTipopregunta().getNomTipopregunta().equals("OPCIÓN MÚLTIPLE (LISTADO)")) {
            estado=Constantes.ACTIVO;
        } else {
            estado=Constantes.INACTIVO;
        }
    }
    
    
    //get y set 

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
