/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterStats.Facade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import twitter4j.Paging;
import twitter4j.Query;
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
    
    public List<Status> getTuitsCuenta(String user, int estudio, int cantidad) throws TwitterException{
        ResponseList res;
        List<Status> lista = new ArrayList<>();
        for(int i=1; i<=estudio/200; i++){
            res = twitter.getUserTimeline(user, new Paging(i,200));
            lista.addAll(res);
        }
        
        Collections.sort(lista, (Status s1, Status s2) -> s1.getFavoriteCount() > s2.getFavoriteCount() ? -1 : (s1.getFavoriteCount() < s2.getFavoriteCount() ) ? 1 : 0);
        
        if(lista.size()>cantidad){
            return lista.subList(0, cantidad);
        }else{
            return lista;
        }
    }

    public List<Status> getTuitsCuenta(long id, int estudio, int cantidad) throws TwitterException{
        ResponseList res;
        List<Status> lista = new ArrayList<>();
        for(int i=1; i<=estudio/200; i++){
            res = twitter.getUserTimeline(id, new Paging(i,200));
            lista.addAll(res);
        }
        
        Collections.sort(lista, (Status s1, Status s2) -> s1.getFavoriteCount() > s2.getFavoriteCount() ? -1 : (s1.getFavoriteCount() < s2.getFavoriteCount() ) ? 1 : 0);
        
        if(lista.size()>cantidad){
            return lista.subList(0, cantidad);
        }else{
            return lista;
        }
    }
    
    public List<Status> getTuitsCuenta(String user, Date desde, Date hasta, int cantidad) throws TwitterException{
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Query q = new Query("from:"+user+" since:"+dt.format(desde)+" until:"+dt.format(hasta));
        q.setCount(100);
        List<Status> lista = new ArrayList<>();
        List<Status> res = twitter.search(q).getTweets();
        
        while (res.size()>1 && lista.size()<3000){
            lista.addAll(res);
            
            q.setMaxId(res.get(res.size()-1).getId());
            res = twitter.search(q).getTweets();
        }
        
        Collections.sort(lista, (Status s1, Status s2) -> s1.getFavoriteCount() > s2.getFavoriteCount() ? -1 : (s1.getFavoriteCount() < s2.getFavoriteCount() ) ? 1 : 0);
        
        if(lista.size()>cantidad){
            return lista.subList(0, cantidad);
        }else{
            return lista;
        }
    }
}
