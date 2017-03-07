/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.controlador;

import ec.edu.uce.medicina.seguimiento.dto.PreguntaDTO;
import ec.edu.uce.medicina.seguimiento.modelo.Carrera;
import ec.edu.uce.medicina.seguimiento.modelo.Categoria;
import ec.edu.uce.medicina.seguimiento.modelo.Encuesta;
import ec.edu.uce.medicina.seguimiento.modelo.Pregunta;
import ec.edu.uce.medicina.seguimiento.modelo.Respuesta;
import ec.edu.uce.medicina.seguimiento.modelo.TipoPregunta;
import ec.edu.uce.medicina.seguimiento.servicio.CarreraServicio;
import ec.edu.uce.medicina.seguimiento.servicio.CategoriaServicio;
import ec.edu.uce.medicina.seguimiento.servicio.EncuestaServicio;
import ec.edu.uce.medicina.seguimiento.servicio.PreguntaServicio;
import ec.edu.uce.medicina.seguimiento.servicio.RespuestaServicio;
import ec.edu.uce.medicina.seguimiento.servicio.TipoPreguntaServicio;
import ec.edu.uce.medicina.seguimiento.util.AplicacionUtil;
import ec.edu.uce.medicina.seguimiento.util.Constantes;
import ec.edu.uce.medicina.seguimiento.util.MensajesFaces;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UISelectItem;
import javax.faces.component.html.HtmlSelectManyCheckbox;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 * Esta clase CuestionarioBean se encarga de enlazar los atributos con las
 * páginas XHTML en la visualización de la encuesta, por parte del administrador
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@ViewScoped
@ManagedBean
public class CuestionarioBean implements Serializable {

    /**
     * Serial version de la clase.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Atributo encuesta de objeto encuesta
     */
    private Encuesta encuesta;
    /**
     * Variable temporal de encuesta para la encuesta
     */
    private Encuesta encuestatemp;
    /**
     * Atributo categoria de la encuesta
     */
    private Categoria categoria;
    /**
     * Variable temporal categoria de la encuesta
     */
    private Categoria categoriatemp;
    /**
     * Atributo carrera de objeto carrera
     */
    private Carrera carrera;
    /**
     * Atributo pregunta de la encuesta
     */
    private Pregunta pregunta;
    /**
     * Atributo temporal de pregunta de la encuesta
     */
    private Pregunta preguntatemp;
    /**
     * Atributo respuesta de tipo respuesta de la encuesta
     */
    private Respuesta respuesta;
    /**
     * Atributo temporal respuesta de la encuesta
     */
    private Respuesta respuestatemp;
    /**
     * Atributo items de la carrera que se encuentran registrados en el sistema
     */
    private List<SelectItem> itemsCarrera;
    /**
     * Atributo lista de tipo de pregunta de la encuesta
     */
    private List<SelectItem> itemsTipoPregunta;
    /**
     * Atributo id de la Carrera
     */
    private int idCarrera;
    /**
     * Atributo id tipo pregunta
     */
    private int idTipoPregunta;
    /**
     * Atributo id Respuesta
     */
    private int idRespuesta;
    /**
     * Atributo id de la encuesta
     */
    private int nuevaIdEncuesta;
    /**
     * Atributo temporal de id carrera
     */
    private int nuevaIdCarrera;
    /**
     * Atributo id categoria
     */
    private int nuevaIdCategoria;
    /**
     * Atributo id pregunta
     */
    private int nuevaIdPregunta;
    /**
     * Atributo temporal de id tipo pregunta
     */
    private int nuevaIdTP;
    /**
     * Atributo para listar las categorias que existen en una encuesta
     */
    private List<Categoria> listaCategorias;
    /**
     * Atributo para listar las preguntas que existen en una encuesta
     */
    private List<Pregunta> listaPreguntas;
    /**
     * Atributo request context
     */
    private RequestContext req;
    /**
     * Atributo para listar las respuestas que existen en una encuesta
     */
    private List<Respuesta> listaRespuestas;
    /**
     * Variables JSF que permitirán generar la encuesta
     */
    private PanelGrid panelGrid;
    private OutputLabel outputLabel;
    private Encuesta encuestaActiva;
    private InputText imInputText;
    private PanelGrid panelGridHijo;
    private InputTextarea inputTextarea;
    private UISelectItem uISelectItem;
    private HtmlSelectManyCheckbox selectManyCheckbox;
    private HtmlSelectOneRadio selectOneRadio;
    private String estadoBtnVisualizar;
    private Calendar calendar;
    private HtmlSelectOneMenu selectOneMenu;
    //------------------------
    /**
     * Transformara la clase en un java beans que puede ser gestionada por
     * contenedores ejb
     */
    @EJB
    private EncuestaServicio serEncuesta;
    @EJB
    private CarreraServicio serCarrera;
    @EJB
    private CategoriaServicio serCategoria;
    @EJB
    private PreguntaServicio serPregunta;
    @EJB
    private TipoPreguntaServicio serTipoPregunta;
    @EJB
    private RespuestaServicio serRespuesta;

