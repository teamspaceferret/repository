/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

/**
 * This class details all special events that can occur on planets.
 *
 */
public enum SE {
    NONE("None", -1), DROUGHT("Drought", 0), COLD("Cold", 1), WAR("War", 2),
    BOREDOM("Boredom", 3), PLAGUE("Plague", 4),
    LACKOFWORKERS("Lack of workers", 5), CRIMEWAVE("Crime wave", 6),
    STRIKE("Strike", 7), MANYHUNTERS("Many hunters", 8),
    CROPFAIL("Crop failure", 9), HARVEST("Harvest season", 10),
    POLICE("High police presence", 11), LUDDITES("Luddite invasion!", 12),
    STRAIGHTEDGE("Straight-edge invasion!", 13);
    
    private final String NAME;
    private final int ID;
    
    private SE(String name, int id) {
        NAME = name;
        ID = id;
    }
    
    public String getName() {
        return NAME;
    }
    
    public int getID() {
        return ID;
    }
}
