package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import exception.FileManagerException;
import library.CodingTextManager;
import library.FileManager;
import model.*;

public class ModelFactoryController {

    private static class SingletonHolder {
        // The Singleton constructor can be called from here since it's protected
        private final static ModelFactoryController INSTANCE = new ModelFactoryController();
    }

    // Method to obtain the instance of our class
    public static ModelFactoryController getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public ModelFactoryController() {
        System.out.println("Singleton class invocation");
        initializeData();
    }


    private ShoppingCart cart;
    private Store company;
    private SaleHistory history;


    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    private void initializeData() {
        // Create company
        company = new Store();
        cart = new ShoppingCart();
        history = new SaleHistory();

        // customer
        Customer c1 = new Customer();
        c1.setAddress("Armenia");
        c1.setCustomerName("Juan Felipe");
        c1.setDocumentNumber("1234");
        c1.setPhoneNumber("3127109857");
        company.createCustomer(c1);
        System.out.println(company.getCustomerList().toString());
    }
    private Map<String, Product> getListProductsFile() {
		try {
			String ruta = "/laboratorio/src/application/Productos.txt";
			CodingTextManager file = new CodingTextManager(ruta, ",");
			file.setFileManager(new FileManager(ruta));

			for (ArrayList<String> f :file.getObjectList() ) {
				Product p = new Product();
				p.setProductName(f.get(0));
				p.setCode(f.get(1));
				p.setQuantity(Integer.parseInt(f.get(2)));
				p.setPrice(Double.parseDouble(f.get(3)));
				company.createProduct(p);
			}
		} catch (FileManagerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public LinkedList<SaleHistory> returnSalesStory (){
    	return Store.returnLISTstaDeHIsotorialDeVentas();
    }


     public boolean AddProductSoldToHIsotrialSales (SaleHistory E)
     {
    	 return  Store.CreateListOfSaleHistoryData(E);
     }
     public boolean addProducts (String nombre,int cantidad)
     {
	return Store.AddProductToTreeset(nombre, cantidad);
	}



    public Store getCompany() {
        return company;
    }

    public void setCompany(Store company) {
        this.company = company;
    }

	public HashSet<ShoppingCart> getListShoppingCart() {
		// TODO Auto-generated method stub
		return company.getlistShoppingCart();
	}

	public boolean deleteShoppingCart(String code) {
		// TODO Auto-generated method stub
		return company.delete(code);
	}

	public boolean setQuantityShoppingCart(String code,int cantNew) {
		// TODO Auto-generated method stub
		return company.updateCantidad(code, cantNew);
	}

	public boolean setSubtotalShoppingCart(String code) {
		// TODO Auto-generated method stub
		return company.setSubtotalShoppingCart(code);
	}

	public List<Sale> getListaSale() {
		// TODO Auto-generated method stub
		return company.getListSale();
	}

	public boolean createSale(String codeCustomer) {
		return company.createSale(codeCustomer);
	}

	public boolean emptyCartList() {
		// TODO Auto-generated method stub
		return company.emptyCartList();
	}
	public Map<String,Product> getListProducts() {
		return company.getProductList();
	}

	public boolean createShoppingCart(String code,String q) {
		return company.createShopping(code,q);

	}

}
