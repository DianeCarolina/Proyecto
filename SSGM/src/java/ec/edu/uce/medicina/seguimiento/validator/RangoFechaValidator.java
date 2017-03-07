/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.validator;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * <b>
 * Esta clase RangoFechaValidator que permite validar el rango de la fecha que ingresa
 * </b>
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@FacesValidator("validatorRangoFechas")
public class RangoFechaValidator implements Validator {
    /**
     * Método para validar el rango de ingreso de la fecha.
     * @param context
     * @param component
     * @param value
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        Object startDateValue = component.getAttributes().get("finicial");

        if (startDateValue == null) {
            return;
        }
        Date startDate = (Date) startDateValue;
        Date endDate = (Date) value;
        if (endDate.before(startDate)) {
            FacesMessage message = new FacesMessage("RANGO DE FECHAS ES INVALIDO");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);

        }
    }

}
