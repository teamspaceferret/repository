package spacetrader;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import spacetrader.SpaceTrader.ControlledScreen;

public class GameScreenController implements ControlledScreen {
    @FXML private Button travelButton;
    @FXML private Label fuelLabel;
    @FXML private TextArea descriptions;
    @FXML private Canvas canvas;
    
    ScreensController controller;
    
    /**
     *
     * @param screenParent
     */
    @Override
    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }
    
    public void drawSolarSystems() {
        Universe universe = Context.getInstance().getUniverse();
        
        System.out.println(universe.getSolarSystems().length);
        System.out.println(universe.getSolarSystems()[0].getPlanets().length);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        /*
        for(int i = 0; i < universe.getSolarSystems().length; i++) {
            for(int j = 0; j < universe.getSolarSystems()[i].getPlanets().length; j++) {
                gc.setFill(Color.RED);
                gc.fillOval(universe.getSolarSystems()[i].getPlanets()[j].getX(),
                    universe.getSolarSystems()[i].getPlanets()[j].getY(), 5, 5);
            }
        }*/
        for (SolarSystem solarSystem : universe.getSolarSystems()) {
            gc.setFill(Color.RED);
            gc.fillOval(solarSystem.getCoords().getX(), solarSystem.getCoords().getY(), 10, 10);
        }
        
        System.out.println(universe.toString());
    }
    
    public void currentFuel() {
        fuelLabel.setText("Fuel: 1000"); //add fuel value here!
    }
}