    /**
     * Constructor por defecto
     */
    public CuestionarioBean() {
    }

    /**
     * Inicialización de variables
     */
    @PostConstruct
    public void inicializar() {
        encuesta = new Encuesta(); // Instancia de la clase Encuesta
        categoria = new Categoria();// Instancia de la clase Categoria
        carrera = new Carrera();// Instancia de la clase Carrera
        encuestatemp = new Encuesta();// Instancia de la clase Encuesta
        categoriatemp = new Categoria();// Instancia de la clase Categoria
        pregunta = new Pregunta();// Instancia de la clase Pregunta
        preguntatemp = new Pregunta();// Instancia de la clase Pregunta
        respuesta = new Respuesta();// Instancia de la clase Respuesta
        respuestatemp = new Respuesta();// Instancia de la clase Respuesta
        itemsCarrera = serCarrera.oneMenuCarrera(serCarrera.listarCarrera());// Lista la Carrera
        itemsTipoPregunta = serTipoPregunta.oneMenuTipoPregunta(serTipoPregunta.listarTipoPregunta());//Lista el Tipo de Pregunta que tiene la encuesta
        //inicializacion de variables de tipo entero
        idCarrera = 0;
        nuevaIdEncuesta = 0;
        nuevaIdCarrera = 0;
        nuevaIdCategoria = 0;
        idRespuesta = 0;
        //Inicialización de variable que cambiara de estado de false a true
        estadoBtnVisualizar = Constantes.INACTIVO;
    }

    /**
     * Método para crear la encuesta
     */
    public void crearEncuesta() {
        try {
            encuesta.setIdCarrera(serCarrera.buscarCarreraPorId(nuevaIdCarrera));
            serEncuesta.insertarEncuesta(encuesta);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('encDialog').hide()");
            encuesta = new Encuesta();
            req = null;
            MensajesFaces.informacion("ENCUESTA CREADA", "");
        } catch (Exception e) {
            MensajesFaces.error("NO SE PUDO CREAR", "" + e);
        }
    }

    /**
     * Método para cerrar la ventana del cuestionario
     */
    public void cerrar() {
        encuesta = new Encuesta();
        categoria = new Categoria();
        pregunta = new Pregunta();
        respuesta = new Respuesta();
    }

    /**
     * Método para crear la categoria de la encuesta
     */
    public void crearCategoria() {
        try {
            categoria.setIdEncuesta(serEncuesta.findEncuestaById(nuevaIdEncuesta));
            serCategoria.insertarCategoria(categoria);
            categoria = new Categoria();
            MensajesFaces.informacion("CATEGORÍA AGREGADA", "");
        } catch (Exception e) {
            MensajesFaces.error("NO SE PUDO AGREGAR", "" + e);
        }
    }

    /**
     * Método para crear la pregunta de la encuesta
     */
    public void crearPregunta() {
        try {
            pregunta.setIdCategoria(serCategoria.buscarCategoriaPorId(nuevaIdCategoria));
            pregunta.setIdTipopregunta(serTipoPregunta.buscarTipoPreguntaPorId(nuevaIdTP));
            serPregunta.insertarPregunta(pregunta);
            pregunta = new Pregunta();
            nuevaIdTP = 0;
            MensajesFaces.informacion("PREGUNTA AGREGADA", "");
        } catch (Exception e) {
            MensajesFaces.informacion("NO SE PUDO AGREGAR", "" + e);
        }
    }

    /**
     * Método para crear la respuesta de la pregunta
     */
    public void crearRespuesta() {
        try {
            respuesta.setIdPregunta(serPregunta.buscarPreguntaPorId(nuevaIdPregunta));
            serRespuesta.insertarRespuesta(respuesta);
            respuesta = new Respuesta();
            MensajesFaces.informacion("RESPUESTA AGREGADA", "");
        } catch (Exception e) {
            MensajesFaces.informacion("NO SE PUDO AGREGAR", "" + e);
        }
    }

    /**
     * Método para obtener la lista de las carreras existentes en el sistema
     *
     * @param ent AjaxBehaviorEvent representa el comportamiento de los
     * componentes específicos de Ajax
     */
    public void getLista(AjaxBehaviorEvent ent) {
        carrera = serCarrera.buscarCarreraPorId(idCarrera);
        if (carrera != null) {
            nuevaIdCarrera = idCarrera;
            MensajesFaces.informacion("CARRERA ENCUESTADA", "");
        } else {
            idCarrera = 0;
            nuevaIdCarrera = 0;
        }
    }

