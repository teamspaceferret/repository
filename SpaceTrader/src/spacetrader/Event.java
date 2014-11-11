package spacetrader;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.Serializable;
import static spacetrader.Event.values;

public enum Event implements Serializable{
    NONE("Nothing", -1), DROUGHT("A drought", 0), COLD("Cold", 1),
    WAR("War", 2), BOREDOM("Boredom", 3), PLAGUE("A plague", 4),
    LACKOFWORKERS("Lack of workers", 5), CRIMEWAVE("A crime wave", 6),
    STRIKE("A strike", 7), MANYHUNTERS("Many hunters", 8),
    CROPFAIL("Crop failure", 9), HARVEST("Harvest season", 10),
    POLICE("High police presence", 11), LUDDITES("A luddite invasion", 12),
    STRAIGHTEDGE("A straight-edge invasion", 13);
    
    private static final Random RAND = new Random();
    private static final List<Event> VALUES = Arrays.asList(values());
    private final String NAME;
    private final int ID;
    
    private Event(final String name, final int id) {
        this.NAME = name;
        this.ID = id;
    }
    
    /**
     * Returns a random event.
     * @return a random event
     */
    public Event randomEvent() {
        return VALUES.get(RAND.nextInt(VALUES.size()));
    }
    
    public static Event[] eventArray() {
        Event[] events = new Event[15];
        events[0] = Event.NONE;
        events[1] = Event.DROUGHT;
        events[2] = Event.COLD;
        events[3] = Event.WAR;
        events[4] = Event.BOREDOM;
        events[5] = Event.PLAGUE;
        events[6] = Event.LACKOFWORKERS;
        events[7] = Event.CRIMEWAVE;
        events[8] = Event.STRIKE;
        events[9] = Event.MANYHUNTERS;
        events[10] = Event.CROPFAIL;
        events[11] = Event.HARVEST;
        events[12] = Event.POLICE;
        events[13] = Event.LUDDITES;
        events[14] = Event.STRAIGHTEDGE;
        return events;
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
     * Returns the up multiplier.
     * @return the up multiplier
     */
    public double getUpMult() {
        if (ID == -1) {
            return 1;
        }
        return Context.EVENT_UP_MULTIPLIER;
    }
    
    /**
     * Returns the down multiplier.
     * @return the down multiplier1
     */
    public double getDownMult() {
        if (ID == -1) {
            return 1;
        }
        return Context.EVENT_DOWN_MULTIPLIER;
    }
    
    /**
     * Returns true if event equals the given event.
     * @param other the given event to compare to
     * @return true if event equals the given event
     */
    public boolean equals(final Event other) {
        return other.getName().equals(this.getName());
    }
}
