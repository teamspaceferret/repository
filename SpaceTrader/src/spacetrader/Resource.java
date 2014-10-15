package spacetrader;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.Serializable;

public enum Resource implements Serializable{
    NOSPECIALRESOURCES("No special resources", 0),
    MINERALRICH("Mineral rich", 1), MINERALPOOR("Mineral poor", 2),
    DESERT("Desert", 3), LOTSOFWATER("Lots of water", 4),
    RICHSOIL("Rich soil", 5), POORSOIL("Poor soil", 6),
    RICHFAUNA("Rich fauna", 7), LIFELESS("Lifeless", 8),
    WEIRDMUSHROOMS("Weird mushrooms", 9), LOTSOFHERBS("Lots of herbs", 10),
    ARTISTIC("Artistic", 11), WARLIKE("Warlike", 12), PACIFIST("Pacifist", 13);
    
    private static final Random rand = new Random();
    private static final List<Resource> VALUES = Arrays.asList(values());
    private final String NAME;
    private final int ID;
    
    private Resource(String name, int id) {
        NAME = name;
        ID = id;
    }
    
    /**
     * Returns a random resource.
     * @return a random resource
     */
    public Resource randomResource() {
        return VALUES.get(rand.nextInt(VALUES.size()));
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
    
    public boolean equals(Resource other) {
        if (other.getName().equals(this.getName())){
            return true;
        }
        return false;
    }
}
