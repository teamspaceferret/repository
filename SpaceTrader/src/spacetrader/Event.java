package spacetrader;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum Event {
    NONE("None", -1), DROUGHT("Drought", 0), COLD("Cold", 1), WAR("War", 2),
    BOREDOM("Boredom", 3), PLAGUE("Plague", 4),
    LACKOFWORKERS("Lack of workers", 5), CRIMEWAVE("Crime wave", 6),
    STRIKE("Strike", 7), MANYHUNTERS("Many hunters", 8),
    CROPFAIL("Crop failure", 9), HARVEST("Harvest season", 10),
    POLICE("High police presence", 11), LUDDITES("Luddite invasion!", 12),
    STRAIGHTEDGE("Straight-edge invasion!", 13);
    
    private static final Random rand = new Random();
    private static final List<Event> VALUES = Arrays.asList(values());
    private final String NAME;
    private final int ID;
    
    private Event(String name, int id) {
        NAME = name;
        ID = id;
    }
    
    /**
     * Returns a random event.
     * @return a random event
     */
    public Event randomEvent() {
        return VALUES.get(rand.nextInt(VALUES.size()));
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
}
