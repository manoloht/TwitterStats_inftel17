/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterStats.Beans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author inftel06
 */
@Named(value = "tweetRelevantesBean")
@RequestScoped
public class TweetRelevantesBean {

    /**
     * Creates a new instance of TweetRelevantesBean
     */
    public TweetRelevantesBean() {
    }
    
}
