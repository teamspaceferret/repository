package spacetrader;

import java.util.HashMap;

public class Ship {
    public static final HashMap<String,int[]> types = new HashMap<>();
            static{
                types.put("flea",new int[]{10,0,0,0,1,30,4,1,2000,5,2,25,-1,-1,0,1,0,10});
                types.put("gnat",new int[]{15,1,0,1,1,14,5,2,10000,50,28,100,0,0,0,1,1,10});
                types.put("firefly",new int[]{20,1,1,1,1,17,5,3,25000,75,20,100,0,0,0,1,1,10});
                types.put("mosquito",new int[]{15,2,1,1,1,13,5,5,30000,100,20,100,0,1,0,1,1,10});
                types.put("bumblebee",new int[]{25,1,2,2,2,15,5,7,60000,125,15,100,0,1,0,1,2,10});
                
            }

    private String type;
    private boolean hasEscapePod;
    private HashMap<TradeGood,Integer> cargo;
    private Weapon[] weapons;
    private Shield[] shields;
    private Gadget[] gadgets;
    private Mercenary[] crew;
    private int currentFuelLevel;
    private int minTechLevel;
    private int fuelCost;
    private int price;
    private int bounty;
    private int occurrence;
    private int hullStr;
    private int police,pirate,trader;
    private int repairCost;
    private int size;
    private int range; //the radius of the range bounding circle, potentially
    
    private int maxFuelLevel;
    private int maxCargoSlots;
    private int usedCargoSlots;
    

    /**
     * Creates a ship with the given type and sets up the default stats
     * @param type the type of ship to create
     */
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
        range = stats[17];
        
