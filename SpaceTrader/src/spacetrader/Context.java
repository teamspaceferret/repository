package spacetrader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.Serializable;


public class Context implements Serializable{
    private static Context instance = new Context();
    
    private final Player player = new Player();
    private final Universe universe = new Universe();
    private final Names names;
    
    public static final int BOUNDARY_FULL = 310;
    public static final int BOUNDARY_VISIBLE = 300;
    public static final int DOT_SIZE = 10;
    public static final int MIN_DISTANCE_BETWEEN_PLANETS = 1;
    public static final int MIN_DISTANCE_BETWEEN_SOLAR_SYSTEMS = 10;
    public static final int MIN_PLANETS_PER_SOLAR_SYSTEM = 4;
    public static final int MAX_PLANETS_PER_SOLAR_SYSTEM = 8;
    public static final int MAX_POINTS_PER_SKILL = 5;
    public static final int NUM_SOLAR_SYSTEMS = 20;
    public static final int PLANET_BOUNDARY = 30;
    public static final int UNIVERSE_TO_SOLAR_SYSTEM_RATIO = 10;
    public static final double EVENT_DOWN_MULTIPLIER = 1.25;
    public static final double EVENT_UP_MULTIPLIER = 0.75;
    public static final double FUEL_TO_DISTANCE_RATIO = 2.0;
    public static final double DISTANCE_TO_FUEL_RATIO = 1
            / FUEL_TO_DISTANCE_RATIO;
    
    public static final int PRE_AGRICULTURE = 0;
    public static final int AGRICULTURE = 1;
    public static final int MEDIEVAL = 2;
    public static final int RENAISSANCE = 3;
    public static final int EARLY_INDUSTRIAL = 4;
    public static final int INDUSTRIAL = 5;
    public static final int POST_INDUSTRIAL = 6;
    public static final int HI_TECH = 7;
    
    public static final int STARTING_CREDITS = 1000;
    
    public static final int INVESTOR_ID = 0;
    public static final int PILOT_ID = 1;
    public static final int TRADER_ID = 2;
    public static final int FIGHTER_ID = 3;
    public static final int ENGINEER_ID = 4;
    public static final int STOCK_BASE_VALUE = 7;
    public static final int STOCK_RAND_VALUE = 14;

    public static final double STOCK_RESOURCE_DEC = .45;
    public static final double STOCK_RESOURCE_INC = 1.55;
    public static final double STOCK_EVENT_DEC = .75;
    public static final double STOCK_EVENT_INC = 1.25;
    public static final double TECH_PRICE_MULT = .95;
    
    public static final String[] PRICES =
            new String[]{"1000", "1500", "2000", "2000", "4000", "-1",
                "1000"};
    
    //public static final int NUM_TRADE_GOODS = 10;
    
    public static final String[] SOLAR_SYSTEM_NAMES = {
        "Acamar", "Adahn", "Aldea", "Andevian", "Antedi", "Balosnee", "Baratas",
        "Brax", "Bretel", "Calondia", "Campor", "Capelle", "Carzon", "Castor",
        "Cestus", "Cheron", "Courteney", "Daled", "Damast", "Davlos", "Deneb",
        "Deneva", "Devidia", "Draylon", "Drema", "Endor", "Esmee", "Exo",
        "Ferris", "Festen", "Fourmi", "Frolix", "Gemulon", "Guinifer", "Hades",
        "Hamlet", "Helena", "Hulst", "Iodine", "Iralius", "Janus", "Japori",
        "Jarada", "Jason", "Kaylon", "Khefka", "Kira", "Klaatu", "Klaestron",
        "Korma", "Kravat", "Krios", "Laertes", "Largo", "Lave", "Ligon",
        "Lowry", "Magrat", "Malcoria", "Melina", "Mentar", "Merik", "Mintaka",
        "Montor", "Mordan", "Myrthe", "Nelvana", "Nix", "Nyle", "Odet", "Og",
        "Omega", "Omphalos", "Orias", "Othello", "Parade", "Penthara", "Picard",
        "Pollux", "Quator", "Rakhar", "Ran", "Regulas", "Relva", "Rhymus",
        "Rochani", "Rubicum", "Rutia", "Sarpeidon", "Sefalla", "Seltrice",
        "Sigma", "Sol", "Somari", "Stakoron", "Styris", "Talani", "Tamus",
        "Tantalos", "Tanuga", "Tarchannen", "Terosa", "Thera", "Titan", "Torin",
        "Triacus", "Turkana", "Tyrus", "Umberlee", "Utopia", "Vadera", "Vagra",
        "Vandor", "Ventax", "Xenon", "Xerxes", "Yew", "Yojimbo", "Zalkon",
        "Zuul"
    };
    
