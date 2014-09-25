package spacetrader;

public enum Resource {
    NOSPECIALRESOURCES("No special resources", 0),
    MINERALRICH("Mineral rich", 1), MINERALPOOR("Mineral poor", 2),
    DESERT("Desert", 3), LOTSOFWATER("Lots of water", 4),
    RICHSOIL("Rich soil", 5), POORSOIL("Poor soil", 6),
    RICHFAUNA("Rich fauna", 7), LIFELESS("Lifeless", 8),
    WEIRDMUSHROOMS("Weird mushrooms", 9), LOTSOFHERBS("Lots of herbs", 10),
    ARTISTIC("Artistic", 11), WARLIKE("Warlike", 12), PACIFIST("Pacifist", 13);
    
    private final String NAME;
    private final int ID;
    
    private Resource(String name, int id) {
        NAME = name;
        ID = id;
    }
    
    /**
     * Returns resource name.
     * @return resource name
     */
    public String getName() {
        return NAME;
    }
    
    /**
     * Returns resource id.
     * @return resource id
     */
    public int getID() {
        return ID;
    }
}
