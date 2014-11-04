/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Cora
 */
public class Shipyard implements Serializable{
    private final int MAX_SHIELD_NUM = 5;
    private final int MAX_WEAPON_NUM = 5;
    private Planet planet;
    private HashMap<String,Shield> shieldStock;
    private HashMap<String,Weapon> weaponStock;
    
    private static final HashMap<String,Shield> defaultShields = new HashMap<>();
        static{
            defaultShields.put("Name1", new Shield("Name1"));
            defaultShields.put("Name2", new Shield("Name2"));
            defaultShields.put("Name3", new Shield("Name3"));
            defaultShields.put("Name4", new Shield("Name4"));
            defaultShields.put("Name5", new Shield("Name5"));
            defaultShields.put("Name6", new Shield("Name6"));
            defaultShields.put("Name7", new Shield("Name7"));
            defaultShields.put("Name8", new Shield("Name8"));
        }
        
        private static final HashMap<String,Weapon> defaultWeapons = new HashMap<>();
        static{
            defaultWeapons.put("Name1", new Weapon("Name1"));
            defaultWeapons.put("Name2", new Weapon("Name2"));
        }
    
    public Shipyard(Planet hostPlanet){
        planet = hostPlanet;
        Random rand = new Random();
        shieldStock = defaultShields;
        weaponStock = defaultWeapons;
        //limit based on planet tech level
        
        // something for spaceships too
    }
    
    
    
    private HashMap<String,Shield> getShieldsFromTechLevel(int techLevel){
        HashMap<String,Shield> map = new HashMap<>();
        
        
        return map;
    }
    
    public HashMap<String, Shield> getShieldStock(){
        return shieldStock;
    }
    
    public HashMap<String, Weapon> getWeaponStock(){
        return weaponStock;
    }
    
    public Set<String> getShieldNames(){
        return shieldStock.keySet();
    }
    
    public Set<String> getWeaponNames(){
        return weaponStock.keySet();
    }
    
    public Shield getShield(String name){
        return shieldStock.get(name);
    }
    
    public Weapon getWeapon(String name){
        return weaponStock.get(name);
    }
    
    
    /*
    for personal testing of new methods/numbers, delete later
    */
    public static void main(String[] args){
        Planet p = new Planet();
        Shipyard s = new Shipyard(p);
        Set<String> keys = s.getShieldNames();
        for(String key : keys){
            System.out.println(key);
            System.out.println(s.getShield(key).getHealth());
            System.out.println(s.getShield(key).getPrice());
            System.out.println(s.getShield(key).getTechLevel());
        }
        
        System.out.println();
        System.out.println("Remove 1");
        System.out.println();
        
        s.getShieldStock().remove("Name1");
        keys = s.getShieldNames();
        for(String key : keys){
            System.out.println(key);
            System.out.println(s.getShield(key).getHealth());
            System.out.println(s.getShield(key).getPrice());
            System.out.println(s.getShield(key).getTechLevel());
        }
        
        System.out.println();
        System.out.println("Remove 2");
        System.out.println();
        
        s.getShieldStock().remove("Name2");
        keys = s.getShieldNames();
        for(String key : keys){
            System.out.println(key);
            System.out.println(s.getShield(key).getHealth());
            System.out.println(s.getShield(key).getPrice());
            System.out.println(s.getShield(key).getTechLevel());
        }
        
    }
    
    
}
