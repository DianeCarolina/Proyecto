/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.controlador;

import ec.edu.uce.medicina.seguimiento.dto.GraficoBarrasCaPersona;
import ec.edu.uce.medicina.seguimiento.dto.GraficoEsCarrera;
import ec.edu.uce.medicina.seguimiento.servicio.CarreraServicio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 * Clase que permite generar graficas en el sistema usando primefaces
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@ManagedBean
@ViewScoped
public class GraficoBarrasEnEsCarreraBean implements Serializable {

    /**
     * Serial version de la clase.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Atributo lista de datos para graficar de graduados
     */
    private List<GraficoEsCarrera> listaGraficada;
    /**
     * Atributo lista de datos para graficar graduados que han llenado la
     * encuesta
     */
    private List<GraficoBarrasCaPersona> listaEncuestaGraficada;
    /**
     * Atributo modelo animado de tipo barchartModel
     */
    private BarChartModel animatedModel;
    /**
     * Transforma la clase en un java beans para que puede ser gestionada por
     * contenedores ejb
     */
    @EJB
    private CarreraServicio serCarrera;

    /**
     * Constructor por defecto
     */
    public GraficoBarrasEnEsCarreraBean() {
    }

    /**
     * Inicialización de variables
     */
    @PostConstruct
    private void init() {
        listaGraficada = serCarrera.listaGraficoEstudianteCarrera();
        listaEncuestaGraficada = serCarrera.listaGraficoEnEsCarrera();
        createAnimatedModels();
    }

    /**
     * Metodo para crear el grafico con un modelo animado de datos
     */
    private void createAnimatedModels() {
        animatedModel = initBarModel();
        animatedModel.setTitle("ESTUDIANTES ENCUESTADOS POR CARRERA");
        animatedModel.setAnimate(true);
        animatedModel.setLegendPosition("NÚMERO DE ESTUDIANTES");
        Axis xAxis = animatedModel.getAxis(AxisType.X);
        xAxis.setLabel("CARRERAS");
        Axis yAxis = animatedModel.getAxis(AxisType.Y);
        yAxis.setLabel("NÚMERO DE GRADUADOS");
        yAxis.setMin(0);
        yAxis.setMax(1500);
    }

    /**
     * Metodo para inicializa los datos para la generacion del grafico en barras
     */
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        ChartSeries carrera = new ChartSeries();
        ChartSeries estudiante = new ChartSeries();
        for (GraficoEsCarrera items : listaGraficada) {
            carrera.setLabel("TOTAL DE GRADUADOS");
            carrera.set(items.getCarrera(), items.getCantidad());
        }
        for (GraficoBarrasCaPersona items : listaEncuestaGraficada) {
            estudiante.setLabel("GRADUADOS ENCUESTADOS");
            estudiante.set(items.getCarrera(), items.getCantidad());
        }
        model.addSeries(carrera);
        model.addSeries(estudiante);

        return model;
    }

    /**
     * Devuelve animatedModel
     *
     * @return animatedModel
     */
    public BarChartModel getAnimatedModel() {
        return animatedModel;
    }

    /**
     * Modifica el animatedModel
     *
     * @param animatedModel
     */
    public void setAnimatedModel(BarChartModel animatedModel) {
        this.animatedModel = animatedModel;
    }

}
