package spacetrader;

public enum Event {
    NONE("None", -1), DROUGHT("Drought", 0), COLD("Cold", 1), WAR("War", 2),
    BOREDOM("Boredom", 3), PLAGUE("Plague", 4),
    LACKOFWORKERS("Lack of workers", 5), CRIMEWAVE("Crime wave", 6),
    STRIKE("Strike", 7), MANYHUNTERS("Many hunters", 8),
    CROPFAIL("Crop failure", 9), HARVEST("Harvest season", 10),
    POLICE("High police presence", 11), LUDDITES("Luddite invasion!", 12),
    STRAIGHTEDGE("Straight-edge invasion!", 13);
    
    private final String NAME;
    private final int ID;
    
    private Event(String name, int id) {
        NAME = name;
        ID = id;
    }
    
    /**
     *
     * @return
     */
    public String getName() {
        return NAME;
    }
    
    /**
     *
     * @return
     */
    public int getID() {
        return ID;
    }
}
