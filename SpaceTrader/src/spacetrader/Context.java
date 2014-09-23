package spacetrader;

public class Context {
    private static final Context instance = new Context();
    
    private Player player = new Player();
    private Universe universe = new Universe();
    
    public static final int NUM_SOLAR_SYSTEMS = 6;
    public static final int MIN_DISTANCE_BETWEEN_PLANETS = 200;
    public static final int MIN_PLANETS_PER_SOLAR_SYSTEM = 4;
    public static final int MAX_PLANETS_PER_SOLAR_SYSTEM = 8;
    
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
    
    /**
     * Gets the instance of the game
     * @return the context instance
     */
    public static Context getInstance() {
        return instance;
    }
    
    /**
     * Gets the player of the game's instance
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }
    
    /**
     * Gets the universe of the game's instance
     * @return the universe
     */
    public Universe getUniverse() {
        return universe;
    }
    
    
}
