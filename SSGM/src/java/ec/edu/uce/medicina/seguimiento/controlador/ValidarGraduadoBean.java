/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.controlador;

import com.cursojsf.validadores.Cedula;
import ec.edu.uce.medicina.seguimiento.modelo.Persona;
import ec.edu.uce.medicina.seguimiento.servicio.PersonaServicio;
import ec.edu.uce.medicina.seguimiento.util.Constantes;
import ec.edu.uce.medicina.seguimiento.util.MensajesFaces;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Esta clase EstudianteBean se encarga de enlazar los atributos con las páginas
 * XHTML en la validación del graduado
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@ManagedBean
@ApplicationScoped
public class ValidarGraduadoBean implements Serializable {

    /**
     * Serial version de la clase.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Atributo numero de cedula del estudiante
     */
    @Cedula(message = "CÉDULA INCORRECTA")
    private String numeroCedulaValidar;
    /**
     * Atributo numero de cedula del estudiante
     */
    @Cedula(message = "CÉDULA INCORRECTA")
    private String numeroCedula;
    /**
     * Atributo persona encontrada
     */
    private Persona personaEncontrada;
    /**
     * Transformara la clase en un java beans que puede ser gestionada por
     * contenedores ejb
     */
    @EJB
    private PersonaServicio serPersona;

    /**
     * Constructor por defecto
     */
    public ValidarGraduadoBean() {
    }

    /**
     * Inicialización de variables
     */
    @PostConstruct
    private void inicializar() {
        numeroCedulaValidar = Constantes.VACIO;
        numeroCedula = Constantes.VACIO;
    }

    /**
     * Método para validar Persona
     *
     * @return navegarPagina
     */
    public String validarPersona() {
        String navegarPagina;
        personaEncontrada = serPersona.buscarPersonaPorCedula(numeroCedulaValidar);
        if (personaEncontrada != null) {
            MensajesFaces.informacion("PERSONA", "ENCONTRADA");
        } else {
            numeroCedula = numeroCedulaValidar;
            personaEncontrada = new Persona();
            MensajesFaces.informacion("PERSONA", "NO ENCONTRADA");
        }
        numeroCedulaValidar = Constantes.VACIO;
        navegarPagina = "/paginas/pagCliente/registro_graduado.jsf";
        return navegarPagina;
    }

    /**
     * Devuelve numeroCedula
     *
     * @return numeroCedula
     */
    public String getNumeroCedula() {
        return numeroCedula;
    }

    /**
     * Modifica numeroCedula
     *
     * @param numeroCedula tipo String
     */
    public void setNumeroCedula(String numeroCedula) {
        this.numeroCedula = numeroCedula;
    }

    /**
     * Devuelve personaEncontrada
     *
     * @return personaEncontrada
     */
    public Persona getPersonaEncontrada() {
        return personaEncontrada;
    }

    /**
     * Modifica personaEncontrada
     *
     * @param personaEncontrada tipo Persona
     */
    public void setPersonaEncontrada(Persona personaEncontrada) {
        this.personaEncontrada = personaEncontrada;
    }

    /**
     * Devuelve numeroCedulaValidar
     *
     * @return numeroCedulaValidar
     */
    public String getNumeroCedulaValidar() {
        return numeroCedulaValidar;
    }

    /**
     * Modifica numeroCedulaValidar
     *
     * @param numeroCedulaValidar tipo String
     */
    public void setNumeroCedulaValidar(String numeroCedulaValidar) {
        this.numeroCedulaValidar = numeroCedulaValidar;
    }

}