    /**
     * Método para recuperar la encuesta por id
     *
     * @param id tipo entero
     */
    public void recuperarEncuestaPorId(int id) {
        try {
            encuestatemp = new Encuesta();
            encuestatemp = encuesta;
            encuestatemp = serEncuesta.findEncuestaById(id);
            nuevaIdEncuesta = id;
            idCarrera = 0;
            MensajesFaces.informacion("ENCUESTA", "");
        } catch (Exception e) {
            MensajesFaces.informacion("NO SE ENCONTRÓ", "" + e);
        }
    }

    /**
     * Método para recuperar la categoria por id
     *
     * @param id tipo entero
     */
    public void recuperarCategoriaPorId(int id) {
        try {
            categoriatemp = new Categoria();
            categoriatemp = categoria;
            categoriatemp = serCategoria.buscarCategoriaPorId(id);
            nuevaIdCategoria = categoriatemp.getIdCategoria();//*
            MensajesFaces.informacion("CATEGORÍA", "");
        } catch (Exception e) {
            MensajesFaces.informacion("NO SE ENCONTRÓ", "" + e);
        }
    }

    /**
     * Método para recuperar la pregunta por id
     *
     * @param id tipo entero
     */
    public void recuperarPreguntaPorId(int id) {
        try {
            preguntatemp = new Pregunta();
            preguntatemp = pregunta;
            preguntatemp = serPregunta.buscarPreguntaPorId(id);
            nuevaIdPregunta = id;
            idTipoPregunta = preguntatemp.getIdTipopregunta().getIdTipopregunta();
            MensajesFaces.informacion("PREGUNTA", "");
        } catch (Exception e) {
            MensajesFaces.informacion("NO SE ENCONTRÓ", "" + e);
        }
    }

    /**
     * Método para recuperar la respuesta por id
     *
     * @param id tipo entero
     */
    public void recuperarRespuestaPorId(int id) {
        try {
            respuestatemp = new Respuesta();
            respuestatemp = respuesta;
            respuestatemp = serRespuesta.buscarPorId(id);
            idRespuesta = id;
            MensajesFaces.informacion("RESPUESTA", "");
        } catch (Exception e) {
            MensajesFaces.informacion("NO SE ENCONTRÓ", "" + e);
        }
    }

    /**
     * Método para actualizar la encuesta por carrera
     */
    public void actualizarEncuesta() {
        try {
            encuesta = encuestatemp;
            idCarrera = encuesta.getIdCarrera().getIdCarrera();
            encuesta.setIdCarrera(serCarrera.buscarCarreraPorId(idCarrera));
            serEncuesta.actualizarEncuesta(encuesta);
            encuesta = new Encuesta();
            MensajesFaces.informacion("ACTUALIZADO CORRECTAMENTE", "");
        } catch (Exception e) {
            MensajesFaces.error("NO SE PUEDE ACTUALIZAR", "" + e);
        }
    }

    /**
     * Método para actualizar la categoria por encuesta
     */
    public void actualizarCategoria() {
        try {
            categoria = categoriatemp;
            categoria.setIdEncuesta(serEncuesta.findEncuestaById(nuevaIdEncuesta));
            serCategoria.actualizarCategoria(categoria);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('acatDialog').hide()"); // Permite cerrar un dialigo después de realizar un evento
            categoria = new Categoria();
            req = null;
            MensajesFaces.informacion("ACTUALIZADO CORRECTAMENTE", "");
        } catch (Exception e) {
            MensajesFaces.error("NO SE PUEDE ACTUALIZAR", "" + e);
        }
    }

    /**
     * Método para actualizar la pregunta por categoria
     */
    public void actualizarPregunta() {
        try {
            pregunta = preguntatemp;
            pregunta.setIdCategoria(serCategoria.buscarCategoriaPorId(nuevaIdCategoria));
            pregunta.setIdTipopregunta(serTipoPregunta.buscarTipoPreguntaPorId(idTipoPregunta));
            serPregunta.actualizarPregunta(pregunta);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('apreDialog').hide()");// Permite cerrar un dialigo después de realizar un evento
            pregunta = new Pregunta();
            req = null;
            MensajesFaces.informacion("ACTUALIZADO CORRECTAMENTE", "");
        } catch (Exception e) {
            MensajesFaces.error("NO SE PUEDE ACTUALIZAR", "" + e);
        }
    }

    /**
     * Método para actualizar la respuesta por pregunta
     */
    public void actualizarRespuesta() {
        try {
            respuesta = respuestatemp;
            respuesta.setIdPregunta(serPregunta.buscarPreguntaPorId(nuevaIdPregunta));
            serRespuesta.actualizarRespuesta(respuesta);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('arespDialog').hide()");// Permite cerrar un dialigo después de realizar un evento
            respuesta = new Respuesta();
            req = null;
            MensajesFaces.informacion("ACTUALIZADO CORRECTAMENTE", "");
        } catch (Exception e) {
            MensajesFaces.error("NO SE PUEDE ACTUALIZAR", "" + e);
        }
    }

