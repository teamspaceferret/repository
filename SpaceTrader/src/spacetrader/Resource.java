package spacetrader;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.Serializable;
import static spacetrader.Resource.values;

public enum Resource implements Serializable {
    NOSPECIALRESOURCES("No special resources", 0),
    MINERALRICH("Mineral rich", 1), MINERALPOOR("Mineral poor", 2),
    DESERT("Desert", 3), LOTSOFWATER("Lots of water", 4),
    RICHSOIL("Rich soil", 5), POORSOIL("Poor soil", 6),
    RICHFAUNA("Rich fauna", 7), LIFELESS("Lifeless", 8),
    WEIRDMUSHROOMS("Weird mushrooms", 9), LOTSOFHERBS("Lots of herbs", 10),
    ARTISTIC("Artistic", 11), WARLIKE("Warlike", 12), PACIFIST("Pacifist", 13);
    
    private static final Random rand = new Random();
    private static final List<Resource> VALUES = Arrays.asList(values());
    private final String name;
    private final int id;
    private double priceMultUp, priceMultDown;
    
    private Resource(String name, int id) {
        this.name = name;
        this.id = id;
        if (id == 0) {
            priceMultUp = 1.0;
            priceMultDown = 1.0;
        } else {
            priceMultUp = 1.15;
            priceMultDown = 0.85;
        }
    }
    
    /**
     * Returns a random resource.
     * @return a random resource
     */
    public Resource randomResource() {
        return VALUES.get(rand.nextInt(VALUES.size()));
    }

    /**
     * Returns an array of available Resource conditions.
     * @return array of resource conditions
     */
    public static Resource[] resourceArray() {
        Resource[] resources = new Resource[14];
        resources[0] = Resource.NOSPECIALRESOURCES;
        resources[1] = Resource.MINERALRICH;
        resources[2] = Resource.MINERALPOOR;
        resources[3] = Resource.DESERT;
        resources[4] = Resource.LOTSOFWATER;
        resources[5] = Resource.RICHSOIL;
        resources[6] = Resource.POORSOIL;
        resources[7] = Resource.RICHFAUNA;
        resources[8] = Resource.LIFELESS;
        resources[9] = Resource.WEIRDMUSHROOMS;
        resources[10] = Resource.LOTSOFHERBS;
        resources[11] = Resource.ARTISTIC;
        resources[12] = Resource.WARLIKE;
        resources[13] = Resource.PACIFIST;
        return resources;
    }

    /**
     * Returns resource name.
     * @return resource name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns resource id.
     * @return resource id
     */
    public int getID() {
        return id;
    }
    
    public double getUpMult() {
        return priceMultUp;
    }
    
    public double getDownMult() {
        return priceMultDown;
    }
    
    public boolean equals(Resource other) {
        if (other.getName().equals(this.getName())){
            return true;
        }
        return false;
    }
}
