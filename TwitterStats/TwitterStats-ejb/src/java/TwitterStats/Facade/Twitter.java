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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author inftel06
 */
public class Twitter {
    twitter4j.Twitter twitter;
    
    public Twitter(){
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey("4bUGil5YTghhAZj05wJZX0wjB");
        builder.setOAuthAccessToken("330584281-bFRvETvWXnH7fb9acRMIIagP1oGQeMK0mv4RiIUB");
        builder.setOAuthConsumerSecret("nZPD0wtVuQRmqSdyEnGN4d8TRv63BPlUvkVOAZSZ2O8DfDGwGX");
        builder.setOAuthAccessTokenSecret("FGSC23QvIqBYRKleno9OQI3eBo8J54hZ5btpAf1jR2bYy");
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
    
    public Map<String,Double> getRepercusion(List<String> entrada) throws TwitterException{
        if(entrada.get(0).charAt(0)=='@'){
            return getRepercusionCuentas(entrada);
        }else{
            return getRepercusionTuits(entrada);
        }
    }
    
    public Map<String,Double> getRepercusionCuentas(List<String> cuentas) throws TwitterException{
        Map<String,Double> map = new HashMap<>();

        for(int j=0; j<cuentas.size(); j++){
            List<User> users = twitter.getFollowersList(cuentas.get(j).substring(1, cuentas.get(j).length()), -1,50);
            map.put(cuentas.get(j),(double)twitter.getFollowersIDs(cuentas.get(j).substring(1, cuentas.get(j).length()), -1).getIDs().length);
        
            for (User user : users) {
                map.put(cuentas.get(j), map.get(cuentas.get(j)) + user.getFollowersCount());
            }
        }
            
        double total = 0;

        for(Map.Entry<String, Double> entry : map.entrySet()){
            System.out.println(entry.getValue());
            total = total + (double)entry.getValue();
        }

        System.out.println(total);

        for(String key : map.keySet()){
            map.put(key, (map.get(key)*100)/total);
        }
        
        return map;
    }
    
    public Map<String,Double> getRepercusionTuits(List<String> tuits) throws TwitterException{
        Map<String,Double> map = new HashMap<>();

        for(int j=0; j<tuits.size(); j++){
            ResponseList<Status> statuses = twitter.getRetweets(Long.parseLong(tuits.get(j).split("/status/")[1]));
            map.put("Tweet "+(j+1), (double) statuses.size());
            
            //List<Status> res = statuses.subList(0, 20);
            
            for (Status status : statuses) {
                map.put("Tweet "+(j+1), map.get("Tweet "+(j+1)) + status.getUser().getFollowersCount());
            }
        }
        
        double total = 0;

        for(Map.Entry<String, Double> entry : map.entrySet()){
            total = total + (double)entry.getValue();
        }

        for(String key : map.keySet()){
            map.put(key, (map.get(key)*100)/total);
        }
        
        return map;
    }
    
}
