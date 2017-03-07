package ec.edu.uce.medicina.seguimiento.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * <b>
 * Clase con la descripción con métodos para la presentación de mensajes en una
 * plantilla JSF.
 * </b>
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
public class MensajesFaces {

    /**
     * Método que permite presentar un mensaje informativo al usuario.
     *
     * @param summary: encabezado del mensaje
     * @param detail: detalle del mensaje
     */
    public static void informacion(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage("SG",
                new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
    }

    /**
     * Método que permite presentar un mensaje de adevertencia al usuario.
     * @param summary
     * @param detail
     */
    public static void advertencia(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage("SG",
                new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail));
    }
   
    /**
     *Método que permite presentar un mensaje error al usuario.
     * @param summary
     * @param detail
     */
    public static void error(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage("SG",
                new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
    }

    /**
     *Método que permite presentar un mensaje fatal al usuario.
     * @param summary
     * @param detail
     */
    public static void fatal(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage("SG",
                new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, detail));
    }
}
