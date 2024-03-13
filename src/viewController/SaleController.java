package viewController;

import java.net.URL;
import java.util.ResourceBundle;

import controller.ModelFactoryController;
import exception.FileNotFoundException;
import exception.JavaFXException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Sale;
import model.ShoppingCart;

public class SaleController extends ViewController {

	ModelFactoryController modelfactory;

	ObservableList<Sale> listSale =FXCollections.observableArrayList();

	Sale select;

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label IdCliente;

    @FXML
    private TableColumn<Sale,Integer> columnCantidad;

    @FXML
    private TableColumn<Sale,String> columnIdProducto;

    @FXML
    private TableColumn<Sale,String> columnNombre;

    @FXML
    private TableColumn<Sale,Double> columnPrecio;

    @FXML
    private TableColumn<Sale,Double> columnSubtotal;

    @FXML
    private Label nombreCliente;

    @FXML
    private TableView<Sale> tableVentasProducto;

    @FXML
    private Label totalCompra;

    @FXML
    private Label dateSale;

    @FXML
    private Button ButtonBackToStore;

    @FXML
    private Button ButtonHistorial;

    /**
     * we start the window
     */
    @FXML
	public void initialize() {
    	modelfactory= ModelFactoryController.getInstance();
    	init();

	}

    /**
     * we start data window
     */
	private void init() {
		getData();
        initDataBinding();
        tableVentasProducto.getItems().clear();
        tableVentasProducto.setItems(listSale);
        fillData();
        emptyCartList();
    }

	/**
	 * cuando se realiza la venta vaciamos el carro de compra
	 * para cuando se haga una nueva venta
	 */
private void emptyCartList() {
		modelfactory.emptyCartList();

	}
/**
 * llneamos los labels vacios por afuera de la tabla con los datos
 */
private void fillData() {
	IdCliente.setText(listSale.get(0).getCodeCustomer());
	nombreCliente.setText(listSale.get(0).getNameCustomer());
	totalCompra.setText(listSale.get(0).getTotal()+"");
	dateSale.setText(listSale.get(0).getDate());
	}

/**
 * we take the data from the list
 */
	private void getData() {
		listSale.addAll(modelfactory.getListaSale());

	}





	@Override
	public void intiView() {
		// TODO Auto-generated method stub

	}

	/**
	 * we fill the table
	 */
	@Override
	public void initDataBinding() {
		columnIdProducto.setCellValueFactory(new PropertyValueFactory("codeProduct"));
		columnNombre.setCellValueFactory(new PropertyValueFactory("nameProduct"));
		columnPrecio.setCellValueFactory(new PropertyValueFactory("price"));
		columnCantidad.setCellValueFactory(new PropertyValueFactory("quantity"));
		columnSubtotal.setCellValueFactory(new PropertyValueFactory("subtotal"));;
	}

	@FXML
    void actionBackToStore(ActionEvent event) {
		try {
			application.showWindow("PrincipalMain", application.getPrimaryStage());
		} catch (FileNotFoundException | JavaFXException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
    }

    @FXML
    void actionHistorial(ActionEvent event) {
    	try {
			application.showWindow("SaleHistory", application.getPrimaryStage());
		} catch (FileNotFoundException | JavaFXException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
    }

}