    /**
     * Método para eliminar la categoria
     *
     * @param cat Categoria, en este caso recibirá un objeto Categoria
     */
    public void eliminarCategoria(Categoria cat) {
        try {
            serCategoria.eliminarCategoria(cat);
            categoria = new Categoria();
            MensajesFaces.informacion("ELIMINADO CORRECTAMENTE", "");
        } catch (Exception e) {
            MensajesFaces.informacion("NO SE PUEDE ELIMINAR", "" + e);
        }

    }

    /**
     * Método para eliminar la pregunta
     *
     * @param pre Pregunta, en este caso recibirá un objeto Pregunta
     */
    public void eliminarPregunta(Pregunta pre) {
        try {
            serPregunta.eliminarPregunta(pre);
            pregunta = new Pregunta();
            MensajesFaces.informacion("ELIMINADO CORRECTAMENTE", "");
        } catch (Exception e) {
            MensajesFaces.informacion("NO SE PUEDE ELIMINAR", "" + e);
        }
    }

    /**
     * Método para eliminar la pregunta
     *
     * @param rep Respuesta, en este caso recibirá un objeto Respuesta
     */
    public void eliminarRespuesta(Respuesta rep) {
        try {
            serRespuesta.eliminarRespuesta(rep);
            respuesta = new Respuesta();
            MensajesFaces.informacion("ELIMINADO CORRECTAMENTE", "");
        } catch (Exception e) {
            MensajesFaces.informacion("NO SE PUEDE ELIMINAR", "" + e);
        }

    }

    /**
     * Método para listar encuesta por carrera
     */
    /**
     * Método para listar encuesta por carrera
     *
     * @return serEncuesta.listaEncuestaPorCarrera(nuevaIdCarrera) una lista de
     * encuestas por carrera
     */
    public List<Encuesta> listaEncuestaCarrera() {
        return serEncuesta.listaEncuestaPorCarrera(nuevaIdCarrera);
    }

    /**
     * Método para listar categoria por encuesta
     *
     * @return serCategoria.listaCategoriaPorEncuesta(nuevaIdEncuesta) una lista
     * de categorias por encuesta
     */
    public List<Categoria> listaCategoriaEncuesta() {
        return serCategoria.listaCategoriaPorEncuesta(nuevaIdEncuesta);
    }

    /**
     * Método para listar pregunta por categoria
     *
     * @return
     * serPregunta.listaPreguntaPorCategoriaAdministrador(nuevaIdCategoria) una
     * lista de pregunta por categoria
     */
    public List<PreguntaDTO> listaPreguntaCategoria() {
        return serPregunta.listaPreguntaPorCategoriaAdministrador(nuevaIdCategoria);
    }

    /**
     * Método para listar respuesta por pregunta
     *
     * @return serRespuesta.listaRespuestaPorPregunta(nuevaIdPregunta) una lista
     * de respuesta por pregunta
     */
    public List<Respuesta> listaRespuestaPregunta() {
        return serRespuesta.listaRespuestaPorPregunta(nuevaIdPregunta);
    }
    ///----------codigo para visualizar encuesta

    /**
     * Método para cambiar de evento mediante una acción
     *
     * @param event UnselectEvent desactivada mientras no haya seleccionado un
     * registro de la lista presentada
     */
    public void onRowUnselect(UnselectEvent event) {

    }

    /**
     * Método para cambiar de evento mediante una acción, mostrará la encuesta
     *
     * @param event SelectEvent activación una vez seleccionado un registro de
     * la lista presentada
     */
    public void onRowSelect(SelectEvent event) {
        encuestaActiva = (Encuesta) event.getObject();
        if (encuestaActiva != null) {
            inicializarDatosEncuesta();
            estadoBtnVisualizar = Constantes.ACTIVO;
        } else {
            MensajesFaces.error("NO EXISTE ENCUESTAS ACTIVAS PARA ESTA CARRERA", "ERROR AL GENERAR LA ENCUESTA");
        }
        mostrar();// invoca al método mostrar, el mismo que generará la enncuesta
    }

    /**
     * Método para inicializar los datos de la encuesta
     */
    private void inicializarDatosEncuesta() {
        listaCategorias = serCategoria.listaCategoriaPorEncuesta(encuestaActiva.getIdEncuesta());
    }

