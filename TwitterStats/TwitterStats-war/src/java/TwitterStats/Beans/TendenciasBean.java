/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterStats.Beans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author alberto carrion
 */
@Named(value = "tendenciasBean")
@RequestScoped
public class TendenciasBean {

    /**
     * Creates a new instance of TendenciasBean
     */
    
    private String busqueda;
    private String fechaInicio;
    private String fechaFin;
    private int numTweetsEstudio;
    
    public TendenciasBean() {
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getNumTweetsEstudio() {
        return numTweetsEstudio;
    }

    public void setNumTweetsEstudio(int numTweetsEstudio) {
        this.numTweetsEstudio = numTweetsEstudio;
    }
    
    public String doBuscar(){
        return "resultadosTendencias.xhtml";
    }
    
}
