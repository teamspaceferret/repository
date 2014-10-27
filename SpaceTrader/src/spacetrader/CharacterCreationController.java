package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import spacetrader.SpaceTrader.ControlledScreen;

public class CharacterCreationController implements ControlledScreen,
        Initializable {
    @FXML private Button cancelButton, confirmButton, engineerButton,
            engineerDecrement, engineerIncrement, fighterButton,
            fighterDecrement, fighterIncrement, investorButton,
            investorDecrement, investorIncrement, okButton, pilotButton,
            pilotDecrement, pilotIncrement, traderButton, traderDecrement,
            traderIncrement;
    @FXML private Label pointsLabel;
    @FXML private TextArea descriptions;
    @FXML private TextField fighterField, engineerField, investorField,
            nameEntry, pilotField, traderField;
    @FXML private Slider engineerSlider, fighterSlider, investorSlider,
            pilotSlider, traderSlider;
    
    ScreensController controller;
    private int pointsRemaining = 15;
    private int[] stats;
    private Player player;
    private String playerName = "";
    

    /**
     * Set the screen parent.
     * @param screenParent the screen parent
     */
    @Override
    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }
    
    /**
     * Initializes the screen.
     */
    @Override
    public void initScreen() {

    }
    
    /**
     * Initializes the controller class.
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
   /**
     * Initializes the Player object, once all stat points are spent and a
     * name has been entered. Also initializes the Universe object.
     *
     */
    public void confirmAction() {
        if (pointsRemaining <= 0 && !playerName.equals("")) {
            stats = new int[5];
            //investor stat in index 0
            stats[0] = Integer.parseInt(investorField.getText());
            //pilot stat in index 1
            stats[1] = Integer.parseInt(pilotField.getText());
            //trader stat in index 2
            stats[2] = Integer.parseInt(traderField.getText());
            //fighter stat in index 3
            stats[3] = Integer.parseInt(fighterField.getText());
            //engineer stat in index 4
            stats[4] = Integer.parseInt(engineerField.getText());
            
            // Create player
            Context.getInstance().getPlayer().setName(playerName);
            Context.getInstance().getPlayer().setStats(stats);
            Context.getInstance().getPlayer().setShip(new Ship("gnat"));
            
            // Create universe
            Context.getInstance().getUniverse().generateUniverse();
            // Set default location
            Context.getInstance().getUniverse().getSolarSystems()[0].getPlanets()[0].setName("Noobville");
            Context.getInstance().getUniverse().getSolarSystems()[0].getPlanets()[0].setGovernment(Government.MONARCHY);
            Context.getInstance().getPlayer().setCurrentPlanet(Context.getInstance().getUniverse().getSolarSystems()[0].getPlanets()[0]);
            
            controller.setScreen("GalaxyMap");
            
        } else if (pointsRemaining > 0 && playerName.equals("")) {
            descriptions.setText("You still have points left to assign, and "
                    + "you still have to enter a name and press OK.");
            
        } else if (pointsRemaining > 0) {
            descriptions.setText("You still have points left to assign.");
            
        } else {
            descriptions.setText("You still have to enter a name "
                    + "and press OK.");
        }
    }
    
    /**
     * Returns to the start screen. Clears all stat fields and
     * resets stat sliders and refunds stat points.
     * 
     */
    
    public void backAction() {
        controller.setScreen("StartScreen");
        playerName = "";
        nameEntry.clear();
        investorField.setText("0");
        investorSlider.setValue(0);
        pilotField.setText("0");
        pilotSlider.setValue(0);
        traderField.setText("0");
        traderSlider.setValue(0);
        fighterField.setText("0");
        fighterSlider.setValue(0);
        engineerField.setText("0");
        engineerSlider.setValue(0);
        descriptions.clear();
        pointsRemaining = 15;
        updatePoints();
    }
    
    /**
     * Sets the Player name. Player name cannot be "".
     * Also greets the Player!
     *
     */
    
    public void setPlayerName() {
        String name = nameEntry.getText();
        if (name.isEmpty()) {
            descriptions.setText("Your name cannot be empty.");
        } else if (!name.matches("^[a-zA-Z'-]+")) {
            descriptions.setText("Your name can only contain letters, dashes, and apostrophes.");
        } else {
            playerName = name;
            descriptions.setText("Hello, " + playerName + "!");
        }
    }
    
    /**
     * Updates the GUI to show how many stat points are left to assign.
     *
     */
    public void updatePoints() {
        pointsLabel.setText("Points remaining: " + pointsRemaining);
    }
    
    /**
     * Returns true if the user has remaining stat points and has not allocated
     * five stat points to a single skill.
     *
     * @param current the current stat points for a single skill
     * @return True if the Player has points left to assign, false otherwise.
     */
    private boolean checkTotals(double current) {
        return pointsRemaining != 0 && current <= 5;
    }
    
    private void adjustStat(Slider slider, TextField field, int adjustment) {
        int current = (int)slider.getValue() + adjustment;
        
        if (checkTotals(current)) {
            pointsRemaining -= adjustment;
            slider.setValue(current);
            field.setText(String.valueOf(current));
            updatePoints();
        }
    }
    
    /**
     * Increments or decrements the appropriate stat slider and field
     */
    public void incrementFighterAction() { adjustStat(fighterSlider, fighterField, 1); }
    public void incrementTraderAction() { adjustStat(traderSlider, traderField, 1); }
    public void incrementPilotAction() { adjustStat(pilotSlider, pilotField, 1); }
    public void incrementEngineerAction() { adjustStat(engineerSlider, engineerField, 1); }
    public void incrementInvestorAction() { adjustStat(investorSlider, investorField, 1); }
    public void decrementFighterAction() { adjustStat(fighterSlider, fighterField, -1); }
    public void decrementTraderAction() { adjustStat(traderSlider, traderField, -1); }
    public void decrementPilotAction() { adjustStat(pilotSlider, pilotField, -1); }
    public void decrementEngineerAction() { adjustStat(engineerSlider, engineerField, -1); }
    public void decrementInvestorAction() { adjustStat(investorSlider, investorField, -1); }
    
    /**
     * Sets the description TextArea to the Fighter stat description.
     * 
     */
    public void fighterDescriptionAction() {
        descriptions.setText("The fighter skill determines how well you handle "
                + "your weapons, in particular, how easy it is for you hit "
                + "another ship. A trader who isn't interested in a pirating "
                + "or bounty hunting career hasn't that much use of this "
                + "skill, but for a pirate it is an absolute must. A targeting "
                + "system will enhance your fighting capabilities.");
    }
    
    /**
     * Sets the description TextArea to the Trader stat description.
     * 
     */
    public void traderDescriptionAction() {
        descriptions.setText("The trader skill determines what prices you must " 
                + "pay for trade goods, ships and equipment. A good trader can "
                + "reduce prices up to 10%. This makes a high trader skill "
                + "invaluable for traders, while pirates have less use for it. "
                + "In the early stages of your life as a trader you might have "
                + "a hard time getting any money if you work with low trading "
                + "capabilities.");
    }
    
    /**
     * Sets the description TextArea to the Pilot stat description.
     * 
     */
    public void pilotDescriptionAction() {
        descriptions.setText("The pilot skill determines how well you pilot "
                + "your ship. A high piloting skill will enable you to flee "
                + "from attacks easily, dodge attacks, and to stay on the "
                + "tail of fleeing ships if you want to keep attacking them. "
                + "This makes the piloting skill important to both traders and "
                + "pirates. A navigating system will enhance your piloting "
                + "capabilities.");
    }
    
    /**
     * Sets the description TextArea to the Engineer stat description.
     * 
     */
    public void engineerDescriptionAction() {
        descriptions.setText("The engineer skill determines how well you keep "
                + "your ship in shape. A good engineer may keep your hull and "
                + "shields intact during a fight, will repair them quicker "
                + "while traveling, and may even enhance your weaponry a bit "
                + "so it does more damage. This makes the engineer skill "
                + "important for both traders and pirates. An auto-repair "
                + "system will enhance the engineering capabilities.");
    }
    
    /**
     * Sets the description TextArea to the Investor stat description.
     * 
     */
    public void investorDescriptionAction() {
        descriptions.setText("The investor skill influences how well you do in "
                + "investing in the galactic markets. Stock prices and "
                + "interest rates are volatile. You can make a killing or you "
                + "can lose your savings, but having a high Investor skill can "
                + "bolster your successes and cushion your losses. Be wary. ");
    }
}
