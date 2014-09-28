/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.util.HashMap;

/**
 *
 * @author Cora
 */
public class Ship {
    public static final HashMap<String,int[]> types = new HashMap<String,int[]>();
            static{
                types.put("flea",new int[]{10,0,0,0,1,30,4,1,2000,5,2,25,-1,-1,0,1,0});
                types.put("gnat",new int[]{15,1,0,1,1,14,5,2,10000,50,28,100,0,0,0,1,1});
                types.put("firefly",new int[]{20,1,1,1,1,17,5,3,25000,75,20,100,0,0,0,1,1});
                types.put("mosquito",new int[]{15,2,1,1,1,13,5,5,30000,100,20,100,0,1,0,1,1});
                types.put("bumblebee",new int[]{25,1,2,2,2,15,5,7,60000,125,15,100,0,1,0,1,2});
                
            }
    String type;
    boolean hasEscapePod;
    TradeGood[] cargo;
    Weapon[] weapons;
    Shield[] shields;
    Gadget[] gadgets;
    Mercenary[] crew;
    int currentFuelLevel;
    int minTechLevel;
    int fuelCost;
    int price;
    int bounty;
    int occurrence;
    int hullStr;
    int police,pirate,trader;
    int repairCost;
    int size;
   // boolean isPirate, isPolice, isTrader;
    int maxFuelLevel;
    
    public Ship(String type){
        type = type.toLowerCase();
        this.type = type;
        if(type.equals("flea")){
            hasEscapePod = true;
        } else {
            hasEscapePod = false;
        }
        int [] stats = types.get(type);
        cargo = new TradeGood[stats[0]];
        weapons = new Weapon[stats[1]];
        shields = new Shield[stats[2]];
        gadgets = new Gadget[stats[3]];
        crew = new Mercenary[stats[4]];
        currentFuelLevel = stats[5];
        minTechLevel = stats[6];
        fuelCost = stats[7];
        price = stats[8];
        bounty = stats[9];
        occurrence = stats[10];
        hullStr = stats[11];
        police = stats[12];
        pirate = stats[13];
        trader = stats[14];
        repairCost = stats[15];
        size = stats[16];
        
        maxFuelLevel = stats[5];
    }
    
    public TradeGood[] getCargo(){
        return cargo;
    }
    
    public Weapon[] getWeapons(){
        return weapons;
    }
    
    public Shield[] getShields(){
        return shields;
    }
    
    public Gadget[] getGadgets(){
        return gadgets;
    }
    
    public Mercenary[] getCrew(){
        return crew;
    }
    
    public int getFuelLevel(){
        return currentFuelLevel;
    }
    
    public int getMaxFuelLevel(){
        return maxFuelLevel;
    }
    
    public int getMinTechLevel(){
        return minTechLevel;
    }
    
    public int getFuelCost(){
        return fuelCost;
    }
    
    public int getPrice(){
        return price;
    }
    
    public int getBounty(){
        return bounty;
    }
    
    public int getOccurrence(){
        return occurrence;
    }
    
    public int getHullStrength(){
        return hullStr;
    }
    
    public int getPolice(){
        return police;
    }
    
    public int getPirate(){
        return pirate;
    }
    
    public int getTrader(){
        return trader;
    }
    
    public int getRepairCost(){
        return repairCost;
    }
    
    public int getSize(){
        return size;
    }
    
    public boolean addFuel(int addedFuel){
        int newAmt = currentFuelLevel + addedFuel;
        if(newAmt > maxFuelLevel){
            System.out.println("Too much fuel");
            return false;
        } else {
            currentFuelLevel = newAmt;
            return true;
        }
    }
    
    /**
     * Takes the given amount of fuel and subtracts it from the current amount.
     * If this creates a negative amount, the operation does not occur and the method returns false.
     * If this amount is 0 or greater, the operation occurs and the method returns true.
     * @param usedFuel amount of fuel to subtract
     * @return true if the action was successful (if there was enough fuel), false otherwise
     */
    public boolean subtractFuel(int usedFuel){
        int newAmt = currentFuelLevel - usedFuel;
        if(newAmt >= 0){
           currentFuelLevel = newAmt;
           return true;
        } else {
            System.out.println("Not enough fuel");
            return false;
        } 
    }
    
    
    
    
    
    
}
