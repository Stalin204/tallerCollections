package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import interfaces.IStrore;


public class Store implements IStrore{

	private String name;
	private String address;
	private String nit;
	public Store() {}
	public String getName() {return name;}

	public void setName(String name) {this.name = name;}
	public String getAddress() {return address;}
	public void setAddress(String address) {this.address = address;}
	public String getNit() {return nit;}
	public void setNit(String nit) {this.nit = nit;}
		private Map<String, Customer> customerList = new HashMap<>();


		public Boolean createCustomer(Customer E) {
			try{
				customerList.put(E.getDocumentNumber(), E);
				return true;
			}catch (Exception e) {
				return false;
			}

		}


		public Boolean deleteCustomer(String ID) {
			try{
				customerList.remove(ID);
				return true;
			}catch (Exception e) {
				return false;
			}
		}


		public Boolean updateCustomer(Customer E2) {
			try{
				customerList.replace(E2.getDocumentNumber(), E2);
				return true;
			}catch (Exception e) {
				return false;
			}
		}


		public Customer getCustomer(String ID) {
			return customerList.get(ID);
		}


		public Boolean existCustomer(String ID) {
			return customerList.values().contains(customerList.get(ID));
		}


		public Boolean updateIDCustomer(String IDOld, String IDNew) {
			try{
				Customer c = getCustomer(IDOld);
				c.setDocumentNumber(IDNew);
				createCustomer(c);
				customerList.remove(IDOld);
				return true;
			}catch (Exception e) {
				return false;
			}
		}


//---------------------------------------------------------------------

		private Map<String, Product> productList = new HashMap<>();


		public Map<String, Customer> getCustomerList() {
			return customerList;
		}
		public void setCustomerList(Map<String, Customer> customerList) {
			this.customerList = customerList;
		}
		public Map<String, Product> getProductList() {
			return productList;
		}
		public void setProductList(Map<String, Product> productList) {
			this.productList = productList;
		}
		public Boolean createProduct(Product E) {
			try{
				productList.put(E.getCode(), E);
				return true;
			}catch (Exception e) {
				return false;
			}
		}



		public Boolean deleteProduct(String code) {
			try{
				productList.remove(code);
				return true;
			}catch (Exception e) {
				return false;
			}
		}


		public Boolean updateProduct(Product E2) {
			try{
				productList.replace(E2.getCode(), E2);
				return true;
			}catch (Exception e) {
				return false;
			}
		}


		public Product getProduct(String code) {
			return productList.get(code);
		}


		public Boolean existProduct(String code) {
			return productList.values().contains(productList.get(code));
		}


		public Boolean updateIDProduct(String codeOLD, String codeNew) {
			try{
				Product p = getProduct(codeOLD);
				p.setCode(codeNew);
				createProduct(p);
				productList.remove(codeOLD);
				return true;
			}catch (Exception e) {
				return false;
			}
		}
		private static  LinkedList<SaleHistory> listLinked = new LinkedList<>();


		public static SaleHistory AddHisotroryProductsSold (){
			SaleHistory test  = new SaleHistory ();
			test.setQuantityProductsPurchased("2");
			test.setCodeBuyer("56");
			test.setDate("10/03/2024");
			test.setProductCode("1234");
			return test;
		}


			public static  boolean CreateListOfSaleHistoryData (SaleHistory E){
				try{
					listLinked.add(E);
					return true;

				}catch(Exception e) {
					return false;
				}
			}


			private static TreeSet<Product> inventory = new TreeSet<>();


			// Method for adding a product to inventory
		    public static  boolean AddProductToTreeset(String nombre, int cantidad) {
		    	try{
		    		Product produ = new Product();
		    		produ.setProductName(nombre);;
		    		produ.setQuantity(cantidad);;
		    		inventory.add(produ);
		    		ManageProductInventory();
					return true;

				}catch(Exception e) {
					return false;
				}
		    }




		 // Method for Replenishing an Existing Product in Inventory
		    public static void RestockProduct(String nombre, int cantidad) {
		        for (Product prod : inventory) {
		            if (prod.getProductName().equals(nombre)) {
		                prod.reabastecer(cantidad);
		                break;
		            }
		        }
		    }

