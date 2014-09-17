package spacetrader;


import spacetrader.SpaceTrader.ControlledScreen;
import javafx.fxml.*;
import javafx.scene.control.*;

public class CharacterCreationController implements ControlledScreen {
    @FXML
    private TextField nameEntry;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button okButton;
    @FXML
    private Button fighterButton;
    @FXML
    private Button traderButton;
    @FXML
    private Button pilotButton;
    @FXML
    private Button engineerButton;
    @FXML
    private Button investorButton;
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
    private TextField investorField;
    @FXML
    private Slider fighterSlider;
    @FXML
    private Slider traderSlider;
    @FXML
    private Slider pilotSlider;
    @FXML
    private Slider engineerSlider;
    @FXML
    private Slider investorSlider;
    @FXML
    private Button fighterIncrement;
    @FXML
    private Button traderIncrement;
    @FXML
    private Button pilotIncrement;
    @FXML
    private Button engineerIncrement;
    @FXML
    private Button investorIncrement;
    @FXML
    private Button fighterDecrement;
    @FXML
    private Button traderDecrement;
    @FXML
    private Button pilotDecrement;
    @FXML
    private Button engineerDecrement;
    @FXML
    private Button investorDecrement;
    
    private int pointsRemaining = 28;
    private String playerName = "";
    private int[] stats;
    private Player player;
    
    ScreensController controller;
    
    public Player getPlayer() {
        if (player != null) {
            return player;
        } else {
            System.out.println("Player hasn't been created yet (null).");
            return player;
        }
    }
    
    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }
    
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
            Context.getInstance().getPlayer().setStates(stats);
            
            System.out.println(Context.getInstance().getPlayer().toString());
            controller.setScreen("GameScreen");
        } else if (pointsRemaining > 0 && playerName.equals("")) {
            descriptions.setText("You still have points left to assign, and you still have to enter"
                    + " a name and press OK.");
        } else if (pointsRemaining > 0) {
            descriptions.setText("You still have points left to assign.");
        } else {
            descriptions.setText("You still have to enter a name and press OK.");
        }
    }
    
    public void backAction() {
        controller.setScreen("StartScreen");
    }
    
    public void setPlayerName() {
        String tempName = playerName;
        playerName = nameEntry.getText();
        if (playerName.length() > 0 && !playerName.equals(tempName)) {
            descriptions.setText("Hello, " + playerName + "!");
        }
    }
    
    //Will implement stat randomization on Saturday (9/13)
    /*public void randomizeButtonAction() {
        System.out.println("Stats randomized!");
    }*/
    
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
        if (checkTotals() && current <= 10) {
            fighterSlider.setValue(current);
            pointsRemaining--;
            current = fighterSlider.getValue();
            fighterField.setText("" + ((int)current));
            upr();
        } else {
            current = fighterSlider.getValue();
            upr();
        }
    }
    
    public void incrementTraderAction() {
        double current = traderSlider.getValue();
        current += 1;
        if (checkTotals() && current <= 10) {
            traderSlider.setValue(current);
            pointsRemaining--;
            current = traderSlider.getValue();
            traderField.setText("" + ((int)current));
            upr();
        } else {
            current = traderSlider.getValue();
            upr();
        }
    }
    
    public void incrementPilotAction() {
        double current = pilotSlider.getValue();
        current += 1;
        if (checkTotals() && current <= 10) {
            pilotSlider.setValue(current);
            pointsRemaining--;
            current = pilotSlider.getValue();
            pilotField.setText("" + ((int)current));
            upr();
        } else {
            current = pilotSlider.getValue();
            upr();
        }
    }
    
    public void incrementEngineerAction() {
        double current = engineerSlider.getValue();
        current += 1;
        if (checkTotals() && current <= 10) {
            engineerSlider.setValue(current);
            pointsRemaining--;
            current = engineerSlider.getValue();
            engineerField.setText("" + ((int)current));
            upr();
        } else {
            current = engineerSlider.getValue();
            upr();
        }
    }
    
    public void incrementInvestorAction() {
        double current = investorSlider.getValue();
        current += 1;
        if (checkTotals() && current <= 10) {
            investorSlider.setValue(current);
            pointsRemaining--;
            current = investorSlider.getValue();
            investorField.setText("" + ((int)current));
            upr();
        } else {
            current = investorSlider.getValue();
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
    
    public void decrementInvestorAction() {
        double current = investorSlider.getValue();
        if (current != 0) {
            pointsRemaining++;
        }
        current -= 1;
        investorSlider.setValue(current);
        current = investorSlider.getValue();
        investorField.setText("" + ((int)current));
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
    
    public void investorDescriptionAction() {
        descriptions.setText("The investor skill influences how well you do in investing in"
                + " the galactic markets. Stock prices and interest rates are volatile. You"
                + " can make a killing or you can lose your savings, but having a high"
                + "Investor skill can bolster your successes and cushion your losses. Be wary. ");
    }
}
