/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterStats.Beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

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
    private boolean enviado = false;
   
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
    
    public void doReset(){
        this.nombre = "";
        this.apellidos = "";
        this.email = "";
        this.mensaje = "";
    }
    
    public void doEnviar(){
        //System.out.println(nombre + apellidos+ email +mensaje);
        this.enviado = true;
    }
    
}
