package spacetrader;


import spacetrader.SpaceTrader.ControlledScreen;
import javafx.fxml.*;
import javafx.scene.control.*;

public class CharacterCreationController implements ControlledScreen {
    
    //note: the descriptions of the skills used in this document are taken
    //directly from the final project description, change if necessary
    
    @FXML
    private Button fighterButton;
    @FXML
    private Button traderButton;
    @FXML
    private Button pilotButton;
    @FXML
    private Button engineerButton;
    @FXML
    private TextArea descriptions;
    @FXML
    private Label pointsRemainingGUI;
    @FXML
    private TextField fighterField;
    @FXML
    private TextField traderField;
    @FXML
    private TextField pilotField;
    @FXML
    private TextField engineerField;
    @FXML
    private Slider fighterSlider;
    @FXML
    private Slider traderSlider;
    @FXML
    private Slider pilotSlider;
    @FXML
    private Slider engineerSlider;
    @FXML
    private Button fighterIncrement;
    @FXML
    private Button traderIncrement;
    @FXML
    private Button pilotIncrement;
    @FXML
    private Button engineerIncrement;
    @FXML
    private Button fighterDecrement;
    @FXML
    private Button traderDecrement;
    @FXML
    private Button pilotDecrement;
    @FXML
    private Button engineerDecrement;
    
    ScreensController controller;
    public int pointsRemaining = 24;
    
    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }
    
    public void randomizeButtonAction() {
        System.out.println("Stats randomized!");
    }
    
    public void upr() {
        pointsRemainingGUI.setText("Points remaining: " + pointsRemaining);
    }
    
    public boolean checkTotals() {
        if (pointsRemaining == 0) {
            System.out.println("You're out of points.");
            return false;
        }
        return true;
    }
    
    public void incrementFighterAction() {
        double current = fighterSlider.getValue();
        current += 1;
        if (checkTotals()) {
            fighterSlider.setValue(current);
            pointsRemaining--;
            current = fighterSlider.getValue();
            fighterField.setText("" + ((int)current));
            upr();
        } else {
            upr();
        }
    }
    
    public void incrementTraderAction() {
        double current = traderSlider.getValue();
        current += 1;
        if (checkTotals()) {
            traderSlider.setValue(current);
            pointsRemaining--;
            current = traderSlider.getValue();
            traderField.setText("" + ((int)current));
            upr();
        } else {
            upr();
        }
    }
    
    public void incrementPilotAction() {
        double current = pilotSlider.getValue();
        current += 1;
        if (checkTotals()) {
            pilotSlider.setValue(current);
            pointsRemaining--;
            current = pilotSlider.getValue();
            pilotField.setText("" + ((int)current));
            upr();
        } else {
            upr();
        }
    }
    
    public void incrementEngineerAction() {
        double current = engineerSlider.getValue();
        current += 1;
        if (checkTotals()) {
            engineerSlider.setValue(current);
            pointsRemaining--;
            current = engineerSlider.getValue();
            engineerField.setText("" + ((int)current));
            upr();
        } else {
            upr();
        }
    }
    
    public void decrementFighterAction() {
        double current = fighterSlider.getValue();
        if (current != 0) {
            pointsRemaining++;
        }
        current -= 1;
        fighterSlider.setValue(current);
        current = fighterSlider.getValue();
        fighterField.setText("" + ((int)current));
        upr();
    }
    
    public void decrementTraderAction() {
        double current = traderSlider.getValue();
        if (current != 0) {
            pointsRemaining++;
        }
        current -= 1;
        traderSlider.setValue(current);
        current = traderSlider.getValue();
        traderField.setText("" + ((int)current));
        upr();
    }
    
    public void decrementPilotAction() {
        double current = pilotSlider.getValue();
        if (current != 0) {
            pointsRemaining++;
        }
        current -= 1;
        pilotSlider.setValue(current);
        current = pilotSlider.getValue();
        pilotField.setText("" + ((int)current));
        upr();
    }
    
    public void decrementEngineerAction() {
        double current = engineerSlider.getValue();
        if (current != 0) {
            pointsRemaining++;
        }
        current -= 1;
        engineerSlider.setValue(current);
        current = engineerSlider.getValue();
        engineerField.setText("" + ((int)current));
        upr();
    }
    
    public void fighterDescriptionAction() {
        descriptions.setText("The fighter skill determines how well you handle your weapons,"
                + " in particular, how easy it is for you hit another ship. A trader who isn't"
                + " interested in a pirating or bounty hunting career hasn't that much use of"
                + " this skill, but for a pirate it is an absolute must. A targeting system"
                + " will enhance your fighting capabilities. ");
    }
    
    public void traderDescriptionAction() {
        descriptions.setText("The trader skill determines what prices you must pay for "
                + "trade goods, ships and equipment. A good trader can reduce prices up to "
                + "10%. This makes a high trader skill invaluable for traders, while pirates "
                + "have less use for it. In the early stages of your life as a trader you "
                + "might have a hard time getting any money if you work with low trading "
                + "capabilities.");
    }
    
    public void pilotDescriptionAction() {
        descriptions.setText("The pilot skill determines how well you pilot your ship."
                + " A high piloting skill will enable you to flee from attacks easily,"
                + " dodge attacks, and to stay on the tail of fleeing ships if you want"
                + " to keep attacking them. This makes the piloting skill important to"
                + " both traders and pirates. A navigating system will enhance your piloting"
                + " capabilities.");
    }
    
    public void engineerDescriptionAction() {
        descriptions.setText("The engineer skill determines how well you keep your ship in "
                + "shape. A good engineer may keep your hull and shields intact during a "
                + "fight, will repair them quicker while traveling, and may even enhance "
                + "your weaponry a bit so it does more damage. This makes the engineer "
                + "skill important for both traders and pirates. An auto-repair system "
                + "will enhance the engineering capabilities.");
    }
}
