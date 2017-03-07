/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 * <b>
 * Clase con la descripción del listener del ciclo de vida de JSF.
 * </b>
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
public class AutorizacionListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext fc = event.getFacesContext();
        String paginaActual = fc.getViewRoot().getViewId();
        boolean isLogin;
        //Todas las paginas a las cuales no podrá acceder segun el perfil del usuario
        if (paginaActual.lastIndexOf("/paginas/login/login.xhtml") > -1) {
            isLogin = true;
        } else if (paginaActual.lastIndexOf("/paginas/inicio.xhtml") > -1) {
            isLogin = true;
        } else if (paginaActual.lastIndexOf("/paginas/pagCliente/contactos.xhtml") > -1) {
            isLogin = true;

        } else if (paginaActual.lastIndexOf("/paginas/pagCliente/validar_registro.xhtml") > -1) {
            isLogin = true;

        } else if (paginaActual.lastIndexOf("/paginas/pagCliente/registro_graduado.xhtml") > -1) {
            isLogin = true;
        } else if (paginaActual.lastIndexOf("/paginas/cuestionario/vista1.xhtml") > -1) {
            isLogin = true;
        } else if (paginaActual.lastIndexOf("/paginas/cuestionario/mensajeEncuestaGuardada.xhtml") > -1) {
            isLogin = true;
        } else if (paginaActual.lastIndexOf("/paginas/pagCliente/validar_ficha.xhtml") > -1) {
            isLogin = true;
        } else if (paginaActual.lastIndexOf("/paginas/pagCliente/reporte.xhtml") > -1) {
            isLogin = true;
        } else {
            isLogin = false;
        }
        HttpSession httpSession = (HttpSession) fc
                .getExternalContext().getSession(true);
        Object usuario = httpSession.getAttribute("usuario");
        if (isLogin == false && usuario == null) {
            NavigationHandler nh = fc.getApplication()
                    .getNavigationHandler();
            nh.handleNavigation(fc, null, "/paginas/inicio.xhtml");
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
