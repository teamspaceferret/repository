package spacetrader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.Serializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Context implements Serializable{
    private static Context instance = new Context();
    
    private Player player = new Player();
    private Universe universe = new Universe();
    private Names names = new Names();
   
    
    private final SoundManager soundManager = new SoundManager(6);
    
    public static final int BOUNDARY = 300;
    public static final int MIN_DISTANCE_BETWEEN_PLANETS = 15;
    public static final int MIN_PLANETS_PER_SOLAR_SYSTEM = 4;
    public static final int MAX_PLANETS_PER_SOLAR_SYSTEM = 8;
    public static final int NUM_SOLAR_SYSTEMS = 10;
    
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
     * returns the sound manager
     * @return soundManager
     */
    public SoundManager getSoundManager(){
        return this.soundManager;
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
    
    public void saveContextBinary(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.bin"))) {
            //player
            //out.writeObject(player);
            //solarsystem focus
            //out.writeObject(focus);
            //stock??
            //out.writeObject(stock);
            //universe
            //out.writeObject(universe);
                    
            
            //instance:
            out.writeObject(this);
            
        } catch (IOException ex) {
            Logger.getLogger(Context.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadContextBinary(){
        try {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.bin"))) {
                Context loaded = (Context) in.readObject();
                instance = loaded;
                
            }
        } catch (FileNotFoundException ex){
            System.out.println("No Save Data");
            //System.exit(0);
            //popup window
            /*final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(primaryStage);
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("This is a Dialog"));
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                dialog.setScene(dialogScene);
                dialog.show();
            */    
        } catch (IOException ex) {
            Logger.getLogger(Context.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Context.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
