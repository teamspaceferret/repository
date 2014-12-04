/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Cora
 */
public class Shipyard implements Serializable {
    public static final int TURRET_IDX = 0;
    public static final int CANNON_IDX = 1;
    public static final int OCULASER_IDX = 2;
    public static final int PLATE_IDX = 0;
    public static final int MATTER_CLOAK_IDX = 1;
    
    private Planet planet;
    private Weapon[] weaponStock;
    private Shield[] shieldStock;
    
    private static final HashMap<String, Shield> DEFAULT_SHIELDS =
            new HashMap<>();
        static {
            DEFAULT_SHIELDS.put("Plate", new Shield("Plate", 50, 4));
            DEFAULT_SHIELDS.put("Matter Cloak",
                    new Shield("Matter Cloak", 70, 5));
            DEFAULT_SHIELDS.put("Name3", new Shield("Name3"));
            DEFAULT_SHIELDS.put("Name4", new Shield("Name4"));
            DEFAULT_SHIELDS.put("Name5", new Shield("Name5"));
            DEFAULT_SHIELDS.put("Name6", new Shield("Name6"));
            DEFAULT_SHIELDS.put("Name7", new Shield("Name7"));
            DEFAULT_SHIELDS.put("Name8", new Shield("Name8"));
        }
        
        private static final HashMap<String, Weapon> DEFAULT_WEAPONS =
                new HashMap<>();
        static {
            DEFAULT_WEAPONS.put("Turret", new Weapon("Turret", 10, 4));
            DEFAULT_WEAPONS.put("Dual Cannon",
                    new Weapon("Dual Cannon", 50, 5));
            DEFAULT_WEAPONS.put("Oculaser", new Weapon("Oculaser", 100, 6));
            DEFAULT_WEAPONS.put("Name4", new Weapon("Name4"));
            DEFAULT_WEAPONS.put("Name5", new Weapon("Name5"));
            DEFAULT_WEAPONS.put("Name6", new Weapon("Name6"));
            DEFAULT_WEAPONS.put("Name7", new Weapon("Name7"));
            DEFAULT_WEAPONS.put("Name8", new Weapon("Name8"));
        }
    
    public Shipyard() {
        //Random rand = new Random();
        weaponStock = new Weapon[] {DEFAULT_WEAPONS.get("Turret"),
        DEFAULT_WEAPONS.get("Dual Cannon"), DEFAULT_WEAPONS.get("Oculaser")};
        shieldStock = new Shield[] {DEFAULT_SHIELDS.get("Plate"),
        DEFAULT_SHIELDS.get("Matter Cloak")};
        //limit based on planet tech level
        
        // something for spaceships too
    }
    
    public final Weapon[] getWeaponStock() {
        return weaponStock;
    }
    
    public final Shield[] getShieldStock() {
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
        Shipyard s = new Shipyard();
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
