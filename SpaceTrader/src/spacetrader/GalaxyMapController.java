package spacetrader;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import spacetrader.SpaceTrader.ControlledScreen;

public class GalaxyMapController implements ControlledScreen {
    @FXML private Button travelButton;
    @FXML private Label fuelLabel;
    @FXML private TextArea descriptions;
    @FXML private Canvas canvas;
    
    ScreensController controller;
    Universe universe = Context.getInstance().getUniverse();
    SolarSystem currentlySelected;
    
    /**
     * 
     * @param screenParent
     */
    @Override
    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }
    
    /**
     * Draws current solar systems on galaxy map
     */
    public void drawSolarSystems() {        
        System.out.println(universe.getSolarSystems().length);
        System.out.println(universe.getSolarSystems()[0].getPlanets().length);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        for (SolarSystem solarSystem : universe.getSolarSystems()) {
            gc.setFill(Color.RED);
            gc.fillOval(solarSystem.getCoords().getX(), solarSystem.getCoords().getY(), 10, 10);
        }
        System.out.println(universe.toString());
    }
    
    /**
     * Sets the current available fuel value to be displayed
     */
    public void currentFuel() {
        fuelLabel.setText("Fuel: 1000"); //add fuel value here!
    }
    
    public void onMouseClick(MouseEvent event) {
        //shouldnt loop over all solarSystems after it's found the clicked one
        for(int i = 0; i < universe.getSolarSystems().length; i++) {
            if(((event.getX() <= (universe.getSolarSystems()[i].getCoords().getX() + 10)) && 
                    ((event.getX() >= (universe.getSolarSystems()[i].getCoords().getX()))) &&
                    ((event.getY() <= (universe.getSolarSystems()[i].getCoords().getY() + 10)) &&
                    (event.getY() >= (universe.getSolarSystems()[i].getCoords().getY()))))) {
                currentlySelected = universe.getSolarSystems()[i];
                setDescription(universe.getSolarSystems()[i]);
            }
        }
    }
    
    public void setDescription(SolarSystem solarSystem) {
        //draw indicator of currently selected one
        descriptions.setText("Name: " + solarSystem.getName() + "\n" +
            "Fuel required: " + "\n" + "\n" +
            "Flavor text?");
    }
    
    public void selectSystem() {
        controller.setScreen("Market");
    }
}
