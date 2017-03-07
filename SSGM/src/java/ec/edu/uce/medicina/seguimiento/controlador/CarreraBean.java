/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.controlador;

import ec.edu.uce.medicina.seguimiento.modelo.Carrera;
import ec.edu.uce.medicina.seguimiento.modelo.Facultad;
import ec.edu.uce.medicina.seguimiento.servicio.CarreraServicio;
import ec.edu.uce.medicina.seguimiento.servicio.EncuestaServicio;
import ec.edu.uce.medicina.seguimiento.servicio.FacultadServicio;
import ec.edu.uce.medicina.seguimiento.servicio.PersonaServicio;
import ec.edu.uce.medicina.seguimiento.servicio.UniversidadServicio;
import ec.edu.uce.medicina.seguimiento.util.Constantes;
import ec.edu.uce.medicina.seguimiento.util.MensajesFaces;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 * Esta clase CarreraBean se encarga de enlazar los atributos con las páginas
 * XHTML conocido como Back beans
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@ViewScoped
@ManagedBean
public class CarreraBean implements Serializable {

    /**
     * Serial version de la clase.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Atributo items de la Universidad
     */
    private List<SelectItem> itemsUniversidad;
    /**
     * Atributo id de la Universidad
     */
    private int idUniversidad;
    /**
     * Atributo items de la Facultad
     */
    private List<SelectItem> itemsFacultad;
    /**
     * Atributo id de la facultad
     */
    private int idFacultad;
    /**
     * Atributo estado de un combo box
     */
    private String estadoComboBox;
    /**
     * Atributo nueva carrera
     */
    private Carrera nuevaCarrera;
    /**
     * Atributo modalidad que tiene una carrera: presencial, semipresencial
     */
    private Map<String, String> modalidad;
    /**
     * Atributo nivel el grado de la carrera
     */
    private Map<String, String> nivel;
    /**
     * Atributo carrera temporal
     */
    private Carrera carreratemp;
    /**
     * Atributo request context
     */
    private RequestContext req;

    /**
     * Transforma la clase en un java beans para que puede ser gestionada por
     * contenedores ejb
     */
    @EJB
    private UniversidadServicio serUniversidad;
    @EJB
    private CarreraServicio serCarrera;
    @EJB
    private FacultadServicio serFacultad;
    @EJB
    private PersonaServicio serPersona;
    @EJB
    private EncuestaServicio serEncuesta;

    /**
     * Constructor por defecto
     */
    public CarreraBean() {
    }

    /**
     * Inicialización de variables
     */
    @PostConstruct
    public void inicializar() {
        this.estadoComboBox = Constantes.INACTIVO; //Asignar en esta variable
        nuevaCarrera = new Carrera();// Instancia de la clase Carrera
        itemsUniversidad = serUniversidad.oneMenuUniversidad(serUniversidad.listarUniversidad());
        nivel = serCarrera.niveles(); //Asignación de variable con el nivel de la carrera
        modalidad = serCarrera.modalidades();//Asignación de variable con modalidades de la carrera
    }

    /**
     * Método que me servirá para cambiar de evento, cuando encuentre la
     * facultad.
     *
     * @param ent AjaxBehaviorEvent representa el comportamiento de los
     * componentes específicos de Ajax
     */
    public void getFacultades(AjaxBehaviorEvent ent) {
        this.estadoComboBox = Constantes.ACTIVO;
        this.itemsFacultad = serFacultad.oneMenuFacultad(this.serFacultad
                .buscarFacultadPorUniversidad(idUniversidad));
    }

    /**
     * Método para ingresar un registro de carrera en la base de datos.
     */
    public void ingresoNuevaCarrera() {
        try {
            Facultad facultadEncontrada = serFacultad.buscarFacultadPorId(idFacultad);
            nuevaCarrera.setIdFacultad(facultadEncontrada);
            serCarrera.insertarCarrera(nuevaCarrera);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dlgCa').hide()"); //cerrar el dialogo después de realizar un evento
            nuevaCarrera = new Carrera();
            estadoComboBox = Constantes.INACTIVO;
            idFacultad = 0;
            idUniversidad = 0;
            req = null;
            MensajesFaces.informacion("GUARDADO CORRECTAMENTE", "");
        } catch (Exception e) {
            MensajesFaces.error("NO SE PUDO GUARDAR", "" + e);
        }

    }

    /**
     * Método para cerrar la ventana de la carrera
     */
    public void cerrar() {
        nuevaCarrera = new Carrera();
        estadoComboBox = Constantes.INACTIVO;
        idFacultad = 0;
        idUniversidad = 0;
    }

    /**
     * Método que recupera datos de la carrera 
     *
     * @return el listado de carreras
     */
    public List<Carrera> recuperarTodos() {
        return serCarrera.listarCarrera();

    }

    /**
     * Método para encontrar la carrera por el Id.
     *
     * @param id int recibirá como parámetro de entrada el Id de la Carrera.
     * @return recuperará el objeto de la carrera.
     */
    public Carrera encontrar(int id) {
        carreratemp = new Carrera();
        carreratemp = serCarrera.buscarCarreraPorId(id);
        idFacultad = carreratemp.getIdFacultad().getIdFacultad();
        return carreratemp;
    }

