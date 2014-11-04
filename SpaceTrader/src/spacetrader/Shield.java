/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.io.Serializable;
import java.util.Random;


public class Shield implements Serializable{
    
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
    
    public Shield(String name,int health,int techLevel){
        this.techLevel = techLevel;
        this.health = health;
        this.name = name;
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
