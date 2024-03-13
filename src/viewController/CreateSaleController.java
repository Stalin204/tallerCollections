package viewController;

import application.MainController;
import controller.ModelFactoryController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Product;

public class CreateSaleController extends ViewController {

	ModelFactoryController modelfactory;
	ObservableList<Product> productList;
	Product select;

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Button ButtonCantidad;

    @FXML
    private Label LabelHistorialVentas;

    @FXML
    private TableColumn<Product,String> ProductName;

    @FXML
    private TableColumn<Product,String> ProductNumber;

    @FXML
    private TableColumn<Product,String> ProductQuantity;

    @FXML
    private TableColumn<Product,String> purchaseNumberId;

    @FXML
    private TableColumn<Product,String> subtotalColumnPurchase;

    @FXML
    private TableView<Product> table;

    @FXML
    private TextField textQuantity;

    @FXML
  	public void initialize() {
    modelfactory= ModelFactoryController.getInstance();
	productList= FXCollections.observableArrayList();
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
    table.getItems().clear();
    table.setItems(productList);
}

void getData(){
	for (Product value : modelfactory.getListProducts().values()) {
	    productList.add(value);
	}

}

/**
 *to select a data from the table and modify the quantity text
 * and be able to manipulate it
 */
void listenerSelection(){
	 table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
         select = newSelection;

         if(select!=null){
     		textQuantity.setText(select.getQuantity()+"");
     	}
     });

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

		modelfactory.createShoppingCart(select.getCode(),textQuantity.getText());
		table.refresh();
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
 * initialize table
 */
@Override
public void initDataBinding() {

	purchaseNumberId.setCellValueFactory(cellData -> new SimpleStringProperty(""));
	ProductName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductName()));
	ProductNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCode()));
	ProductQuantity.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getQuantity()));
	subtotalColumnPurchase.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getPrice()));



}



}






