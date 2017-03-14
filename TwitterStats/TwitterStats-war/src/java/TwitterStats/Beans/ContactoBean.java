/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterStats.Beans;

import TwitterStats.Util.Mail;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.mail.MessagingException;

/**
 *
 * @author alberto carrion
 */
@Named(value = "contactoBean")
@RequestScoped
public class ContactoBean {

    /**
     * Creates a new instance of ContactoBean
     */
    private String nombre;
    private String apellidos;
    private String email;
    private String mensaje;
    private boolean enviado;
    private boolean error;

    public ContactoBean() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void doReset() {
        this.nombre = "";
        this.apellidos = "";
        this.email = "";
        this.mensaje = "";
    }

    public void doEnviar() {
        try {
            Mail mail = new Mail();
            if (mail.enviarEmail(this.nombre, this.apellidos, this.email, this.mensaje)) {
                this.enviado = true;
                this.error = false;
            }
        } catch (MessagingException ex) {
            this.enviado = false;
            this.error = true;
            Logger.getLogger(ContactoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
