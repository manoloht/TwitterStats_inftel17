/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterStats.Beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Manuel
 */
@Named(value = "historicosBean")
@RequestScoped
public class HistoricosBean {
    
    private String cuenta;
    private String mes;
    private String anio;
    private List<Integer> listaAnios;
    /**
     * Creates a new instance of HistoricosBean
     */
    public HistoricosBean() {
    }


    @PostConstruct
    public void init() {
        listaAnios = new ArrayList<>();        
        this.mes = "01";  
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


    
    
    public String doCalcular(){
        System.out.println("+++++++++++++"+this.cuenta);
        System.out.println("+++++++++++++"+this.mes);
        System.out.println("+++++++++++++"+this.anio);
        return "resultadosHistoricos";
    }
    
    public String doVolver() {
        this.init();

        return "historicos";
    }
}
