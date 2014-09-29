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
    public static final HashMap<String,int[]> types = new HashMap<>();
            static{
                types.put("flea",new int[]{10,0,0,0,1,30,4,1,2000,5,2,25,-1,-1,0,1,0});
                types.put("gnat",new int[]{15,1,0,1,1,14,5,2,10000,50,28,100,0,0,0,1,1});
                types.put("firefly",new int[]{20,1,1,1,1,17,5,3,25000,75,20,100,0,0,0,1,1});
                types.put("mosquito",new int[]{15,2,1,1,1,13,5,5,30000,100,20,100,0,1,0,1,1});
                types.put("bumblebee",new int[]{25,1,2,2,2,15,5,7,60000,125,15,100,0,1,0,1,2});
                
            }
    String type;
    boolean hasEscapePod;
    HashMap<TradeGood,Integer> cargo;
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
    int maxCargoSlots;
    int usedCargoSlots;
    
    public Ship(String type){
        type = type.toLowerCase();
        this.type = type;
        if(type.equals("flea")){
            hasEscapePod = true;
        } else {
            hasEscapePod = false;
        }
        int [] stats = types.get(type);
        cargo = new HashMap<>();
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
        maxCargoSlots = stats[0];
        usedCargoSlots = 0;
        //add 0 of each type of item to the cargo bay
        setUpCargoBay();
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
    
    public boolean isCargoEmpty(){
        if(usedCargoSlots == 0){
            return true;
        } else return false;
    }
    
    /*
    public boolean addToCargo(TradeGood item){
        int newCargoAmt = usedCargoSlots + 1;
        if(newCargoAmt > maxCargoSlots){
            System.out.println("Not enough cargo space");
            return false;
        } else {
          usedCargoSlots = newCargoAmt;
          if (cargo.containsKey(item)){
            int amt = cargo.get(item);
            amt += 1;
            cargo.put(item,amt);
          } else {
              cargo.put(item, 1);
          }
          return true;
        }
    }
    */
    
    /**
     * Adds the given number of the given item to cargo
     * If there are not enough cargo slots, it does not add and returns false
     * If there are enough slots, the operation is successful and the method returns true
     * @param item the trade good to add to cargo
     * @param number the number of the trade good to add
     * @return true if addition was successful, false otherwise
     */
    public boolean addToCargo(TradeGood item, int number){
        int newCargoAmt = usedCargoSlots + number;
        if(newCargoAmt > maxCargoSlots){
            System.out.println("Not enough cargo space");
            return false;
        } else {
          usedCargoSlots = newCargoAmt;
          
          int amt = cargo.get(item);
          amt += number;
          cargo.put(item,amt);
          
          return true;
        }
    }
    
    /*
    public boolean removeFromCargo(TradeGood item){
        //if cargo item does not exist in the cargo
        //if there are 0 of the item
        //tries to remove too many
        int newCargoAmt = usedCargoSlots - 1;
        if (!cargo.containsKey(item) || cargo.get(item) == 0){
            System.out.println("Item not in cargo");
            return false;
        } else {
            int itemAmt = cargo.get(item);
            int newItemAmt = itemAmt-1;
            cargo.put(item, newItemAmt);
            usedCargoSlots = newCargoAmt;
            return true;
        }
    }
    */
    
    /**
     * Removes the given number of the given item from the ships cargo
     * If the good is not in cargo, or the user tries to remove too many of that item, it does not remove any and returns false
     * If the correct number of the given item can be removed, the operation is successful and the method returns true
     * @param item the trade good to remove
     * @param number the number of the trade good to remove
     * @return true if removal is successful, false otherwise
     */
    public boolean removeFromCargo(TradeGood item, int number){
        //if cargo item does not exist in the cargo
        //if there are 0 of the item
        //tries to remove too many
        int newCargoAmt = usedCargoSlots - number;
        
        int itemAmt = cargo.get(item);
        int newItemAmt = itemAmt-number;
        if(newItemAmt < 0){
            System.out.println("Not enough of that item");
            return false;
        }
        cargo.put(item, newItemAmt);
        usedCargoSlots = newCargoAmt;
        return true;
    }
    
    
    //Getters
    
    public HashMap<TradeGood,Integer> getCargo(){
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
    
    //private helper methods:
    private void setUpCargoBay(){
        int NUM = 0;
        //add num of each type of trade good to the cargo bay
        cargo.put(TradeGood.FIREARMS, NUM);
        cargo.put(TradeGood.FOOD, NUM);
        cargo.put(TradeGood.FURS, NUM);
        cargo.put(TradeGood.GAMES, NUM);
        cargo.put(TradeGood.MACHINES, NUM);
        cargo.put(TradeGood.MEDICINE, NUM);
        cargo.put(TradeGood.NARCOTICS, NUM);
        cargo.put(TradeGood.ORE, NUM);
        cargo.put(TradeGood.ROBOTS, NUM);
        cargo.put(TradeGood.WATER, NUM);
    }
        
}
