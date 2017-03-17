/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterStats.Beans;

import TwitterStats.Facade.Twitter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import twitter4j.TwitterException;

/**
 *
 * @author Manuel
 */
@Named(value = "historicosBean")
@RequestScoped
public class HistoricosBean {

    @EJB
    private TwitterStats.Facade.Twitter twitter;

    private String cuenta;
    private String mes;
    private String anio;
    private List<Integer> listaAnios;
    private List<Map<String, Integer>> listaMapa;

    private List<String> elementosHoras;
    private Map<String, Integer> porcentajesHoras;

    private List<String> elementosSemanal;
    private Map<String, Integer> porcentajesSemanal;

    private List<String> elementosDias;
    private Map<String, Integer> porcentajesDias;
    
    private boolean renderizarGraficos = false;

    /**
     * Creates a new instance of HistoricosBean
     */
    public HistoricosBean() {
    }

    @PostConstruct
    public void init() {
        listaAnios = new ArrayList<>();
        this.mes = "03";
        this.anio = "2017";
        for (int i = 2009; i < 2018; i++) {
            listaAnios.add(i);
        }

    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public List<Integer> getListaAnios() {
        return listaAnios;
    }

    public void setListaAnios(List<Integer> listaAnios) {
        this.listaAnios = listaAnios;
    }

    public List<Map<String, Integer>> getListaMapa() {
        return listaMapa;
    }

    public void setListaMapa(List<Map<String, Integer>> listaMapa) {
        this.listaMapa = listaMapa;
    }

    public List<String> getElementosHoras() {
        return elementosHoras;
    }

    public void setElementosHoras(List<String> elementosHoras) {
        this.elementosHoras = elementosHoras;
    }

    public Map<String, Integer> getPorcentajesHoras() {
        return porcentajesHoras;
    }

    public void setPorcentajesHoras(Map<String, Integer> porcentajesHoras) {
        this.porcentajesHoras = porcentajesHoras;
    }

    public Twitter getTwitter() {
        return twitter;
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

    public List<String> getElementosSemanal() {
        return elementosSemanal;
    }

    public void setElementosSemanal(List<String> elementosSemanal) {
        this.elementosSemanal = elementosSemanal;
    }

    public Map<String, Integer> getPorcentajesSemanal() {
        return porcentajesSemanal;
    }

    public void setPorcentajesSemanal(Map<String, Integer> porcentajesSemanal) {
        this.porcentajesSemanal = porcentajesSemanal;
    }

    public List<String> getElementosDias() {
        return elementosDias;
    }

    public void setElementosDias(List<String> elementosDias) {
        this.elementosDias = elementosDias;
    }

    public Map<String, Integer> getPorcentajesDias() {
        return porcentajesDias;
    }

    public void setPorcentajesDias(Map<String, Integer> porcentajesDias) {
        this.porcentajesDias = porcentajesDias;
    }

    public boolean isRenderizarGraficos() {
        return renderizarGraficos;
    }

    public void setRenderizarGraficos(boolean renderizarGraficos) {
        this.renderizarGraficos = renderizarGraficos;
    }
    
    public String doCalcular() throws TwitterException {
        try {
            //this.listaMapa = new ArrayList<>();

            elementosHoras = new ArrayList<>();
            porcentajesHoras = new HashMap();

            elementosDias = new ArrayList<>();
            porcentajesDias = new HashMap();

            elementosSemanal = new ArrayList<>();
            porcentajesSemanal = new HashMap();

            // Obtención de los datos de twitter
            this.listaMapa = twitter.getHistorico(this.cuenta, this.mes, this.anio);

            // Datos de horas
            porcentajesHoras = this.listaMapa.get(0);
            porcentajesHoras.keySet().forEach((el) -> {
                this.elementosHoras.add(el);
            });

            // Datos de dias
            porcentajesDias = this.listaMapa.get(2);
            porcentajesDias.keySet().forEach((el) -> {
                this.elementosDias.add(el);
            });

            // Datos de semanal
            porcentajesSemanal = this.listaMapa.get(1);
            porcentajesSemanal.keySet().forEach((el) -> {
                this.elementosSemanal.add(el);
                if(porcentajesSemanal.get(el)>0){
                    this.renderizarGraficos = true;
                }
            });

        } catch (TwitterException ex) {
             Logger.getLogger(TweetRelevantesBean.class.getName()).log(Level.SEVERE, null, ex);
             this.listaMapa = new ArrayList<>();
        }

        return "resultadosHistoricos";
    }

    public String doVolver() {
        this.init();

        return "historicos";
    }
}
