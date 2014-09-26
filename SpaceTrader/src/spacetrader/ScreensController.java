package spacetrader;

import java.io.IOException;
import java.util.HashMap;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import spacetrader.SpaceTrader.ControlledScreen;

public class ScreensController extends StackPane {
    private final HashMap<String, Node> screens = new HashMap<>();
    private final HashMap<String, ControlledScreen> controllers = new HashMap<>();
    
    /**
     * Constructs a ScreensController.
     */
    public ScreensController() {
        super();
    }
    
    /**
     * Add the screen to the collection.
     *
     * @param name   name of the screen
     * @param screen screen to be added
     */
    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }
    
    /**
     * Add the controller to the collection.
     *
     * @param name       name of the screen
     * @param controller controller to be added
     */
    public void addController(String name, ControlledScreen controller) {
        controllers.put(name, controller);
    }
    
    /**
     * Returns the Node with the appropriate name.
     *
     * @param name name of the screen
     * @return     screen with the given name
     */
    public Node getScreen(String name) {
        return screens.get(name);
    }
    
    /**
     * Loads the FXML file, add the screen to the screens collection and
     * finally injects the screenPane to the controller.
     *
     * @param name     name of the screen
     * @param resource name of the FXML file
     * @return         true if the screen loads successfully
     */
    public boolean loadScreen(String name, String resource) {
        Parent loadScreen;
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
        
        try {
            loadScreen = myLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        ControlledScreen myScreenController = ((ControlledScreen) myLoader.getController());
        myScreenController.setScreenParent(this);
        addScreen(name, loadScreen);
        addController(name, myScreenController);
        return true;
    }
       
    /**
     * This method tries to display the screen with the given name.
     * First it makes sure the screen has been already loaded.  Then if there is
     * more than one screen the new screen is been added second, and then the
     * current screen is removed.  If there isn't any screen being displayed,
     * the new screen is just added to the root.
     *
     * @param name name of the screen
     * @return     true if the screen sets successfully
     */
    public boolean setScreen(final String name) {
        // Screen loaded
        if (screens.get(name) != null) {
            controllers.get(name).initScreen();
            
            final DoubleProperty opacity = opacityProperty();
                
            if (!getChildren().isEmpty()) {
                Timeline fade;
                fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(100), (ActionEvent t) -> {
                            // Remove the displayed screen
                            getChildren().remove(0); 
                            // Add the screen
                            getChildren().add(0, screens.get(name));
                            Timeline fadeIn = new Timeline(
                                    new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                    new KeyFrame(new Duration(100), new KeyValue(opacity, 1.0)));
                            fadeIn.play();
                        }, new KeyValue(opacity, 0.0))
                );
                fade.play();
            } else {
                setOpacity(0.0);
                // No other screen being displayed
                getChildren().add(screens.get(name));
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(100), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            return true;
        } else {
            System.out.println("Screen hasn't been loaded");
            return false;
        }
    }
    
    /**
     * This method will remove the screen with the given name from the
     * collection of screens.
     * 
     * @param name name of the screen
     * @return     true if the screen unloads successfully
     */
    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.out.println("Screen doesn't exist");
            return false;
        } else {
            return true;
        }
    }
}
