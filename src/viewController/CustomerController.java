package viewController;
import java.net.URL;
import java.util.ResourceBundle;
import controller.ModelFactoryController;
import exception.FileNotFoundException;
import exception.JavaFXException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Customer;

public class CustomerController  extends ViewController{
	ModelFactoryController mfc = ModelFactoryController.getInstance();
	String idSelection = null;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtIdentificationNamber"
    private TextField txtIdentificationNamber; // Value injected by FXMLLoader

    @FXML // fx:id="tcNombre"
    private TableColumn<Customer, String> tcName; // Value injected by FXMLLoader

    @FXML // fx:id="tcAddress"
    private TableColumn<Customer, String> tcAddress; // Value injected by FXMLLoader

    @FXML // fx:id="txtName"
    private TextField txtName; // Value injected by FXMLLoader

    @FXML // fx:id="btnUpdate"
    private Button btnUpdate; // Value injected by FXMLLoader

    @FXML // fx:id="btnClear"
    private Button btnClear; // Value injected by FXMLLoader

    @FXML // fx:id="tablaView"
    private TableView<Customer> tableView; // Value injected by FXMLLoader

    @FXML // fx:id="txtPhoneNumber"
    private TextField txtPhoneNumber; // Value injected by FXMLLoader

    @FXML // fx:id="tcPhoneNamber"
    private TableColumn<Customer, String> tcPhoneNamber; // Value injected by FXMLLoader

    @FXML // fx:id="btnNew"
    private Button btnNew; // Value injected by FXMLLoader

    @FXML // fx:id="btnDelete"
    private Button btnDelete; // Value injected by FXMLLoader

    @FXML // fx:id="tcIdentificationNumber"
    private TableColumn<Customer, String> tcIdentificationNumber; // Value injected by FXMLLoader

    @FXML // fx:id="txtAddress"
    private TextField txtAddress; // Value injected by FXMLLoader
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


    @FXML
    void createAction(ActionEvent event) {
    	if(newCustomer(createCustomer())) {
    		seeMessage("", "Atención", "estudiante creado", AlertType.INFORMATION);
    		refresh();
    	}else {
    		seeMessage("", "Atención", "estudiante no creado", AlertType.ERROR);
    	}
    }

    private Customer createCustomer() {
		Customer c = new Customer();
		c.setAddress(txtAddress.getText());
		c.setCustomerName(txtName.getText());
		c.setDocumentNumber(txtIdentificationNamber.getText());
		c.setPhoneNumber(txtPhoneNumber.getText());
		return c;
	}

	private void refresh() {
		clearTexts();
		getListObject();
		tableView.setItems(listObjects);


	}

	@FXML
    void updateAction(ActionEvent event) {
    	if (!idSelection.equals(txtIdentificationNamber.getText())) {
    		UpdateIdConsumer(idSelection,txtIdentificationNamber.getText());
    		refresh();
    	}
    	if(updateConsumer(createCustomer())) {
    		seeMessage("", "Atención", "estudiante Actualizado", AlertType.INFORMATION);
    		refresh();
    	}else {
    		seeMessage("", "Atención", "estudiante no Actualizado", AlertType.ERROR);
    	}
    }

	@FXML
    void deleteAction(ActionEvent event) {
    	if(deleteConsumer()) {
    		seeMessage("", "Atención", "estudiante Eliminado", AlertType.INFORMATION);
			refresh();
		}else {
			seeMessage("", "Atención", "estudiante no eliminado", AlertType.ERROR);
		}
    }


	@FXML
    void clearAction(ActionEvent event) {
		clearTexts();
    }
    void clearTexts() {
    	txtAddress.setText("");
    	txtIdentificationNamber.setText("");
    	txtName.setText("");
    	txtPhoneNumber.setText("");
    }
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	intiView();
    	refresh();

    	listenerSelection();

    }
    private boolean newCustomer(Customer c) {
    	return mfc.getCompany().createCustomer(c);
    }
    private boolean deleteConsumer() {
    	return mfc.getCompany().deleteCustomer(idSelection);
    }
    private boolean updateConsumer(Customer c) {
    	return mfc.getCompany().updateCustomer(c);
    }
    private boolean UpdateIdConsumer(String IDOld, String IDNew) {
    	return mfc.getCompany().updateIDCustomer(IDOld, IDNew);
    }

	@Override
	public void intiView() {
		initDataBinding();


	}


//******************************************************Codigo necesario para el manejo de tablas en javaFX:***************************************
    @FXML // This method is called by the FXMLLoader when initialization is complete
	//configuracion de inicio

	 ObservableList<Customer> listObjects = FXCollections.observableArrayList();
	 Customer objectSelection;




		@Override
		public void initDataBinding() {
	        tcIdentificationNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDocumentNumber()));

	        tcName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomerName()));

	        tcAddress.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getAddress()));

	        tcPhoneNamber.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getPhoneNumber()));
		}
		private void getListObject() {
			listObjects = FXCollections.observableArrayList();
			listObjects.addAll(mfc.getCompany().getCustomerList().values());

		}
		 private void listenerSelection() {
		    	tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		            objectSelection = newSelection;
		            seeInformationSelection(objectSelection);
		            try{
		            	idSelection = objectSelection.getDocumentNumber();
		            }catch (Exception e) {

					}
		        });
		    }

		 private void seeInformationSelection(Customer objetoSeleccionado) {
		        if(objetoSeleccionado != null){
		            txtAddress.setText(objetoSeleccionado.getAddress());

		            txtIdentificationNamber.setText(objetoSeleccionado.getDocumentNumber());

		            txtName.setText(""+objetoSeleccionado.getCustomerName());

		            txtPhoneNumber.setText(""+objetoSeleccionado.getPhoneNumber());


		        }
		 }

}
