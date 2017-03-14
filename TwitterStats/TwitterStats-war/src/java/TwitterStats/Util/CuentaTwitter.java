/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterStats.Util;

/**
 *
 * @author alberto carrion
 */
public class CuentaTwitter {
    private String imgUsuario;
    private String nombreUsuario;
    private String nombreTwitterUsuario;
    private String descripcionUsuario;
    private Integer numSeguidores;
    private Integer numSiguiendo;
    private Integer numMegusta;
    private Integer numTweets;
    
    public CuentaTwitter(){
        
    }

    public CuentaTwitter(String imgUsuario, String nombreUsuario, String nombreTwitterUsuario, String descripcionUsuario, Integer numSeguidores, Integer numSiguiendo, Integer numMegusta, Integer numTweets) {
        this.imgUsuario = imgUsuario;
        this.nombreUsuario = nombreUsuario;
        this.nombreTwitterUsuario = nombreTwitterUsuario;
        this.descripcionUsuario = descripcionUsuario;
        this.numSeguidores = numSeguidores;
        this.numSiguiendo = numSiguiendo;
        this.numMegusta = numMegusta;
        this.numTweets = numTweets;
    }

    public String getImgUsuario() {
        return imgUsuario;
    }

    public void setImgUsuario(String imgUsuario) {
        this.imgUsuario = imgUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreTwitterUsuario() {
        return nombreTwitterUsuario;
    }

    public void setNombreTwitterUsuario(String nombreTwitterUsuario) {
        this.nombreTwitterUsuario = nombreTwitterUsuario;
    }

    public String getDescripcionUsuario() {
        return descripcionUsuario;
    }

    public void setDescripcionUsuario(String descripcionUsuario) {
        this.descripcionUsuario = descripcionUsuario;
    }

    public Integer getNumSeguidores() {
        return numSeguidores;
    }

    public void setNumSeguidores(Integer numSeguidores) {
        this.numSeguidores = numSeguidores;
    }

    public Integer getNumSiguiendo() {
        return numSiguiendo;
    }

    public void setNumSiguiendo(Integer numSiguiendo) {
        this.numSiguiendo = numSiguiendo;
    }

    public Integer getNumMegusta() {
        return numMegusta;
    }

    public void setNumMegusta(Integer numMegusta) {
        this.numMegusta = numMegusta;
    }

    public Integer getNumTweets() {
        return numTweets;
    }

    public void setNumTweets(Integer numTweets) {
        this.numTweets = numTweets;
    }
    
}
