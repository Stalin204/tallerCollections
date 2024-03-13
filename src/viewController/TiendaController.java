package viewController;

import application.App;
import application.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TiendaController extends ViewController {

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Button ButtonCarroCompras;

    @FXML
    private Button ButtonComprar;

    @FXML
    private Label LabelHistorialVentas;

    @FXML
    private TextField TextCantidadCompra;

    @FXML
    private TableColumn<?, ?> columnCantidadProducto;

    @FXML
    private TableColumn<?, ?> columnCodigoPrpducto;

    @FXML
    private TableColumn<?, ?> columnNombreProducto;

    @FXML
    private TableColumn<?, ?> columnPrecioProducto;

    @FXML
    private TableColumn<?, ?> idnumeroProdcuto;

    @FXML
    private TableView<?> tablaProductos;

    @FXML
    void ActionButtonCarroCompras(ActionEvent event) {

    }

    @FXML
    void ActionButtonComprar(ActionEvent event) {

    }

	@Override
	public void setApplication(App aplicacion) {
		this.application = aplicacion;

	}

	@Override
	public void intiView() {
		// TODO Esbozo de método generado automáticamente

	}

	@Override
	public void initDataBinding() {
		// TODO Esbozo de método generado automáticamente

	}

}
