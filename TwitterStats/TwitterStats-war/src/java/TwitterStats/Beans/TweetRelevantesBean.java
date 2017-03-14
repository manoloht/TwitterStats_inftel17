/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterStats.Beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author alberto carrion
 */
@Named(value = "tweetRelevantesBean")
@RequestScoped
public class TweetRelevantesBean {

    /**
     * Creates a new instance of TweetRelevantesBean
     */
    private String busqueda;
    private int numEstudio = 1600;
    private int numTweetsMostrar = 10;
    private String fechaInicio;
    private String fechaFin;

    public TweetRelevantesBean() {
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public int getNumEstudio() {
        return numEstudio;
    }

    public void setNumEstudio(int numEstudio) {
        this.numEstudio = numEstudio;
    }

    public int getNumTweetsMostrar() {
        return numTweetsMostrar;
    }

    public void setNumTweetsMostrar(int numTweetsMostrar) {
        this.numTweetsMostrar = numTweetsMostrar;
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

    public void doBuscar() {

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-mm-yyyy");
        try {
            if (!fechaInicio.equals("") && !fechaFin.equals("")) {
                Date fechaIni = formatoFecha.parse(fechaInicio);
                Date fechaF = formatoFecha.parse(fechaFin);
                System.out.println("fechaInicio ---> " + fechaIni.toString());
                System.out.println("fechaFin ---> " + fechaF.toString());
            }
            System.out.println("busqueda ---> " + busqueda);
            System.out.println("numEstudio ---> " + numEstudio);
            System.out.println("numMostrar ---> " + numTweetsMostrar);

        } catch (ParseException ex) {
            Logger.getLogger(TweetRelevantesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
