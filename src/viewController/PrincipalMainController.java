package viewController;
import exception.FileNotFoundException;
import exception.JavaFXException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrincipalMainController extends ViewController{

	   @FXML
	    private Button AddClients;

	    @FXML
	    private Button ButtonSaleHistory;

	    @FXML
	    private Button Products;

	    @FXML
	    private Button ShoppingCart;

	    @FXML
	    void ActionAddClients(ActionEvent event) {
	    	try {
				application.showWindow("Customer", application.getPrimaryStage());
			} catch (FileNotFoundException | JavaFXException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
	    }

	    @FXML
	    void ActionHistorySale(ActionEvent event) {
	    	try {
				application.showWindow("SaleHistory", application.getPrimaryStage());
			} catch (FileNotFoundException | JavaFXException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
	    }
	    @FXML
	    void ActionProduct(ActionEvent event) {
	    	try {
				application.showWindow("CreateSale", application.getPrimaryStage());
			} catch (FileNotFoundException | JavaFXException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
	    }

	    @FXML
	    void ActionShoppingCart(ActionEvent event) {
	    	try {
				application.showWindow("ShoppingCart", application.getPrimaryStage());
			} catch (FileNotFoundException | JavaFXException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
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
