/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterStats.Beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import twitter4j.TwitterException;

/**
 *
 * @author alberto carrion
 */
@Named(value = "tendenciasBean")
@RequestScoped
public class TendenciasBean {

    @EJB
    private TwitterStats.Facade.Twitter twitter;

    /**
     * Creates a new instance of TendenciasBean
     */
    private String busqueda;
    private String fechaInicio;
    private String fechaFin;
    private int numTweetsEstudio = 400;
    private boolean busquedaNumEstudio;
    private Map<String, Integer> tendencias;
    private List<String> listaKeyMapa = new ArrayList<>();
    
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

    public boolean isBusquedaNumEstudio() {
        return busquedaNumEstudio;
    }

    public void setBusquedaNumEstudio(boolean busquedaNumEstudio) {
        this.busquedaNumEstudio = busquedaNumEstudio;
    }

    public Map<String, Integer> getTendencias() {
        return tendencias;
    }

    public void setTendencias(Map<String, Integer> tendencias) {
        this.tendencias = tendencias;
    }

    public List<String> getListaKeyMapa() {
        return listaKeyMapa;
    }

    public void setListaKeyMapa(List<String> listaKeyMapa) {
        this.listaKeyMapa = listaKeyMapa;
    }

    public String doBuscar() {
        try {
            this.busquedaNumEstudio = true;
            this.tendencias = twitter.getTendencias(this.busqueda, this.numTweetsEstudio);
            tendencias.keySet().forEach((el) -> {
                this.listaKeyMapa.add(el);
            });
        } catch (TwitterException ex) {
            Logger.getLogger(TendenciasBean.class.getName()).log(Level.SEVERE, null, ex);
            this.tendencias = new HashMap<>();
        }
        return "resultadosTendencias.xhtml";
    }

    public String doBuscarFechas() {
        try {
            this.busquedaNumEstudio = false;
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            if (!this.fechaInicio.equals("") && !this.fechaFin.equals("")) {
                Date fInicio = dt.parse(fechaInicio);
                Date fFin = dt.parse(fechaFin);
                this.tendencias = twitter.getTendencias(busqueda, fInicio, fFin);
                tendencias.keySet().forEach((el) -> {
                    this.listaKeyMapa.add(el);
                });
            } else {
                this.tendencias = new HashMap<>();
            }
        } catch (ParseException | TwitterException ex) {
            Logger.getLogger(TweetRelevantesBean.class.getName()).log(Level.SEVERE, null, ex);
            this.tendencias = new HashMap<>();
        }
        return "resultadosTendencias.xhtml";
    }

}
