package viewController;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.App;
import controller.ModelFactoryController;
import interfaces.IController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public abstract class ViewController implements IController{
	@FXML
    protected ResourceBundle resources;
    @FXML
    protected URL location;
    protected App application = new App();
    protected ModelFactoryController mfc = ModelFactoryController.getInstance();
	protected void seeMessage(String title, String header, String content, AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(title);
        aler.setHeaderText(header);
        aler.setContentText(content);
        aler.showAndWait();
    }

    protected boolean seeMessageConfirmation(String text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("CONFIRMATION");
        alert.setContentText(text);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public void setApplication(App application) {
    	this.application = application;
    }


}
