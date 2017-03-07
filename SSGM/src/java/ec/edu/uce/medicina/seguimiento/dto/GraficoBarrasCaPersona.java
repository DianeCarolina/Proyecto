/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.dto;

/**
 *
 * @author CAROLINE 
 */
public class GraficoBarrasCaPersona {
    private String carrera;
    private Integer cantidad;

    public GraficoBarrasCaPersona(String carrera, Integer cantidad) {
        this.carrera = carrera;
        this.cantidad = cantidad;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
}