    /**
     * Método que generará la encuesta, usando jsf dinamico Se tomará encuenta
     * si la encuesta esta activa
     * <ul>
     * <li>true: presenta la encuesta</li>
     * <li>false: presenta un mensaje indicando que no hay una encuesta
     * activa</li>
     * </ul>
     */
    public void mostrar() {
        panelGrid = new PanelGrid();
        panelGrid.setColumns(1);
        if (encuestaActiva != null) {
            outputLabel = new OutputLabel();
            outputLabel.setStyle("font-size:14pt;font-weight: bold");
            outputLabel.setId("labelTitulo"); //debe ser unico
            //Crea un label para mostrar el titulo de la encuesta
            outputLabel.setValue(encuestaActiva.getTituloEncuesta());
            // Agrega al panelgrid principal el label del titulo
            panelGrid.getChildren().add(outputLabel);
            //Recorre las categorias de la encuesta con un foreach
            for (Categoria itemCategoria : listaCategorias) {
                //Crea un label para mostrar la categoria
                outputLabel = new OutputLabel();
                outputLabel.setStyle("font-size:10pt;font-weight: bold");
                outputLabel.setId("lblCategoria" + itemCategoria.getIdCategoria());
                outputLabel.setValue(itemCategoria.getOrden() + "." + " " + itemCategoria.getNomCategoria());
                //Agrega el label al panelgrid hijo
                panelGrid.getChildren().add(outputLabel);
                //Crea un panelgrid que va a contener las preguntas
                panelGridHijo = new PanelGrid();
                panelGridHijo.setStyle("width:100%");
                panelGridHijo.setId("panelGridcategoria" + itemCategoria.getIdCategoria());//debe ser unico
                panelGridHijo.setColumns(2);
                listaPreguntas = serPregunta.listaPreguntaPorCategoriaCliente(itemCategoria.getIdCategoria());
                //Recorre la lista de preguntas para cada categoria con un foreach
                for (Pregunta itemPregunta : listaPreguntas) {
                    //Crea el label para mostrar cada pregunta
                    outputLabel = new OutputLabel();
                    outputLabel.setStyle("font-size:8pt;");
                    outputLabel.setId("lblPregunta" + itemPregunta.getIdPregunta());
                    outputLabel.setValue(itemPregunta.getOrden() + "." + " " + itemPregunta.getTexto());
                    //Agrega al panelgridHijo correspondiente a la categoria
                    panelGridHijo.getChildren().add(outputLabel);
                    TipoPregunta tipoPregEncontrada = serTipoPregunta.buscarTipoPreguntaPorId(itemPregunta.getIdTipopregunta().getIdTipopregunta());
                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++  
                    //Busca el tipo de pregunta para recorrer la respuesta y segun eso agregar el componente
                    if (tipoPregEncontrada.getControlTipo().equals("InputText")) {
                        //Se repite para los demás tipos de control con algunas excepciones
                        imInputText = new InputText();
                        imInputText.setId("inputTextPregunta" + itemPregunta.getIdPregunta());
                        imInputText.setStyle("width:100%");
                        panelGridHijo.getChildren().add(imInputText);
                    }
                    if (tipoPregEncontrada.getControlTipo().equals("OneMenu")) {
                        selectOneMenu = new HtmlSelectOneMenu();
                        selectOneMenu.setId("selectOneMenuPregunta" + itemPregunta.getIdPregunta());
                        selectOneMenu.setStyle("width:100%");
                        uISelectItem = new UISelectItem();
                        uISelectItem.setItemLabel("SELECCIONE UNO");
                        selectOneMenu.getChildren().add(uISelectItem);
                        listaRespuestas = serRespuesta.listaRespuestaPorPregunta(itemPregunta.getIdPregunta());
                        for (Respuesta itemRespuesta : listaRespuestas) {
                            uISelectItem = new UISelectItem();
                            uISelectItem.setId("item" + itemRespuesta.getIdRespuesta());
                            uISelectItem.setItemValue(itemRespuesta.getRespuestaTexto());
                            uISelectItem.setItemLabel(itemRespuesta.getRespuestaTexto());
                            selectOneMenu.getChildren().add(uISelectItem);
                        }

                        panelGridHijo.getChildren().add(selectOneMenu);
                    }
                    if (tipoPregEncontrada.getControlTipo().equals("Vacio")) {
                        outputLabel = new OutputLabel();
                        outputLabel.setId("OutputTextVacio" + itemPregunta.getIdPregunta());
                        outputLabel.setStyle("width:100%");
                        panelGridHijo.getChildren().add(outputLabel);
                    }

                    if (tipoPregEncontrada.getControlTipo().equals("InputTextArea")) {
                        inputTextarea = new InputTextarea();
                        inputTextarea.setId("inputTextAreaPregunta" + itemPregunta.getIdPregunta());
                        inputTextarea.setStyle("width:100%");
                        panelGridHijo.getChildren().add(inputTextarea);
                    }
                    if (tipoPregEncontrada.getControlTipo().equals("Calendar")) {
                        calendar = new Calendar();
                        calendar.setId("calendarPregunta" + itemPregunta.getIdPregunta());
                        calendar.setShowOn("button");
                        panelGridHijo.getChildren().add(calendar);
                    }
                    if (tipoPregEncontrada.getControlTipo().equals("ManyCheckbox")) {

                        selectManyCheckbox = new HtmlSelectManyCheckbox();
                        selectManyCheckbox.setId("selectManyCheckbox" + itemPregunta.getIdPregunta());
                        listaRespuestas = serRespuesta.listaRespuestaPorPregunta(itemPregunta.getIdPregunta());
                        for (Respuesta itemRespuesta : listaRespuestas) {

                            uISelectItem = new UISelectItem();
                            uISelectItem.setId("item" + itemRespuesta.getIdRespuesta());
                            uISelectItem.setItemValue(itemRespuesta.getRespuestaTexto());
                            uISelectItem.setItemLabel(itemRespuesta.getRespuestaTexto());
                            selectManyCheckbox.getChildren().add(uISelectItem);

                        }

                        panelGridHijo.getChildren().add(selectManyCheckbox);
                    }
                    if (tipoPregEncontrada.getControlTipo().equals("OneRadio")) {
                        selectOneRadio = new HtmlSelectOneRadio();
                        selectOneRadio.setId("selectOneMenuPregunta" + itemPregunta.getIdPregunta());
                        listaRespuestas = serRespuesta.listaRespuestaPorPregunta(itemPregunta.getIdPregunta());
                        for (Respuesta itemRespuesta : listaRespuestas) {
                            uISelectItem = new UISelectItem();
                            uISelectItem.setId("item" + itemRespuesta.getIdRespuesta());
                            uISelectItem.setItemValue(itemRespuesta.getRespuestaTexto());
                            uISelectItem.setItemLabel(itemRespuesta.getRespuestaTexto());
                            selectOneRadio.getChildren().add(uISelectItem);
                        }
                        panelGridHijo.getChildren().add(selectOneRadio);
                    }

                    panelGrid.getChildren().add(panelGridHijo);
                }
            }

        }

    }

///----------fin codigo para visualizar encuesta
//set y get de crear una encuesta
    /**
     * Devuelve la encuesta
     *
     * @return encuesta
     */
    public Encuesta getEncuesta() {
        return encuesta;
    }

