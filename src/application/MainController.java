package application;

import exception.FileNotFoundException;
import exception.JavaFXException;

public class MainController {
    /*
     * Configuration of the window to launch
     */
    String windowName = "PrincipalMain";

    //-----------------------------------------------------------------Main Method----------------------------------------------------------------------
    App application;

    public MainController(App application) {
        super();
        this.application = application;
    }

    public void run() {
        try {
            application.showWindow(windowName, application.getPrimaryStage());
        } catch (FileNotFoundException | JavaFXException e) {
            e.printStackTrace();
        }
    }
}
