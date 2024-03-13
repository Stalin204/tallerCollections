package model;

import java.util.HashMap;

public class SaleDetail {

	private int quantity;

	private int subtotal;

	HashMap<String, Product> productList = new HashMap<>();

	public SaleDetail() {

	}

	public int getQuantity() {
		return quantity;
	}

	public int getSubtotal() {
		return subtotal;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}
}
