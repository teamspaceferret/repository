package spacetrader;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import static spacetrader.Event.values;

public enum TravelEvent {
    NONE("None", -1, "Nothing eventful occurs on your journey."),
    FUELLOSS("Stolen fuel", 0, "A thief has siphoned off some of your fuel. You don't notice until they are long gone. \n\n"),
    CARGOLOSS("Stolen cargo", 1, "A thief breaks into your cargo hold to steal some of your cargo! He escapes "
            + "before the can catch him. \n\n"),
    CREDITLOSS("Broken equipment", 2, "One of your heat capacitors is malfunctioning and needs to be replaced. \n\n"),
    FUELWIN("Fuel discovered", 3, "You discover canisters of fuel floating in space. They appear undamaged, "
            + "so you fuel up as much as you can. \n\n"),
    CARGOWIN("Cargo discovered", 4, "You find a crate of supplies floating in space. It appears undamaged "
            + "and is filled with games. \n\n"),
    CREDITWIN("You won the lottery!", 5, "You won the galactic scratch-off lottery! \n\n"),
    PRICEINCREASE("Radical price increase", 6, "An event happens to radically increase a price! Do this later.");
    
    private static final Random rand = new Random();
    private static final List<TravelEvent> VALUES = Arrays.asList(values());
    private final String NAME;
    private final int ID;
    private String TEXT;
    
    Universe universe = Context.getInstance().getUniverse();
    Player player = Context.getInstance().getPlayer();
    
    private TravelEvent(String name, int id, String text) {
        NAME = name;
        ID = id;
        TEXT = text;
    }
    
    /**
     * Chooses a random travel event, applies effects, and returns event text
     * @return the text representation of what happened during the event
     */
    public String randomTravelEvent() {
        TravelEvent selected = VALUES.get(rand.nextInt(VALUES.size()));
        //edit a copy of the base text, adding on the random effects to be printed
        String eventText = selected.TEXT;
        //do the things based on which event happened
        if (selected.ID == 0) {
            //random amount between 0 and (1/4 total OR 50) <- whichever is lower
            int max;
            if (((player.getShip().getFuelLevel())/4) > 50) {
                max = 50;
            } else {
                max = ((player.getShip().getFuelLevel())/4);
            }
            int fuelToRemove = rand.nextInt(max);
            player.getShip().subtractFuel(fuelToRemove);
            eventText += "You lose " + fuelToRemove + " units of fuel.";
        } else if (selected.ID == 1) {
            if (player.getShip().getCurrentUsedCargoSlots() > 0) {
                TradeGood stolenGood = null;
                //go through all cargo to find which has quantity > 0, steal one
                Map<TradeGood, Integer> map = player.getShip().getCargoClone();
                for (Entry<TradeGood, Integer> entry : map.entrySet()) {
                    if (entry.getValue() > 0) {
                        System.out.println(entry.getKey());
                        stolenGood = entry.getKey();
                    }
                }
                //remove one cargo
                if (stolenGood != null) {
                    player.getShip().removeFromCargo(stolenGood, 1);
                    eventText += "You find that one box of " + stolenGood.name() + " is missing.";
                } else {
                    //this shouldnt happen, but in case no cargo is found...
                    eventText += "You check the hold, but find that nothing is out of place. "
                            + "You guess that you got lucky this time.";
                }
            } else {
                eventText += "The joke is on him though. You didn't have any cargo anyway!";
            }
        } else if (selected.ID == 2) {
            //would be cool to randomize which part breaks
            //random amount between 0 and (1/4 total OR 250) <- whichever is lower
            int max = 0;
            if (((player.getCredits())/4) > 250) {
                max = 250;
            } else {
                max = ((player.getCredits())/4);
            }
            int creditsToRemove = rand.nextInt(max);
            player.removeCredits(creditsToRemove);
            eventText += "You lose " + creditsToRemove + " credits in repair costs.";
        } else if (selected.ID == 3) {
            //would be cool to randomize how much fuel
            //if at max fuel, notify player
            if (player.getShip().getMaxFuelLevel() == player.getShip().getFuelLevel()) {
                eventText += "However, you are already at max fuel. You leave the canisters as they are.";
            }
            //if you can, add all
            else if ((player.getShip().getMaxFuelLevel() - player.getShip().getFuelLevel()) >= 50) {
                player.getShip().addFuel(50);
                eventText += "You empty the canisters, gaining 50 fuel.";
            } else {
                //else add what you can
                int fuelAdded = player.getShip().getMaxFuelLevel() - player.getShip().getFuelLevel();
                player.getShip().addFuel(fuelAdded);
                eventText += "You gain " + fuelAdded + " fuel and leave the rest as is.";
            }
        } else if (selected.ID == 4) {
            //would be cool to randomize which good
            //if room in cargo hold, add cargo
            if (player.getShip().getCurrentUsedCargoSlots() != player.getShip().getMaxCargoSlots()) {
                player.getShip().addToCargo(TradeGood.GAMES, 1);
                eventText += "You load the cargo and continue on your way.";
            } else {
                eventText += "However, you have no room in your cargo hold so you leave it alone.";
            }
        } else if (selected.ID == 5) {
            //would be cool to randomize how many credits
            player.addCredits(100);
            eventText += "You gain 100 credits.";
        } else if (selected.ID == 6) {
            
            
            
            
            
            eventText += "No but seriously, do this later.";
            
            
            
            
            
        }
        //return appropriately editted text
        return eventText;
    }
    
    /**
     * Returns event name.
     * @return event name
     */
    public String getName() {
        return NAME;
    }
    
    /**
     * Returns event id.
     * @return event id
     */
    public int getID() {
        return ID;
    }
    
    /**
     * Returns event name.
     * @return event name
     */
    public String getText() {
        return TEXT;
    }
    
    /**
     * Returns true if event equals the given event.
     * @param other the given event to compare to
     * @return true if event equals the given event
     */
    public boolean equals(Event other) {
        if (other.getName().equals(this.getName())){
            return true;
        }
        return false;
    }
}
