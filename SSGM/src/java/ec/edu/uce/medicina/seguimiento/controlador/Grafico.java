/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.controlador;

import ec.edu.uce.medicina.seguimiento.dto.GraficoEsCarrera;
import ec.edu.uce.medicina.seguimiento.servicio.CarreraServicio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.PieChartModel;

/**
 * Clase que permite generar graficas en el sistema usando primefaces
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@ManagedBean
@ViewScoped
public class Grafico implements Serializable {

    /**
     * Serial version de la clase.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Atributo lista de datos para graficar de graduados
     */
    private List<GraficoEsCarrera> listagraficada;
    /**
     * Atributo de pie model usando primefaces para una grafica pastel
     */
    private PieChartModel pieModel;
    /**
     * Transforma la clase en un java beans para que puede ser gestionada por
     * contenedores ejb
     */
    @EJB
    private CarreraServicio serCarrera;

    /**
     * Constructor por defecto
     */
    public Grafico() {
    }

    /**
     * Inicialización de variables
     */
    @PostConstruct
    private void init() {
        listagraficada = serCarrera.listaGraficoEstudianteCarrera();
        createPieModel();
    }

    /**
     * Metodo para crear el grafico pie model que es un pastel de datos
     */
    private void createPieModel() {
        pieModel = new PieChartModel();
        for (GraficoEsCarrera items : listagraficada) {//recorre la lista de datos de alumnos segun la carrera
            pieModel.set(items.getCarrera(), items.getCantidad());
        }
        pieModel.setTitle("ESTUDIANTES RESGITRADOS POR CARRERA");
        pieModel.setLegendPosition("e");
        pieModel.setFill(false);
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(150);
    }

    /**
     * Devuelve pieModel
     *
     * @return pieModel
     */
    public PieChartModel getPieModel() {
        return pieModel;
    }

    /**
     * Modifica el pieModel
     *
     * @param pieModel
     */
    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

}
