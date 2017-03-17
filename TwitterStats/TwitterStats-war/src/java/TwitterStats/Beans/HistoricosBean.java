/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterStats.Beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private List<Map<String,Integer>> listaMapa;
    
    private List<String> elementosHoras;
    private Map<String, Integer> porcentajesHoras;
    

    
    
    
    /**
     * Creates a new instance of HistoricosBean
     */
    public HistoricosBean() {
    }


    @PostConstruct
    public void init() {
        listaAnios = new ArrayList<>();        
        this.mes = "03";  
        this.anio ="2017";        
        for(int i=2009;i<2018;i++){
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


    
    
    public String doCalcular() throws TwitterException{
        
        this.listaMapa = new ArrayList<>();
        
        elementosHoras = new ArrayList<>();
        porcentajesHoras = new HashMap();;
        
        this.listaMapa = twitter.getHistorico(this.cuenta, this.mes, this.anio);
        
        porcentajesHoras = this.listaMapa.get(0);
        porcentajesHoras.keySet().forEach((el) -> {
                this.elementosHoras.add(el);
            });
        
        
        
        return "resultadosHistoricos";
    }
    
    public String doVolver() {
        this.init();

        return "historicos";
    }
}
