/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterStats.Beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author inftel06
 */
@Named(value = "repercusionBean")
@RequestScoped
public class RepercusionBean {
    
    private List<String> elementos;
    private String elemento0;
    private String elemento1;
    private Map lista;
    private int numElementos=0;
    
    /**
     * Creates a new instance of RepercusionBean
     */
    public RepercusionBean() {
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

    public Map getLista() {
        return lista;
    }

    public void setLista(Map lista) {
        this.lista = lista;
    }

    public int getNumElementos() {
        return numElementos;
    }

    public void setNumElementos(int numElementos) {
        this.numElementos = numElementos;
    }
    
    
    
    public String doCalcular(){
        lista = new HashMap();
        lista.put(this.elemento0, 60);
        lista.put(this.elemento1, 40);
        
        elementos = new ArrayList<String>();
        this.elementos.add(this.elemento0);
        this.elementos.add(this.elemento1);
        numElementos=this.elementos.size();
        return "resultadosRepercusion";
    }
    
    public String doVolver(){
        this.elemento0="";
        this.elemento1="";
        
        return "repercusion";
    }
    
    
    
}
