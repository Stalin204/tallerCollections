package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Product implements Comparable<Product>{
	private String code;
	private String productName;
	private int quantity;
	private Double price;

	public Product() {}

	public String getCode() {return code;}

	public void setCode(String code) {this.code = code;}
	public String getProductName() {return productName;}
	public void setProductName(String productName) {this.productName = productName;}
	public int getQuantity() {return quantity;}
	public void setQuantity(int quantity) {this.quantity = quantity;}
	public Double getPrice() {return price;}
	public void setPrice(Double price) {this.price = price;}
	public void reabastecer(int cantidad) {
        this.quantity += cantidad;
    }

	@Override
	public int compareTo(Product o) {
		return Integer.compare(this.quantity,o.quantity);
	}


}


