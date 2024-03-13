package model;

import java.util.ArrayList;

public class ShoppingCart {

	private String codeSale;
	private String codeProduct;
	private String productName;
	private String codeCustomer;
	private int quantity;
	private double price;
	private Double subtotal;


	public ShoppingCart(){
	}


	public String getCodeSale() {	return codeSale; }

	public void setCodeSale(String codeSale) {	this.codeSale = codeSale;	}

	public String getCodeProduct() {	return codeProduct;	}

	public void setCodeProduct(String codeProduct) {	this.codeProduct = codeProduct;	}

	public String getProductName() {	return productName;	}

	public void setProductName(String productName) {	this.productName = productName;	}

	public String getCodeCustomer() {	return codeCustomer;	}

	public void setCodeCustomer(String code) {	this.codeCustomer = code; }

	public int getQuantity() {	return quantity;	}

	public void setQuantity(int quantity) {	this.quantity = quantity;	}

	public double getPrecio() {	return price;	}

	public void setPrecio(double precio) {	this.price = precio;	}

	public Double getSubtotal() {	return subtotal;	}

	public void setSubtotal(Double subtotal) {	this.subtotal = subtotal;	}




}
