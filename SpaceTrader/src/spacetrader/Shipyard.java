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
    private final int MAX_SHIELD_NUM = 5;//for 
    private final int MAX_WEAPON_NUM = 5;
    public static final int TURRET_IDX = 0;
    public static final int CANNON_IDX = 1;
    public static final int OCULASER_IDX = 2;
    public static final int PLATE_IDX = 0;
    public static final int MATTER_CLOAK_IDX = 1;
    
    private Planet planet;
    private Weapon[] weaponStock;
    private Shield[] shieldStock;
    
    private static final HashMap<String,Shield> defaultShields = new HashMap<>();
        static{
            defaultShields.put("Plate",new Shield("Plate",50,4));
            defaultShields.put("Matter Cloak", new Shield("Matter Cloak", 70, 5));
            defaultShields.put("Name3", new Shield("Name3"));
            defaultShields.put("Name4", new Shield("Name4"));
            defaultShields.put("Name5", new Shield("Name5"));
            defaultShields.put("Name6", new Shield("Name6"));
            defaultShields.put("Name7", new Shield("Name7"));
            defaultShields.put("Name8", new Shield("Name8"));
        }
        
        private static final HashMap<String,Weapon> defaultWeapons = new HashMap<>();
        static{
            defaultWeapons.put("Turret", new Weapon("Turret",10,4));
            defaultWeapons.put("Dual Cannon", new Weapon("Dual Cannon",50,5));
            defaultWeapons.put("Oculaser", new Weapon("Oculaser",100,6));
            defaultWeapons.put("Name4", new Weapon("Name4"));
            defaultWeapons.put("Name5", new Weapon("Name5"));
            defaultWeapons.put("Name6", new Weapon("Name6"));
            defaultWeapons.put("Name7", new Weapon("Name7"));
            defaultWeapons.put("Name8", new Weapon("Name8"));
        }
    
    public Shipyard(Planet hostPlanet){
        planet = hostPlanet;
        //Random rand = new Random();
        weaponStock = new Weapon[] {defaultWeapons.get("Turret"), defaultWeapons.get("Dual Cannon"), defaultWeapons.get("Oculaser")};
        shieldStock = new Shield[] {defaultShields.get("Plate"), defaultShields.get("Matter Cloak")};
        //limit based on planet tech level
        
        // something for spaceships too
    }
    
    public Weapon[] getWeaponStock() {
        return weaponStock;
    }
    
    public Shield[] getShieldStock() {
        return shieldStock;
    }
/* 
    public HashMap<String,Shield> getShieldsFromTechLevel(int techLevel){
        HashMap<String,Shield> map = new HashMap<>();
        //get keyset for default
        //loop through keyset for things
        //place things of wanted techlevel or lower in map
        
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
*/ 
    
    /*
    for personal testing of new methods/numbers, delete later
    */
    /*public static void main(String[] args){
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
    */
    
    
}
