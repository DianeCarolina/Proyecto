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
public class GraficoEsCarrera {
    private String carrera;
    private int cantidad;

    public GraficoEsCarrera(String carrera, int cantidad) {
        this.carrera = carrera;
        this.cantidad = cantidad;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