    /**
     * Modifica encuesta
     *
     * @param encuesta tipo encuesta
     */
    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    /**
     * Devuelve la lista de la carrera
     *
     * @return itemsCarrera
     */
    public List<SelectItem> getItemsCarrera() {
        return itemsCarrera;
    }

    /**
     * Modifica items de la Carrera
     *
     * @param itemsCarrera tipo lista
     */
    public void setItemsCarrera(List<SelectItem> itemsCarrera) {
        this.itemsCarrera = itemsCarrera;
    }

    /**
     * Devuelve el id de la carrera
     *
     * @return idCarrera
     */
    public int getIdCarrera() {
        return idCarrera;
    }

    /**
     * Modifica id de la carrera
     *
     * @param idCarrera tipo entero
     */
    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    /**
     * Devuelve la categoria
     *
     * @return categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Modifica categoria
     *
     * @param categoria tipo categoria
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Devuelve la nueva id de la carrera
     *
     * @return nuevaIdCarrera
     */
    public int getNuevaIdCarrera() {
        return nuevaIdCarrera;
    }

    /**
     * Modifica nueva Id de la Carrera
     *
     * @param nuevaIdCarrera tipo entero
     */
    public void setNuevaIdCarrera(int nuevaIdCarrera) {
        this.nuevaIdCarrera = nuevaIdCarrera;
    }

    /**
     * Devuelve la nueva id de la encuesta
     *
     * @return nuevaIdEncuesta
     */
    public int getNuevaIdEncuesta() {
        return nuevaIdEncuesta;
    }

    /**
     * Modifica nueva Id Encuesta
     *
     * @param nuevaIdEncuesta tipo entero
     */
    public void setNuevaIdEncuesta(int nuevaIdEncuesta) {
        this.nuevaIdEncuesta = nuevaIdEncuesta;
    }

    /**
     * Devuelve la carrera
     *
     * @return carrera
     */
    public Carrera getCarrera() {
        return carrera;
    }

    /**
     * Modifica carrera
     *
     * @param carrera tipo carrera
     */
    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    /**
     * Devuelve la encuesta temporal donde se almacenara temporalmente la
     * encuesta
     *
     * @return encuestatemp
     */
    public Encuesta getEncuestatemp() {
        return encuestatemp;
    }

    /**
     * Modifica encuesta temporal
     *
     * @param encuestatemp tipo Encuesta
     */
    public void setEncuestatemp(Encuesta encuestatemp) {
        this.encuestatemp = encuestatemp;
    }

    /**
     * Devuelve la categoria temporal donde se almacenara temporalmente la
     * categoria
     *
     * @return categoriatemp
     */
    public Categoria getNuevaCategoria() {
        return categoriatemp;
    }

    /**
     * Modifica categoria temporal
     *
     * @param categoriatemp tipo Categoria
     */
    public void setNuevaCategoria(Categoria categoriatemp) {
        this.categoriatemp = categoriatemp;
    }

    /**
     * Devuelve la nueva id de la categoria
     *
     * @return nuevaIdCategoria
     */
    public int getNuevaIdCategoria() {
        return nuevaIdCategoria;
    }

