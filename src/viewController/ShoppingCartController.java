package viewController;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.omg.CORBA.INITIALIZE;

import application.MainController;
import controller.ModelFactoryController;
import exception.FileNotFoundException;
import exception.JavaFXException;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Product;
import model.SaleDetail;
import model.ShoppingCart;

public class ShoppingCartController extends ViewController {

	ModelFactoryController modelfactory;

	ObservableList<ShoppingCart> cartList;

	ShoppingCart select;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ButtonBorrar;

    @FXML
    private Button ButtonCantidad;

    @FXML
    private Button ButtonCompraTotal;

    @FXML
	private Label LabelHistorialVentas;

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Button ButtonDelete;

    @FXML
    private Button ButtonQuantity;

    @FXML
    private Button ButtonTotalPurchase;

    @FXML
    private TableColumn<ShoppingCart,String> ProductQuantity;

    @FXML
    private Label LabelSalesHistory;

    @FXML
    private TextField textQuantity;

    @FXML
    private TableColumn<ShoppingCart,String> ProductName;

    @FXML
    private TableColumn<ShoppingCart,String> ProductNumber;

    @FXML
    private TableColumn<ShoppingCart,String> subtotalColumnPurchase;

    @FXML
    private TableColumn<ShoppingCart,String> purchaseNumberId;

    @FXML
    private TableView<ShoppingCart> tablecart;


    @FXML
	public void initialize() {
    	modelfactory= ModelFactoryController.getInstance();
		cartList= FXCollections.observableArrayList();
    	init();

	}

	@Override
	public void intiView() {


	}

    public void init ()
    {
        getData();

        listenerSelection();
        initDataBinding();
        tablecart.getItems().clear();
        tablecart.setItems(cartList);
    }

    void getData(){
    	cartList.addAll(modelfactory.getListShoppingCart());

    }

    /**
     * para seleccionar un dato de la tabala y que se modifique el text de cantidad
     * y poder manipularlo
     */
    void listenerSelection(){
    	 tablecart.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
             select = newSelection;

             if(select!=null){
         		textQuantity.setText(select.getQuantity()+"");
         	}
         });

    }

    @FXML
    private Button back;
    @FXML
    void backAction(ActionEvent event) {
    	try {
			application.showWindow("PrincipalMain", application.getPrimaryStage());
		} catch (FileNotFoundException | JavaFXException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
    }
/**
 * we eliminate a sale that you do not want
 * @param event
 */
    @FXML
    void ActionButtonBorrar(ActionEvent event) {
    	if(select==null){
    		showMessage("debe seleccionar una venta", "ERROR", "No hay seleccion", Alert.AlertType.ERROR);
    	}else{
    		modelfactory.deleteShoppingCart(select.getCodeSale());
    		getData();
    		tablecart.getItems().clear();
    		tablecart.setItems(cartList);
    	}

    }

    /**
     * We modify the sale quantity
     * @param event
     */
    @FXML
    void ActionButtonCantidad(ActionEvent event) {
    	if(select==null){
    		showMessage("debe seleccionar una venta", "ERROR", "No hay seleccion", Alert.AlertType.ERROR);
    	}else{

    		modelfactory.setQuantityShoppingCart(select.getCodeSale(),Integer.parseInt(textQuantity.getText()));
    		modelfactory.setSubtotalShoppingCart(select.getCodeSale());
    		tablecart.refresh();
    	}
    }

    /**
     *method to display messages
     */
    protected void showMessage(String msj, String header, String contenido, Alert.AlertType alertType){
        Alert aler= new Alert(alertType);
        aler.setTitle(msj);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }


    /**
    * button to confirm the sale and create it,
     * then we run the SaleDetail window
     * @param event
     */
    @FXML
    void ActionButtonCompraTotal(ActionEvent event) {

    	try{
    		modelfactory.createSale(cartList.get(0).getCodeCustomer());
        	application.showWindow("SaleDetail",application.getPrimaryStage());

    	}catch(Exception e){
    		showMessage("no hay carro de compras", "ERROR", "Ha ocurrido un error", Alert.AlertType.ERROR);
    	}
    }


    /**
     * initialize table
     */
	@Override
	public void initDataBinding() {

		purchaseNumberId.setCellValueFactory(new PropertyValueFactory("codeSale"));
    	ProductName.setCellValueFactory(new PropertyValueFactory("codeCustomer"));
    	ProductNumber.setCellValueFactory(new PropertyValueFactory("productName"));
    	ProductQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));
    	subtotalColumnPurchase.setCellValueFactory(new PropertyValueFactory("subtotal"));



	}



	}






