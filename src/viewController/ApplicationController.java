package viewController;
import java.net.URL;
import java.util.ResourceBundle;

import application.App;
import application.MainController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class ApplicationController extends ViewController {
	private int estadoAnimacion = 1;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label txtMensajes;

    @FXML
    private Circle bola1;

    @FXML
    private Circle bola3;

    @FXML
    private Circle bola2;

    @FXML
    private ProgressBar barraProgreso;

    @FXML
    private Circle bola4;

    @FXML
    private Label txtEntrada;


    @FXML
    private Button btnComenzar;



    @FXML
    void comenzarAction(ActionEvent event) {
    	iniciarPantallaCarga();
    	initiThreads();

    }


    private void iniciarPantallaCarga() {
		configurarElementos();

	}


	private void configurarElementos() {
		this.btnComenzar.setVisible(false);
		this.txtEntrada.setVisible(false);
		this.txtMensajes.setVisible(true);
		this.barraProgreso.setVisible(true);
	}


	@FXML
    void initialize() {

    }

	@Override
	public void setApplication(App aplicacion) {
		this.application = aplicacion;

	}
	public void initiThreads() {

			mostrarAnimacion();
		}
	@Override
	public void intiView() {

	}
	private void mostrarAnimacion() {
	    int duracion = 450; // Duraci�n de la transici�n en milisegundos

	    if (estadoAnimacion == 13) {
	        mostrarTodasBolas();
	        // Restablece el valor de ProgressBar cuando la animaci�n est� completa
	        Platform.runLater(() -> barraProgreso.setProgress(1.0));
	        // Agrega un EventHandler para abrir la otra ventana al final de la animaci�n
	        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(duracion), e -> {
	        	MainController main = new MainController(this.application);
	        	main.run();
	        }));
	        timeline.setCycleCount(1);
	        timeline.play();
	    } else {
	        switch (estadoAnimacion) {
	            case 1:
	            case 5:
	            case 9:
	                mostrarBola1();
	                break;
	            case 2:
	            case 6:
	            case 10:
	                mostrarBola2();
	                break;
	            case 3:
	            case 7:
	            case 11:
	                mostrarBola3();
	                break;
	            case 4:
	            case 8:
	            case 12:
	                mostrarBola4();
	                break;
	        }

	        // Calcula el progreso de la animaci�n en funci�n del estadoAnimacion
	        double progreso = (estadoAnimacion - 1) / 12.0;
	        // Actualiza el valor de ProgressBar en el hilo de la aplicaci�n de JavaFX
	        Platform.runLater(() ->barraProgreso.setProgress(progreso));

	        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(duracion), e -> {
	            estadoAnimacion++;
	            mostrarAnimacion();
	        }));
	        timeline.setCycleCount(1);
	        timeline.play();
	    }
	}


	public void mostrarBola1() {
		Platform.runLater(()-> {
    	bola1.setVisible(false);
    	bola2.setVisible(false);
    	bola3.setVisible(false);
    	bola4.setVisible(false);
    	bola1.setVisible(true);});
    }

	public void mostrarBola2() {
		Platform.runLater(()-> {
    	bola1.setVisible(false);
    	bola2.setVisible(false);
    	bola3.setVisible(false);
    	bola4.setVisible(false);
    	bola2.setVisible(true);});
    }
    public void mostrarBola3() {
    	Platform.runLater(()-> {
    	bola1.setVisible(false);
    	bola2.setVisible(false);
    	bola3.setVisible(false);
    	bola4.setVisible(false);
    	bola3.setVisible(true);});
    }
    public void mostrarBola4() {
    	Platform.runLater(()-> {
    	bola1.setVisible(false);
    	bola2.setVisible(false);
    	bola3.setVisible(false);
    	bola4.setVisible(false);
    	bola4.setVisible(true);});
    }

    public void mostrarTodasBolas() {
    	Platform.runLater(()-> {
    	bola1.setVisible(true);
    	bola2.setVisible(true);
    	bola3.setVisible(true);
    	bola4.setVisible(true);});
    }

	@Override
	public void initDataBinding() {
		// TODO Esbozo de m�todo generado autom�ticamente

	}
}
