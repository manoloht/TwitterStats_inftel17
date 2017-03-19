/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterStats.Beans;

import java.util.ArrayList;
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
 * @author Anthony Gaudino
 */
@Named(value = "afinidadBean")
@RequestScoped
public class AfinidadBean {

    //private static final long serialVersionUID = 1L;
    @EJB
    private TwitterStats.Facade.Twitter twitter;

    private final int minTweets = 200;
    private final int maxTweets = 3000;

    private String account = "";
    private int quantity;

    private List<String> users = new ArrayList<>();
    private List<Integer> data = new ArrayList<>();

    private int cHeight = 0;  // Chart height

    /**
     * Creates a new instance of AfinidadBean
     */
    public AfinidadBean() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = intervalConstraint(minTweets, maxTweets, quantity);
    }

    public List<String> getUsers() {
        return users;
    }

    public List<Integer> getData() {
        return data;
    }

    public int getcHeight() {
        return cHeight;
    }
    
    private static int intervalConstraint(int low, int high, int n) {
        if (n < low) {
            return low;
        }
        if (n > high) {
            return high;
        }
        return n;
    }

    public void doProcess(javax.faces.event.AjaxBehaviorEvent event) throws javax.faces.event.AbortProcessingException {
        Map< String, Integer> mentions;
        try {
            mentions = twitter.getMenciones(this.account, this.quantity);
        } catch (TwitterException ex) {
            Logger.getLogger(AfinidadBean.class.getName()).log(Level.SEVERE, null, ex);
            this.users = new ArrayList<>();
            this.data = new ArrayList<>();
            this.cHeight = 0;
            return;
        }
        this.users = new ArrayList<>(mentions.keySet());
        this.data = new ArrayList<>(mentions.values());
        this.cHeight = data.size() * 25;
    }
}