		    public static List<Product> getLowProductsInventory() {
		        List<Product> productsLowInventory = new ArrayList<>();
		        for (Product product : inventory) {
		            if (product.getQuantity() < 10) {
		            	productsLowInventory.add(product);
		            }
		        }
		        return productsLowInventory;
		    }

		    public static  void ManageProductInventory (){
		    	List<Product> products= getLowProductsInventory();
		    	for (Product prod: products)
		    	{
		    		RestockProduct(prod.getProductName(),100);
		    	}
		    }
			public static TreeSet<Product> getInventory() {
				return inventory;
			}
			public static void setInventory(TreeSet<Product> inventory) {
				Store.inventory = inventory;
			}
			public static LinkedList<SaleHistory> returnLISTstaDeHIsotorialDeVentas() {
				listLinked.add(AddHisotroryProductsSold());
				return listLinked;

			}
			HashSet<ShoppingCart> listaShoppingCart= new HashSet<>();
			private List<Sale> listSale;

			/**
			 * create ShoppingCart
			 * @param E
			 * @return
			 */
			public Boolean create(ShoppingCart E) {
				try{
					listaShoppingCart.add(E);
					return true;
				}catch(Exception e){
					return false;
				}

			}


			/**
			 * With the sales code we change to the amount they sent us
			 * @param code
			 * @param cantNew
			 * @return
			 */
			public Boolean updateCantidad(String code,int cantNew){
				try{
					for(ShoppingCart s:listaShoppingCart){
						if(s.getCodeSale().equals(code)){
							s.setQuantity(cantNew);
							break;
						}
					}
					return true;
				}catch(Exception e){
					return false;
				}
			}

			/**
			 * With the sales code we delete the sale
			 * @param code
			 * @return
			 */
			public Boolean delete(String code) {
				try{
					for(ShoppingCart s:listaShoppingCart){
						if(s.getCodeSale().equals(code));
						listaShoppingCart.remove(s);
						break;
					}
					return true;
				}catch(Exception e){
					return false;
				}
			}

			/**
			 * get the subtotal of the sale
			 * @param code
			 * @return
			 */
			public boolean setSubtotalShoppingCart(String code) {
				try{
					for(ShoppingCart s:listaShoppingCart){
						if(s.getCodeSale().equals(code));
						s.setSubtotal(s.getQuantity()*s.getPrecio());
						break;
					}
					return true;
				}catch(Exception e){
					return false;
				}
			}


			public HashSet<ShoppingCart> getlistShoppingCart() {
				return listaShoppingCart;}

			public Boolean createShopping(String code,String q){
				try{
					ShoppingCart c= new ShoppingCart();
					c.setCodeSale(generatecode());
					c.setCodeProduct(code);
					c.setProductName(productList.get(code).getProductName());
					c.setSubtotal(productList.get(code).getPrice());
					c.setCodeCustomer("");
					c.setPrecio(productList.get(code).getPrice());
					c.setQuantity(Integer.parseInt(q));

					listaShoppingCart.add(c);
					return true;
				}catch(Exception e){
					return false;
				}
			}
			ArrayList<String> codigos= new ArrayList<>();

			public String generatecode(){
				String c=String.valueOf(Math.random()*1234);
				while(verific(c)){
					c=String.valueOf(Math.random()*1234);
				}


				return c;
			}

			public boolean verific(String c){
				boolean f=false;
				for(int i=0;i<codigos.size();i++){
					if(codigos.get(i).equals(c)){
						f=true;
						break;
					}
				}
			return f;}

			/**
			 * empty the list when the sale is made
			 * @return
			 */
			public boolean emptyCartList() {
				try{
					listaShoppingCart.clear();
					return true;
				}catch(Exception e){
					return false;
				}
			}
			public boolean createSale(String codeCustomer) {
				// TODO Esbozo de método generado automáticamente
				return false;
			}
			public List<Sale> getListSale() {
				// TODO Esbozo de método generado automáticamente
				return listSale;
			}



}

