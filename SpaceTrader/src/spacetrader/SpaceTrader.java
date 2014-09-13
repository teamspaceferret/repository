package spacetrader;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SpaceTrader extends Application {
    
    public interface ControlledScreen {
        public void setScreenParent(ScreensController screenPage); 
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen("StartScreen", "StartScreen.fxml");
        mainContainer.loadScreen("CharacterCreation", "CharacterCreation.fxml");
        mainContainer.loadScreen("GameScreen", "GameScreen.fxml"); 
        mainContainer.setScreen("StartScreen");
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