    /**
     * Modifica nueva Id Categoria
     *
     * @param nuevaIdCategoria tipo entero
     */
    public void setNuevaIdCategoria(int nuevaIdCategoria) {
        this.nuevaIdCategoria = nuevaIdCategoria;
    }

    /**
     * Devuelve la categoria temporal donde se almacenara temporalmente la
     * categoria
     *
     * @return categoriatemp
     */
    public Categoria getCategoriatemp() {
        return categoriatemp;
    }

    /**
     * Modifica categoria temporal
     *
     * @param categoriatemp tipo entero
     */
    public void setCategoriatemp(Categoria categoriatemp) {
        this.categoriatemp = categoriatemp;
    }

    /**
     * Devuelve la pregunta
     *
     * @return pregunta
     */
    public Pregunta getPregunta() {
        return pregunta;
    }

    /**
     * Modifica pregunta
     *
     * @param pregunta tipo Pregunta
     */
    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * Devuelve la pregunta temporal
     *
     * @return preguntatemp
     */
    public Pregunta getPreguntatemp() {
        return preguntatemp;
    }

    /**
     * Modifica pregunta temporal
     *
     * @param preguntatemp tipo Pregunta
     */
    public void setPreguntatemp(Pregunta preguntatemp) {
        this.preguntatemp = preguntatemp;
    }

    /**
     * Devuelve el listado de los tipos de pregunta
     *
     * @return itemsTipoPregunta
     */
    public List<SelectItem> getItemsTipoPregunta() {
        return itemsTipoPregunta;
    }

    /**
     * Modifica items Tipo Pregunta
     *
     * @param itemsTipoPregunta tipo lista
     */
    public void setItemsTipoPregunta(List<SelectItem> itemsTipoPregunta) {
        this.itemsTipoPregunta = itemsTipoPregunta;
    }

    /**
     * Devuelve el id del tipo de pregunta
     *
     * @return idTipoPregunta
     */
    public int getIdTipoPregunta() {
        return idTipoPregunta;
    }

    /**
     * Modifica id Tipo Pregunta
     *
     * @param idTipoPregunta tipo entero
     */
    public void setIdTipoPregunta(int idTipoPregunta) {
        this.idTipoPregunta = idTipoPregunta;
    }

    /**
     * Devuelve nueva id pregunta
     *
     * @return nuevaIdPregunta
     */
    public int getNuevaIdPregunta() {
        return nuevaIdPregunta;
    }

    /**
     * Modifica nueva Id Pregunta
     *
     * @param nuevaIdPregunta tipo entero
     */
    public void setNuevaIdPregunta(int nuevaIdPregunta) {
        this.nuevaIdPregunta = nuevaIdPregunta;
    }

    /**
     * Devuelve la respuesta
     *
     * @return respuesta
     */
    public Respuesta getRespuesta() {
        return respuesta;
    }

    /**
     * Modifica respuesta
     *
     * @param respuesta tipo respuesta
     */
    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * Devuelve la respuesta temporal
     *
     * @return respuestatemp
     */
    public Respuesta getRespuestatemp() {
        return respuestatemp;
    }

    /**
     * Modifica respuesta temporal
     *
     * @param respuestatemp tipo Respuesta
     */
    public void setRespuestatemp(Respuesta respuestatemp) {
        this.respuestatemp = respuestatemp;
    }

    /**
     * Devuelve el id de la respuesta
     *
     * @return idRespuesta
     */
    public int getIdRespuesta() {
        return idRespuesta;
    }

    /**
     * Modifica id de la Respuesta
     *
     * @param idRespuesta tipo entero
     */
    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    /**
     * Devuelve nueva id del tipo de pregunta
     *
     * @return nuevaIdTP
     */
    public int getNuevaIdTP() {
        return nuevaIdTP;
    }

    /**
     * Modifica nueva id del tipo pregunta
     *
     * @param nuevaIdTP tipo entero
     */
    public void setNuevaIdTP(int nuevaIdTP) {
        this.nuevaIdTP = nuevaIdTP;
    }

    /**
     * Devuelve lista de categorias
     *
     * @return listaCategorias
     */
    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    /**
     * Modifica listaCategorias
     *
     * @param listaCategorias tipo lista
     */
    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    /**
     * Devuelve lista de preguntas
     *
     * @return listaPreguntas
     */
    public List<Pregunta> getListaPreguntas() {
        return listaPreguntas;
    }

    /**
     * Modifica listaPreguntas
     *
     * @param listaPreguntas tipo lista
     */
    public void setListaPreguntas(List<Pregunta> listaPreguntas) {
        this.listaPreguntas = listaPreguntas;
    }

    /**
     * Devuelve lista de respuesta
     *
     * @return listaRespuestas
     */
    public List<Respuesta> getListaRespuestas() {
        return listaRespuestas;
    }

