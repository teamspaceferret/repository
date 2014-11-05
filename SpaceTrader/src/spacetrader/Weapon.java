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
public class Weapon implements Serializable{
    private String name;
    private int damage;
    private int price;
    private int techLevel;
   
    private Random random = new Random();
    
    public Weapon(){
        int max = 8; //double check these vals
        int min = 4;
        techLevel = random.nextInt((max - min) + 1) + 1; //max tech level
        
        damage = (techLevel * 3) + random.nextInt(10);
        name = generateName();
        price = generatePrice(damage * 5);
        
    }
    
    public Weapon(String name){
        int max = 8; //double check these vals
        int min = 4;
        techLevel = random.nextInt((max - min) + 1) + min; //max tech level
        
        damage = (techLevel * 3) + random.nextInt(10);
        this.name = name;
        price = generatePrice(damage * 5);
        
    }
    
    public Weapon(String name,int damage,int techLevel){
        this.techLevel = techLevel;
        this.damage = damage;
        this.name = name;
        price = generatePrice(damage);
        
    }
    
    private int generatePrice(int seed){
        return seed * 10 + random.nextInt(seed);
        //return price;
    }
    
    private String generateName(){
        String randName = "";
        //random number w/ array len as seed 
        //then as name is used, remove it from the list
        //look at Names class for example
        return randName;
    }
    
    public int getDamage(){
        return damage;
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
