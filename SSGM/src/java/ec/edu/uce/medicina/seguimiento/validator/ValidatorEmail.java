/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


/**
 * <b>
 * Clase que contiene la descripción del validador de email.
 * </b>
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@FacesValidator("validatorEmail")
public class ValidatorEmail implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String label;
        HtmlInputText intputText= (HtmlInputText) component;
        if (intputText==null || intputText.getLabel().trim().equals("")) {
            label=intputText.getId();
        } else {
            label=intputText.getLabel();
        }
        Pattern pattern= Pattern.compile("([a-zA-Z0-9\\.\\/-_]+\\@[a-zA-Z-]+\\.[a-zA-Z]+)*");
        Matcher matcher= pattern.matcher((CharSequence)value);
        if (!matcher.matches())
        {
        throw  new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR:",label+":ES INCORRECTO"));
        }
    }
    
}
