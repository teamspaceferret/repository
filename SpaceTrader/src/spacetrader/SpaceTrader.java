package spacetrader;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SpaceTrader extends Application {

/**
 * interface of ControlledScreen
 */
    public interface ControlledScreen {
        void setScreenParent(ScreensController screenPage); 
        void initScreen();
    }
  
/**
 * Loading the start screen, game screen, character creation, and setting the screen to start screen. 
 * @throws java.lang.Exception
 */
    @Override
    public void start(Stage primaryStage) throws Exception {
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen("StartScreen", "StartScreen.fxml");
        mainContainer.loadScreen("CharacterCreation", "CharacterCreation.fxml");
        mainContainer.loadScreen("GalaxyMap", "GalaxyMap.fxml");
        mainContainer.loadScreen("SolarMap", "SolarMap.fxml");
        mainContainer.loadScreen("TravelEvent", "TravelEvent.fxml");
        mainContainer.loadScreen("PlanetScreen", "PlanetScreen.fxml");
        mainContainer.loadScreen("Market", "Market.fxml");
        mainContainer.loadScreen("Shipyard", "Shipyard.fxml");
        
        mainContainer.loadScreen("OptionsScreen", "OptionsScreen.fxml");
        
        mainContainer.setScreen("StartScreen");
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
/**
 * Main method that JavaFX should never execute.
 * @param args
 */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void stop(){
        //shutdown the sound threads
        //Context.getInstance().getSoundManager().shutdown();
        SoundManager.getSoundManager().shutdown();
    }
}