        maxFuelLevel = stats[5];
        maxCargoSlots = stats[0];
        usedCargoSlots = 0;
        System.out.println("USED CARGO SLOTS: " + usedCargoSlots);
        System.out.println("MAX CARGO SLOTS: " + maxCargoSlots);
        //add 0 of each type of item to the cargo bay
        setUpCargoBay();
    }
    
    /**
     * Adds the given amount of fuel to the ship
     * If this were to add more fuel than the ship can hold, the addition does not occur and returns false
     * Otherwise, fuel is added and returns true
     * @param addedFuel the amount of fuel to add to the ship
     * @return true if addition was successful, false otherwise
     */
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
    
    /**
     * Gets whether or not the cargo is empty
     * @return true if cargo bay is empty, false otherwise
     */
    public boolean isCargoEmpty(){
        if(usedCargoSlots == 0){
            return true;
        } else return false;
    }
    
    /**
     * Gets whether or not the cargo is full
     * @boolean true if the bay is full, false otherwise
     */
    public boolean isCargoFull() {
        if (usedCargoSlots == maxCargoSlots) {
            return true;
        } 
        return false;
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
    
    /**
     * Gets the number of the given TradeGood in the ship's current cargo
     * @param item TradeGood to get the number of
     * @return the number of the given TradeGood
     */
    public int getCargoStock(TradeGood item){
        return cargo.get(item);
    }
    
    
    //Getters
    /**
     * Gets the ship's cargo bay
     * @return ship's cargo
     */
    public HashMap<TradeGood,Integer> getCargo(){
        return cargo;
    }
    
    /**
     * get a clone of the ship's cargo
     * @return a clone of the ship's cargo bay
     */
    public HashMap<TradeGood,Integer> getCargoClone(){
        HashMap<TradeGood,Integer> clone = new HashMap<>();
        clone.put(TradeGood.WATER, cargo.get(TradeGood.WATER));
        clone.put(TradeGood.FOOD, cargo.get(TradeGood.FOOD));
        clone.put(TradeGood.FURS, cargo.get(TradeGood.FURS));
        clone.put(TradeGood.FIREARMS, cargo.get(TradeGood.FIREARMS));
        clone.put(TradeGood.GAMES, cargo.get(TradeGood.GAMES));
        clone.put(TradeGood.MACHINES, cargo.get(TradeGood.MACHINES));
        clone.put(TradeGood.MEDICINE, cargo.get(TradeGood.MEDICINE));
        clone.put(TradeGood.NARCOTICS, cargo.get(TradeGood.NARCOTICS));
        clone.put(TradeGood.ORE, cargo.get(TradeGood.ORE));
        clone.put(TradeGood.ROBOTS, cargo.get(TradeGood.ROBOTS));
        
        return clone;
    }
    
    /**
     * Gets the ship's weapons
     * @return ship's weapons
     */
    public Weapon[] getWeapons(){
        return weapons;
    }
    
    /**
     * Gets the ship's shields
     * @return ship's shields
     */
    public Shield[] getShields(){
        return shields;
    }
    
    /**
     * Gets the ship's gadgets
     * @return ship's gadgets
     */
    public Gadget[] getGadgets(){
        return gadgets;
    }
    
    /**
     * Gets the ship's crew
     * @return ship's crew
     */
    public Mercenary[] getCrew(){
        return crew;
    }
    
    /**
     * Gets the ship's current amount of fuel
     * @return ship's current amount of fuel
     */
    public int getFuelLevel(){
        return currentFuelLevel;
    }
    
    /**
     * Gets the ship's maximum fuel level
     * @return ship's maximum fuel level
     */
    public int getMaxFuelLevel(){
        return maxFuelLevel;
    }
    
    /**
     * Gets the ship's minimum tech level
     * @return ship's minimum tech level
     */
    public int getMinTechLevel(){
        return minTechLevel;
    }
    
    /**
     * Gets the ship's fuel cost
     * @return ship's fuel cost
     */
    public int getFuelCost(){
        return fuelCost;
    }
    
    /**
     * Gets the ship's base price
     * @return ship's base price
     */
    public int getPrice(){
        return price;
    }
    
    /**
     * Gets the ship's base bounty
     * @return ship's base bounty
     */
    public int getBounty(){
        return bounty;
    }
    
    /**
     * Gets the ship's occurrence
     * @return ship's occurrence
     */
    public int getOccurrence(){
        return occurrence;
    }
    
    /**
     * Gets the ship's hull strength
     * @return ship's hull strength
     */
    public int getHullStrength(){
        return hullStr;
    }
    
    /**
     * Gets the ship's police chance
     * @return ship's police chance
     */
    public int getPolice(){
        return police;
    }
    
    /**
     * Gets the ship's pirate chance
     * @return ship's pirate chance
     */
    public int getPirate(){
        return pirate;
    }
    
    /**
     * Gets the ship's trader chance
     * @return ship's trader chance
     */
    public int getTrader(){
        return trader;
    }
    
    /**
     * Gets the ship's base repair cost
     * @return ship's base repair cost
     */
    public int getRepairCost(){
        return repairCost;
    }
    
    /**
     * Gets the ship's size
     * @return ship's size
     */
    public int getSize(){
        return size;
    }
    
    /**
     * Gets the ship's range radius
     * @return ship's range radius
     */
    public int getRange(){
        return range;
    }
    
    /**
     * Gets the number of cargo slots currently used
     * @return number of currently used cargo slots
     */
    public int getCurrentUsedCargoSlots(){
        return usedCargoSlots;
    }
    
    /**
     * Gets the total number of cargo slots
     * @return the total number of cargo slots
     */
    public int getMaxCargoSlots(){
        return maxCargoSlots;
    }
    
    /**
     * Set the ship's cargo bay stock to the given cargo stock
     * and the used cargo slots to the given number
     * @param cargoStock the cargo stock to set the ships cargo stock
     * @param num the number to set the used cargo slots as
     */
    public void setCargo(HashMap<TradeGood,Integer> cargoStock, int num){
        this.cargo = cargoStock;
        this.usedCargoSlots = num;
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
