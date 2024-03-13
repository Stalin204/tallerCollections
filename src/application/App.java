package application;

import java.io.IOException;

import library.Printer;
import exception.FileNotFoundException;
import exception.JavaFXException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import viewController.ViewController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class App extends Application {
    // Variables
    private Stage primaryStage;
    private Printer printer;
    // Methods
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    /**
     * @start():
     * runs the first window through the primaryStage variable<br>
     * and creates the only company with the singleton method
     */
    public void start(Stage primaryStage) {
        // Set primary stage
    	printer = new Printer<>("Printer APP");
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("My Application");
        this.primaryStage.initStyle(StageStyle.UNIFIED);
        try {
            showWindow("Application", this.primaryStage);
        } catch (FileNotFoundException | JavaFXException e) {
            printer.print(e);
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showWindow(String window, Stage stage) throws FileNotFoundException, JavaFXException {
        String path = getPath(window, PathType.VIEW);
        if (path == null) {
            throw new FileNotFoundException("Check the view path for the window " + window);
        }
        try {
            // Load main view
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource(path));
            AnchorPane rootLayout = loader.load();
            // Set the root of the FXML file
            loader.setRoot(rootLayout);
            ViewController controller = loader.getController();
            controller.setApplication(this);
            // Launch scene
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            printer.print(e);
            e.printStackTrace();
            throw new JavaFXException("Failed to load the window " + window);
        }
    }

    public String getPath(String window, PathType pathType) {
        String controllersPath = "/controller/";
        String fxmlPath = "/windows/";
        String controllersFxmlPath = "viewController.";
        switch (pathType) {
            case CONTROLLER:
                break;
            case VIEW:
                return fxmlPath + "Window" + window + "View.fxml";
            case VIEW_CONTROLLER:
                return controllersFxmlPath + window;
            default:
                return null;
        }
        return null;
    }

}