    public static final String[] TECH_LEVELS = {
        "Pre-Agriculture", "Agriculture", "Medieval", "Renaissance",
        "Early Industrial", "Industrial", "Post-Industrial", "Hi-Tech"
    };
    
    public static final String[] RESOURCES = {
        "NOSPECIALRESOURCES", "MINERALRICH", "MINERALPOOR", "DESERT",
        "LOTSOFWATER", "RICHSOIL", "POORSOIL", "RICHFAUNA", "LIFELESS",
        "WEIRDMUSHROOMS", "LOTSOFHERBS", "ARTISTIC", "WARLIKE", "PACIFIST"
    };
    
    public static final String[] GOVERNMENTS = {
        "Anarchy", "Capitalist", "Communist", "Confederacy", "Corporate",
        "Cybernetic", "Democracy", "Dictatorship", "Fascist", "Feudal",
        "Military", "Monarchy", "Paficist", "Socialist", "Satori",
        "Technocracy", "Theocracy"
    };
    
    public static final String[] EVENTS = {
        "None", "Drought", "Cold", "War", "Boredom", "Plague",
        "Lack of workers", "Crime wave", "Strike", "Many hunters",
        "Crop failure", "Harvest season", "High police presence",
        "Luddite invasion!", "Straight-edge invasion!"
    };
    
    private SolarSystem focus;
    private int[] stock = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    
    /**
     * Returns the context instance.
     * @return the context instance
     */
    public static Context getInstance() {
        return instance;
    }
    
    /**
     * Constructs a Context with the default names array
     */
    public Context() {
        this.names = new Names();
    }
    
    /**
     * Returns the player.
     * @return the player
     */
    public Player getPlayer() {
        return this.player;
    }
    
    /**
     * Returns the universe.
     * @return the universe
     */
    public Universe getUniverse() {
        return this.universe;
    }
    
    /**
     * Returns the names list.
     * @return the names list
     */
    public Names getNames() {
        return this.names;
    }
    
    /**
     * Returns the focus.
     * @return the focus.
     */
    public SolarSystem getFocus() {
        return this.focus;
    }
    
    /**
     * Returns the stock.
     * @return stock
     */
    public int[] getStock() {
        return this.stock;
    }
    
    /**
     * Set the focus.
     * @param focus new focus
     */
    public void setFocus(SolarSystem focus) {
        this.focus = focus;
    }
    
    /**
     * Set the stock.
     * @param stocks new stock
     */
    public void setStock(int[] stocks) {
        this.stock = stocks;
    }
    
    /**
     * Create the save file for the instance of the game
     */
    public void saveContextBinary() {
        try {
            FileOutputStream file = new FileOutputStream("data.bin");
            try (ObjectOutputStream out = new ObjectOutputStream(file)) {
                //instance:
                out.writeObject(this);

            } catch (IOException ex) {
                Logger.getLogger(Context.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Context.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
    
    /**
     * Load the saved file for a game's instance
     */
    public void loadContextBinary() {
        try {
            FileInputStream file = new FileInputStream("data.bin");
            try (ObjectInputStream in = new ObjectInputStream(file)) {
                Context loaded = (Context) in.readObject();
                instance = loaded;
                
            }
        } catch (FileNotFoundException ex){
            System.out.println("No Save Data");
            //pop up window? 
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Context.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
}
