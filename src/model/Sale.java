package model;

import java.util.ArrayList;

public class Sale {

	private String codeSale;
	private String codeCustomer;
	private String nameCustomer;
	private String codeProduct;
	private String nameProduct;
	private Double subtotal;
	private Double price;
	private String date;
	private Double total;
	private int quantity;

	public Sale() {}


	public String getDate() {return date;}
	public Double getTotal() {return total;}
	public void setDate(String date) {this.date = date;}
	public void setTotal(Double total) {this.total = total;}
	public int getQuantity() {return quantity;}
	public void setQuantity(int quantity) {this.quantity = quantity;}
	public String getCodeSale() {	return codeSale;	}
	public void setCodeSale(String codeSale) {	this.codeSale = codeSale;	}
	public String getCodeCustomer() {	return codeCustomer;	}
	public void setCodeCustomer(String codeCustomer) {	this.codeCustomer = codeCustomer;}
	public String getNameCustomer() {	return nameCustomer;	}
	public void setNameCustomer(String nameCustomer) {	this.nameCustomer = nameCustomer;	}
	public String getCodeProduct() {	return codeProduct;	}

	public void setCodeProduct(String codeProduct) {	this.codeProduct = codeProduct;	}

	public String getNameProduct() {	return nameProduct;	}

	public void setNameProduct(String nameProduct) {	this.nameProduct = nameProduct;	}

	public Double getSubtotal() {	return subtotal;	}

	public void setSubtotal(Double subtotal) {	this.subtotal = subtotal;	}

	public Double getPrice() {	return price;	}

	public void setPrice(Double price) {	this.price = price;	}






}
