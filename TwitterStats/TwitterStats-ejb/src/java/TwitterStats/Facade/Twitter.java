/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterStats.Facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author inftel06
 */
public class Twitter {
    twitter4j.Twitter twitter;
    
    public Twitter(){
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey("Y7czzxOoizactb9y3KNPoqlWT");
        builder.setOAuthAccessToken("123586822-Cnp1B4LFOjlbetT7sBa4mvK8zodvapB5p3fOlpnC");
        builder.setOAuthConsumerSecret("tCd9LJxXmsiChbLxXdzihWq4lFWAktFZsiNiBYOjA5LJ8cvaJA");
        builder.setOAuthAccessTokenSecret("34becSxxMVUPrRq6LS5aixwsMXKzqNL02OmNNzg8NPhYc");
        twitter4j.conf.Configuration configuration = builder.build();
        twitter = (new TwitterFactory(configuration)).getInstance();
    }
    
    public List<Status> getTuitsCuenta(String user) throws TwitterException{
        ResponseList res = twitter.getUserTimeline(user);
        List<Status> lista = new ArrayList<>();
        lista.addAll(res);
        return lista;
    }

    public List<Status> getTuitsCuenta(long id) throws TwitterException{
        ResponseList res = twitter.getUserTimeline(id);
        List<Status> lista = new ArrayList<>();
        lista.addAll(res);
        return lista;
    }
    
    public List<Status> getTuitsCuentaFecha(String user, Date despuesDe, Date antesDe) throws TwitterException{
        ResponseList res = twitter.getUserTimeline(user);
        List<Status> lista = new ArrayList<>();
        
        for(Object tuit : res){
            if(dentroRango(((Status)tuit).getCreatedAt(),despuesDe,antesDe)){
                lista.add((Status)tuit);
            }
        }
        return lista;
    }
    
    public List<Status> getTuitsCuentaFecha(long id, Date despuesDe, Date antesDe) throws TwitterException{
        ResponseList res = twitter.getUserTimeline(id);
        List<Status> lista = new ArrayList<>();
        
        for(Object tuit : res){
            if(dentroRango(((Status)tuit).getCreatedAt(),despuesDe,antesDe)){
                lista.add((Status)tuit);
            }
        }
        return lista;
    }
    
    private boolean dentroRango(Date date, Date despuesDe, Date antesDe){
        if(despuesDe == null){
            if(date.before(antesDe)){
                return true;
            }else{
                return false;
            }
        }else if(antesDe == null){
            if(date.after(despuesDe)){
                return true;
            }else{
                return false;
            }
        }else{
            if(date.after(despuesDe) && date.before(antesDe)){
                return true;
            }else{
                return false;
            }
        }
    }
}
