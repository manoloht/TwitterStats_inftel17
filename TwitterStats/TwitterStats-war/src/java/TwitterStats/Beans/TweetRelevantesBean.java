/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterStats.Beans;

import TwitterStats.Util.CuentaTwitter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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

    @EJB
    private TwitterStats.Facade.Twitter twitter;

    /**
     * Creates a new instance of TweetRelevantesBean
     */
    private String busqueda;
    private int numEstudio = 400;
    private int numTweetsMostrar = 10;
    private String fechaInicio;
    private String fechaFin;
    private List<Status> listaTweets;
    private CuentaTwitter cuentaTwitter;

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

    public CuentaTwitter getCuentaTwitter() {
        return cuentaTwitter;
    }

    public void setCuentaTwitter(CuentaTwitter cuentaTwitter) {
        this.cuentaTwitter = cuentaTwitter;
    }

    public String doBuscarNumEstudio() {
        try {
            this.listaTweets = twitter.getTuitsCuenta(this.busqueda, this.numEstudio, this.numTweetsMostrar);

            if (listaTweets.size() > 0) {
                Status st = listaTweets.get(0);
                this.cuentaTwitter = new CuentaTwitter();
                this.cuentaTwitter.setImgUsuario(st.getUser().getProfileImageURL());
                this.cuentaTwitter.setNombreUsuario(st.getUser().getName());
                this.cuentaTwitter.setNombreTwitterUsuario(st.getUser().getScreenName());
                this.cuentaTwitter.setDescripcionUsuario(st.getUser().getDescription());
                this.cuentaTwitter.setNumSeguidores(st.getUser().getFollowersCount());
                this.cuentaTwitter.setNumSiguiendo(st.getUser().getFriendsCount());
                this.cuentaTwitter.setNumMegusta(st.getUser().getFavouritesCount());
                this.cuentaTwitter.setNumTweets(st.getUser().getStatusesCount());
            }

        } catch (TwitterException ex) {
            Logger.getLogger(TweetRelevantesBean.class.getName()).log(Level.SEVERE, null, ex);
            this.listaTweets = new ArrayList<>();
        }
        return "resultadosTweetsRelevantes.xhtml";
    }

    public String doBuscarFechas() {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (!fechaInicio.equals("") && !fechaFin.equals("")) {
                Date fInicio = dt.parse(fechaInicio);
                Date fFin = dt.parse(fechaFin);
                this.listaTweets = twitter.getTuitsCuenta(busqueda, fInicio, fFin, numTweetsMostrar);

                if (listaTweets.size() > 0) {
                    Status st = listaTweets.get(0);
                    this.cuentaTwitter = new CuentaTwitter();
                    this.cuentaTwitter.setImgUsuario(st.getUser().getProfileImageURL());
                    this.cuentaTwitter.setNombreUsuario(st.getUser().getName());
                    this.cuentaTwitter.setNombreTwitterUsuario(st.getUser().getScreenName());
                    this.cuentaTwitter.setDescripcionUsuario(st.getUser().getDescription());
                    this.cuentaTwitter.setNumSeguidores(st.getUser().getFollowersCount());
                    this.cuentaTwitter.setNumSiguiendo(st.getUser().getFriendsCount());
                    this.cuentaTwitter.setNumMegusta(st.getUser().getFavouritesCount());
                    this.cuentaTwitter.setNumTweets(st.getUser().getStatusesCount());
                }
                
            }else{
                this.listaTweets = new ArrayList<>();
            }
        } catch (ParseException | TwitterException ex) {
            Logger.getLogger(TweetRelevantesBean.class.getName()).log(Level.SEVERE, null, ex);
            this.listaTweets = new ArrayList<>();
        }
        return "resultadosTweetsRelevantes.xhtml";
    }
}
