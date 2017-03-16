/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterStats.Facade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.ejb.Stateful;
import twitter4j.HashtagEntity;
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
@Stateful
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
        
        Collections.sort(lista, new Comparator<Status>() {

            public int compare(Status s1, Status s2) {
                return s1.getFavoriteCount() > s2.getFavoriteCount() ? -1 : (s1.getFavoriteCount() < s2.getFavoriteCount() ) ? 1 : 0;
            }
        });
        
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
        
        Collections.sort(lista, new Comparator<Status>() {

            public int compare(Status s1, Status s2) {
                return s1.getFavoriteCount() > s2.getFavoriteCount() ? -1 : (s1.getFavoriteCount() < s2.getFavoriteCount() ) ? 1 : 0;
            }
        });

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
        
        Collections.sort(lista, new Comparator<Status>() {

            public int compare(Status s1, Status s2) {
                return s1.getFavoriteCount() > s2.getFavoriteCount() ? -1 : (s1.getFavoriteCount() < s2.getFavoriteCount() ) ? 1 : 0;
            }
        });
        
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

        for(String key : map.keySet()){
            map.put(key, (map.get(key)*100)/total);
        }
        
        return map;
    }
    
    public Map<String,Double> getRepercusionTuits(List<String> tuits) throws TwitterException{
        Map<String,Double> map = new HashMap<>();

        for(int j=0; j<tuits.size(); j++){
            ResponseList<Status> statuses = twitter.getRetweets(Long.parseLong(tuits.get(j).split("/status/")[1]));
            map.put(tuits.get(j), (double) statuses.size());
            
            //List<Status> res = statuses.subList(0, 20);
            
            for (Status status : statuses) {
                map.put(tuits.get(j), map.get(tuits.get(j)) + status.getUser().getFollowersCount());
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
    
    public Map<String,Integer> getTendencias(String user, int estudio) throws TwitterException{
        ResponseList res;
        List<Status> lista = new ArrayList<>();
        Map<String,Integer> tendencias = new HashMap<>();
        
        for(int i=1; i<=estudio/200; i++){
            res = twitter.getUserTimeline(user, new Paging(i,200));
            lista.addAll(res);
        }
        
        for(Status status : lista){
            HashtagEntity[] ht = status.getHashtagEntities();
            for(int i=0; i<ht.length; i++){
                String hash = ht[i].getText();
                if(tendencias.containsKey(hash)){
                    tendencias.put(hash,tendencias.get(hash)+1);
                }else{
                    tendencias.put(hash,1);
                }
            }
        }

        return sortByValue(tendencias);
    }
    
    public Map<String,Integer> getTendencias(String user, Date desde, Date hasta) throws TwitterException{
        Map<String,Integer> tendencias = new HashMap<>();
        
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
        
        for(Status status : lista){
            HashtagEntity[] ht = status.getHashtagEntities();
            for(int i=0; i<ht.length; i++){
                String hash = ht[i].getText();
                if(tendencias.containsKey(hash)){
                    tendencias.put(hash,tendencias.get(hash)+1);
                }else{
                    tendencias.put(hash,1);
                }
            }
        }

        return sortByValue(tendencias);
    }
    
    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map ){
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
        {
            @Override
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        } );

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }
    
    public List<Map<String,Integer>> getHistorico(String user, String mes, String año) throws TwitterException{
        List<Map<String,Integer>> resultado = new ArrayList<>();
        GregorianCalendar calendar = new GregorianCalendar();
        String end;
        
        if(mes.equals("02") && calendar.isLeapYear(Integer.parseInt(año))){
            end = "29";
        }else if(mes.equals("02") && !calendar.isLeapYear(Integer.parseInt(año))){
            end = "28";
        }else if(mes.equals("04") || mes.equals("06") || mes.equals("09") || mes.equals("11")){
            end = "30";
        }else{
            end = "31";
        }
        
        Query q = new Query("from:"+user+" since:"+año+"-"+mes+"-01 until:"+año+"-"+mes+"-"+end);
        System.out.println(q.getQuery());
        q.setCount(100);
        List<Status> lista = new ArrayList<>();
        
        List<Status> res = twitter.search(q).getTweets();
        
        while (res.size()>1 && lista.size()<3000){
            lista.addAll(res);
            
            q.setMaxId(res.get(res.size()-1).getId());
            res = twitter.search(q).getTweets();
        }
        
        resultado.add(historicoHorario(lista));
        resultado.add(historicoSemanal(lista));
        resultado.add(historicoDiario(lista,end));
        
        return resultado;
    }
    
    private Map<String,Integer> historicoHorario(List<Status> tuits){
        Comparator<String> comparator = new Comparator<String>() {
            public int compare(String o1, String o2) {
              if(Integer.parseInt(o1.substring(0,o1.length()-1))>Integer.parseInt(o2.substring(0,o2.length()-1))){
                  return 1;
              }else if(Integer.parseInt(o1.substring(0,o1.length()-1))>Integer.parseInt(o2.substring(0,o2.length()-1))){
                  return -1;
              }else{
                  return 0;
              }
            }
        };
        
        SortedMap<String,Integer> porHoras = new TreeMap<String,Integer>(comparator);
        GregorianCalendar cal = new GregorianCalendar();
        
        for(int i=0; i<24; i++){
            porHoras.put(i+"h", 0);
        }
        
        for(Status tuit : tuits){
            cal.setTime(tuit.getCreatedAt());
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            
            porHoras.put(hour+"h", porHoras.get(hour+"h")+1);
        }
        System.out.println(porHoras);
        return porHoras;
    }
    
    private Map<String,Integer> historicoSemanal(List<Status> tuits){
        String apoyo = "LMXJVSD";
        
        Comparator<String> comparator = new Comparator<String>() {
            public int compare(String o1, String o2) {
              if(apoyo.indexOf(o1)>apoyo.indexOf(o2)){
                  return 1;
              }else if(apoyo.indexOf(o1)<apoyo.indexOf(o2)){
                  return -1;
              }else{
                  return 0;
              }
            }
        };
        
        SortedMap<String,Integer> semanal = new TreeMap<>(comparator);
        GregorianCalendar cal = new GregorianCalendar();
        String[] semana = new String[]{"_","D","L","M","X","J","V","S"};
        
        for(int i=2; i<8; i++){
            semanal.put(semana[i], 0);
        }
            semanal.put(semana[1], 0);
        
        
        for(Status tuit : tuits){
            
            cal.setTime(tuit.getCreatedAt());
            int sem = cal.get(Calendar.DAY_OF_WEEK);
            
            if(semanal.containsKey(semana[sem])){
                semanal.put(semana[sem], semanal.get(semana[sem])+1);
            }else{
                semanal.put(semana[sem], 0);
            }
        }
        
        return semanal;
    }
    
    private Map<String,Integer> historicoDiario(List<Status> tuits, String mes){
        return null;
    }
            
}
