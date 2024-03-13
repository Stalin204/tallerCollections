package viewController;


import java.net.URL;
import java.util.ResourceBundle;

import controller.HistorySale;
import exception.FileNotFoundException;
import exception.JavaFXException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import model.SaleHistory;

public class SaleHistoryController extends ViewController{

	HistorySale sale ;
	SaleHistory seleccioando;

 private ObservableList<SaleHistory> datos = FXCollections.observableArrayList();
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="CantidadDeProductosComprados"
    private TableColumn<SaleHistory, String> quantityofProductsPurchased; // Value injected by FXMLLoader

    @FXML
    private TableView<SaleHistory> tableHistory;

    @FXML // fx:id="LabelHistorialVentas"
    private Label LabelHistorialVentas; // Value injected by FXMLLoader

    @FXML // fx:id="NumeroProducto"
    private TableColumn<SaleHistory, String> NumberProduct; // Value injected by FXMLLoader

    @FXML // fx:id="NumeroCliente"
    private TableColumn<SaleHistory, String> CustomerNumber; // Value injected by FXMLLoader

    @FXML // fx:id="FechaDeLaCompra"
    private TableColumn<SaleHistory, String> DateofPurchase; // Value injected by FXMLLoader
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



    @FXML // fx:id="AnchorPane"
    private AnchorPane AnchorPane; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    		sale = new HistorySale();
    		initDataBinding();
    		CollectModelFactoryData ();
    		listenerSelection();
    		tableHistory.getItems().clear();
            tableHistory.setItems(datos);
    }

	@Override
	public void intiView() {


	}

	@Override
	public void initDataBinding() {
		NumberProduct.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductCode()));
		CustomerNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodeBuyer()));
		 DateofPurchase.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));
		quantityofProductsPurchased.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuantityProductsPurchased()));

	}
	private void CollectModelFactoryData (){
		datos.addAll(sale.CollectDataModelFactory());
	}
	private void listenerSelection() {
        tableHistory.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            seleccioando = newSelection;
        });
    }


}