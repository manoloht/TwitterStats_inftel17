/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterStats.Beans;

import TwitterStats.Util.Twitter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import twitter4j.Status;
import twitter4j.TwitterException;

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
    private List<Status> listaTweets;  

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

    public List<Status> getListaTweets() {
        return listaTweets;
    }

    public void setListaTweets(List<Status> listaTweets) {
        this.listaTweets = listaTweets;
    }
    
    public String doBuscarNumEstudio() {
        
        try {
            Twitter twitter = new Twitter();
            this.listaTweets = twitter.getTuitsCuenta(this.busqueda, this.numEstudio, this.numTweetsMostrar);
            for(Status st : listaTweets){
                System.out.println(st.getRetweetCount());
            }
        } catch (TwitterException ex) {
            Logger.getLogger(TweetRelevantesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "tweetsRelevantesResultados.xhtml";

    }

    public void doBuscarFechas() {
        /*SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-mm-yyyy");
        try {
            if (!fechaInicio.equals("") && !fechaFin.equals("")) {
                Date fechaIni = formatoFecha.parse(fechaInicio);
                Date fechaF = formatoFecha.parse(fechaFin);
                System.out.println("BUSCANDO POR FECHAS");
                System.out.println("busqueda ---> " + busqueda);
                System.out.println("fechaInicio ---> " + fechaIni.toString());
                System.out.println("fechaFin ---> " + fechaF.toString());
                System.out.println("numMostrar ---> " + numTweetsMostrar);
            }

        } catch (ParseException ex) {
            Logger.getLogger(TweetRelevantesBean.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
}
