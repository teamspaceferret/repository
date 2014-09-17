package spacetrader;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import spacetrader.SpaceTrader.ControlledScreen;

public class GameScreenController implements ControlledScreen {
    @FXML private Button button;
    @FXML private Label nameLabel;
    @FXML private Label investorLabel;
    @FXML private Label pilotLabel;
    @FXML private Label traderLabel;
    @FXML private Label fighterLabel;
    @FXML private Label engineerLabel;
    
    ScreensController controller;
    
    /**
     *
     * @param screenParent
     */
    @Override
    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }
    
    public void setLabels() {
        Player player = Context.getInstance().getPlayer();
        nameLabel.setText("Name: " + player.getName());
        investorLabel.setText("Investor: " + String.valueOf(player.getInvestor()));
        pilotLabel.setText("Pilot: " + String.valueOf(player.getPilot()));
        traderLabel.setText("Trader: " + String.valueOf(player.getTrader()));
        fighterLabel.setText("Fighter: " + String.valueOf(player.getFighter()));
        engineerLabel.setText("Engineer: " + String.valueOf(player.getEngineer()));
    }
}
