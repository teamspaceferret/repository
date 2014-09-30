package spacetrader;

public class Context {
    private static final Context instance = new Context();
    
    private final Player player = new Player();
    private final Universe universe = new Universe();
    private final Names names = new Names();
    
    public static final int BOUNDARY = 300;
    public static final int MIN_DISTANCE_BETWEEN_PLANETS = 30;
    public static final int MIN_PLANETS_PER_SOLAR_SYSTEM = 4;
    public static final int MAX_PLANETS_PER_SOLAR_SYSTEM = 8;
    public static final int NUM_SOLAR_SYSTEMS = 30;
    
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
        "WEIRDMUSHROOMS", "LOTSOFHERBS", "ARTISTIC", "WARLIKE", "PACIFIST" // Pacifist?
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
    
    /**
     * Returns the context instance.
     * @return the context instance
     */
    public static Context getInstance() {
        return instance;
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
}
