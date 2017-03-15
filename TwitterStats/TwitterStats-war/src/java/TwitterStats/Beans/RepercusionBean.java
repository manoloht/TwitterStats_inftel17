/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterStats.Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author inftel06
 */
@Named(value = "repercusionBean")
@SessionScoped
public class RepercusionBean implements Serializable {
    
    private List<String> elementos;
    private String elemento0;
    private String elemento1;
    private Map porcentajes;
    private int numElementos=2;
    
    /**
     * Creates a new instance of RepercusionBean
     */
    public RepercusionBean() {
    }

    @PostConstruct
    public void init(){
        elementos = new ArrayList<>();
        for(int i=0; i<numElementos; i++){
            elementos.add("");
        }

    }
    
    public List<String> getElementos() {
        return elementos;
    }

    public void setElementos(List<String> elementos) {
        this.elementos = elementos;
    }

    public String getElemento0() {
        return elemento0;
    }

    public void setElemento0(String elemento0) {
        this.elemento0 = elemento0;
    }

    public String getElemento1() {
        return elemento1;
    }

    public void setElemento1(String elemento1) {
        this.elemento1 = elemento1;
    }

    public Map getPorcentajes() {
        return porcentajes;
    }

    public void setPorcentajes(Map porcentajes) {
        this.porcentajes = porcentajes;
    }

    

    public int getNumElementos() {
        return numElementos;
    }

    public void setNumElementos(int numElementos) {
        this.numElementos = numElementos;
    }
    
    
    public String doCalcular(){
        
        porcentajes = new HashMap();
        // Funcion de twitter de recuperar lista de diccionario
        // diccionario = repercusion(elementos);
        porcentajes.put(elementos.get(0), 20);
        porcentajes.put(elementos.get(1), 80);
        
        // Numero de elementos de lista para la gráfica
        //numElementos=this.elementos.size();
        numElementos=2;
        return "resultadosRepercusion";
    }

    
    public String doVolver(){
        this.numElementos=2;
        this.init();
        
        return "repercusion";
    }
    
    
    public String doAgregarCampo(){
        this.elementos.add("");
        this.numElementos +=1;
        return "repercusion";
    }
}
