package spacetrader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.Serializable;

class Names implements Serializable{
    private List<String> names;
    // Setting this equal to Context.SOLAR_SYSTEM_NAMES doesn't work???
    private final static String[] namesArray = {
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
        "Zuul"};

    /**
     * Returns the name pool as an array.
     */
    public Names() {
        this.names = new ArrayList(Arrays.asList(namesArray));
    }
    
    /**
     * Gets a random name from the name pool.
     * @return a random name
     */
    public String getRandomName() {
        if (this.names.isEmpty()) {
            this.names = new ArrayList(Arrays.asList(namesArray));
        }
        Random rand = new Random();
        return this.names.remove(rand.nextInt(this.names.size()));
    }
}