    /**
     * Método para editar o actualizar el objeto Carrera.
     */
    public void editCarrera() {
        try {
            nuevaCarrera = carreratemp;
            nuevaCarrera.setIdFacultad(serFacultad.buscarFacultadPorId(idFacultad));
            serCarrera.actualizarCarrera(nuevaCarrera);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('carreraDialog1').hide()");
            nuevaCarrera = new Carrera();
            req = null;
            MensajesFaces.informacion("ACTUALIZADO CORRECTAMENTE", "");
        } catch (Exception e) {
            MensajesFaces.advertencia("NO SE PUEDE ACTUALIZAR", "" + e);

        }
    }

    /**
     * Método para eliminar la Carrera.
     *
     * @param carreratemp Carrera, ingresará el objeto encontrado por id de la
     * carrera. Vamos a verificar si existe carrera en persona o encuesta para
     * proceder a la eliminación
     * <ul>
     * <li>true: presenta un mensaje de confirmación de negación</li>
     * <li>false: presenta un mensaje de confirmación afirmativa</li>
     * </ul>
     */
    public void eliminarCarrera(Carrera carreratemp) {
        try {
            boolean verificarCarreraEnPersona = serPersona.buscarRegistroPorCarrera(carreratemp);
            boolean verificarCarreraEnEncuesta = serEncuesta.buscarRegistroPorCarrera(carreratemp);

            if (verificarCarreraEnPersona || verificarCarreraEnEncuesta) {
                MensajesFaces.informacion("NO SE PUEDE ELIMINAR", "");
            } else {
                serCarrera.eliminarCarrera(carreratemp);
                MensajesFaces.informacion("ELIMINADO CORRECTAMENTE", "");
            }
        } catch (Exception e) {
            MensajesFaces.advertencia("ERROR AL ELIMINAR", "" + e);
        }
    }

    /**
     * Métodos públicos. Get y set de los atributos de la clase. para mostrar
     * (get) o modificar (set) el valor de un atributo.
     */
    /**
     * Devuelve el itemsUniversidad
     *
     * @return items de la universidades
     */
    public List<SelectItem> getItemsUniversidad() {
        return itemsUniversidad;
    }

    /**
     * Modifica el item de la universidad
     *
     * @param itemsUniversidad tipo lista.
     */
    public void setItemsUniversidad(List<SelectItem> itemsUniversidad) {
        this.itemsUniversidad = itemsUniversidad;
    }

    /**
     * Devuelve el id de la universidad
     *
     * @return idUniversidad
     */
    public int getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(int idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    /**
     * Devuelve los items de la facultad
     *
     * @return itemsFacultad
     */
    public List<SelectItem> getItemsFacultad() {
        return itemsFacultad;
    }

    /**
     * Modifica el items de la facultad
     *
     * @param itemsFacultad tipo lista .
     */
    public void setItemsFacultad(List<SelectItem> itemsFacultad) {
        this.itemsFacultad = itemsFacultad;
    }

    /**
     * Devuelve el id de la facultad
     *
     * @return idFacultad
     */
    public int getIdFacultad() {
        return idFacultad;
    }

    /**
     * Modifica el id de la facultad
     *
     * @param idFacultad tipo entero
     */
    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }

    /**
     * Devuelve el estado del combo box
     *
     * @return estadoComboBox
     */
    public String getEstadoComboBox() {
        return estadoComboBox;
    }

    /**
     * Modifica el estado del combo box
     *
     * @param estadoComboBox tipo string
     */
    public void setEstadoComboBox(String estadoComboBox) {
        this.estadoComboBox = estadoComboBox;
    }

    /**
     * Devuelve la nueva carrera
     *
     * @return nuevaCarrera
     */
    public Carrera getNuevaCarrera() {
        return nuevaCarrera;
    }

    /**
     * Modifica la nueva carrera
     *
     * @param nuevaCarrera tipo carrera
     */
    public void setNuevaCarrera(Carrera nuevaCarrera) {
        this.nuevaCarrera = nuevaCarrera;
    }

    /**
     * Devuelve la la modalidad de la carrera
     *
     * @return modalidad
     */
    public Map<String, String> getModalidad() {
        return modalidad;
    }

    /**
     * Modifica la modalidad de la carrera
     *
     * @param modalidad tipo HashMap.put(clave,contenido)
     */
    public void setModalidad(Map<String, String> modalidad) {
        this.modalidad = modalidad;
    }

    /**
     * Devuelve el nivel de la carrera
     *
     * @return nivel
     */
    public Map<String, String> getNivel() {
        return nivel;
    }

    /**
     * Modifica la nivel de la carrera
     *
     * @param nivel tipo HashMap.put(clave,contenido)
     */
    public void setNivel(Map<String, String> nivel) {
        this.nivel = nivel;
    }

    /**
     * Devuelve la carrera temporal
     *
     * @return carreratemp
     */
    public Carrera getCarreratemp() {
        return carreratemp;
    }

    /**
     * Modifica la nueva carrera temporal
     *
     * @param carreratemp tipo carrera
     */
    public void setCarreratemp(Carrera carreratemp) {
        this.carreratemp = carreratemp;
    }

}
