/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Cora
 */
public class Shield implements Serializable{
    //make a shield name pool
    //look at how planet does it
    private String[] namesArray = { "Name1","Name2","Name3","Name4","Name5","Name6",
     "Name7","Name8","Name9"                            
    };
    
    private String name;
    private int health;
    private int price;
    private int techLevel;
   
    private Random random = new Random();
    
    public Shield(){
        int max = 8; //check these vals
        int min = 4;
        techLevel = random.nextInt((max - min) + 1) + 1;
        
        health = (techLevel * 10) + random.nextInt(20);
        name = generateName();
        price = generatePrice(health);
        
    }
    
    public Shield(String name){
        int max = 8; //check these vals
        int min = 4;
        techLevel = random.nextInt((max - min) + 1) + min;
        
        health = (techLevel * 10) + random.nextInt(20);
        this.name = name;
        price = generatePrice(health);
    }
    
    public Shield(int planetTechLevel){
        int max = planetTechLevel;
        int min = 4;
        techLevel = random.nextInt((max - min) + 1) + min;
        
        health = (techLevel * 10) + random.nextInt(20);
        name = generateName();
        price = generatePrice(health);
        
    }
    
    private int generatePrice(int seed){
        return seed * 10 + random.nextInt(seed);
        //return price;
    }
    
    private String generateName(){
        String randName = "";
        
        return randName;
    }
    
    public int getHealth(){
        return health;
    }
    
    public int getTechLevel(){
        return techLevel;
    }
    
    public String getName(){
        return name;
    }
    
    public int getPrice(){
        return price;
    } 
}