    /**
     * Modifica listaRespuestas
     *
     * @param listaRespuestas tipo lista
     */
    public void setListaRespuestas(List<Respuesta> listaRespuestas) {
        this.listaRespuestas = listaRespuestas;
    }

    /**
     * Devuelve panelGrid
     *
     * @return panelGrid
     */
    public PanelGrid getPanelGrid() {
        return panelGrid;
    }

    /**
     * Modifica panelGrid
     *
     * @param panelGrid tipo PanelGrid
     */
    public void setPanelGrid(PanelGrid panelGrid) {
        this.panelGrid = panelGrid;
    }

    /**
     * Devuelve outputLabel
     *
     * @return outputLabel
     */
    public OutputLabel getOutputLabel() {
        return outputLabel;
    }

    /**
     * Modifica outputLabel
     *
     * @param outputLabel tipo OutputLabel
     */
    public void setOutputLabel(OutputLabel outputLabel) {
        this.outputLabel = outputLabel;
    }

    /**
     * Devuelve encuestaActiva
     *
     * @return encuestaActiva
     */
    public Encuesta getEncuestaActiva() {
        return encuestaActiva;
    }

    /**
     * Modifica encuestaActiva
     *
     * @param encuestaActiva tipo Encuesta
     */
    public void setEncuestaActiva(Encuesta encuestaActiva) {
        this.encuestaActiva = encuestaActiva;
    }

    /**
     * Devuelve imInputText
     *
     * @return imInputText
     */
    public InputText getImInputText() {
        return imInputText;
    }

    /**
     * Modifica imInputText
     *
     * @param imInputText tipo InputText
     */
    public void setImInputText(InputText imInputText) {
        this.imInputText = imInputText;
    }

    /**
     * Devuelve panelGridHijo
     *
     * @return panelGridHijo
     */
    public PanelGrid getPanelGridHijo() {
        return panelGridHijo;
    }

    /**
     * Modifica panelGridHijo
     *
     * @param panelGridHijo tipo PanelGrid
     */
    public void setPanelGridHijo(PanelGrid panelGridHijo) {
        this.panelGridHijo = panelGridHijo;
    }

    /**
     * Devuelve inputTextarea
     *
     * @return inputTextarea
     */
    public InputTextarea getInputTextarea() {
        return inputTextarea;
    }

    /**
     * Modifica inputTextarea
     *
     * @param inputTextarea tipo InputTextarea
     */
    public void setInputTextarea(InputTextarea inputTextarea) {
        this.inputTextarea = inputTextarea;
    }

    /**
     * Devuelve uISelectItem
     *
     * @return uISelectItem
     */
    public UISelectItem getuISelectItem() {
        return uISelectItem;
    }

    /**
     * Modifica uISelectItem
     *
     * @param uISelectItem tipo UISelectItem
     */
    public void setuISelectItem(UISelectItem uISelectItem) {
        this.uISelectItem = uISelectItem;
    }

    /**
     * Devuelve selectManyCheckbox
     *
     * @return selectManyCheckbox
     */
    public HtmlSelectManyCheckbox getSelectManyCheckbox() {
        return selectManyCheckbox;
    }

    /**
     * Modifica selectManyCheckbox
     *
     * @param selectManyCheckbox tipo HtmlSelectManyCheckbox
     */
    public void setSelectManyCheckbox(HtmlSelectManyCheckbox selectManyCheckbox) {
        this.selectManyCheckbox = selectManyCheckbox;
    }

    /**
     * Devuelve selectOneRadio
     *
     * @return selectOneRadio
     */
    public HtmlSelectOneRadio getSelectOneRadio() {
        return selectOneRadio;
    }

    /**
     * Modifica selectOneRadio
     *
     * @param selectOneRadio tipo HtmlSelectOneRadio
     */
    public void setSelectOneRadio(HtmlSelectOneRadio selectOneRadio) {
        this.selectOneRadio = selectOneRadio;
    }

    /**
     * Devuelve estadoBtnVisualizar
     *
     * @return estadoBtnVisualizar
     */
    public String getEstadoBtnVisualizar() {
        return estadoBtnVisualizar;
    }

    /**
     * Modifica el estado del botón visualizar
     *
     * @param estadoBtnVisualizar tipo String
     */
    public void setEstadoBtnVisualizar(String estadoBtnVisualizar) {
        this.estadoBtnVisualizar = estadoBtnVisualizar;
    }

    /**
     * Devuelve calendar
     *
     * @return calendar
     */
    public Calendar getCalendar() {
        return calendar;
    }

    /**
     * Modifica Calendar
     *
     * @param calendar tipo Calendar
     */
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    /**
     * Método para el formato de la fecha
     *
     * @param date Date
     * @return AplicacionUtil.dateToString(date) la fecha en string
     */
    public String formatoDate(Date date) {
        return AplicacionUtil.dateToString(date);
    }
}
