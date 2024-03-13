package model;

import java.util.ArrayList;

import interfaces.ICustomer;

public class Customer implements ICustomer{

	//
	private String customerName;
	private String documentNumber;
	private String address;
	private String phoneNumber;
	ArrayList<String> salesList = new ArrayList<>();
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public ArrayList<String> getSalesList() {
		return salesList;
	}
	public void setSalesList(ArrayList<String> salesList) {
		this.salesList = salesList;
	}
	@Override
	public String toString() {
		return "Customer [customerName=" + customerName + ", documentNumber=" + documentNumber + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", salesList=" + salesList + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((documentNumber == null) ? 0 : documentNumber.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((salesList == null) ? 0 : salesList.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (documentNumber == null) {
			if (other.documentNumber != null)
				return false;
		} else if (!documentNumber.equals(other.documentNumber))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (salesList == null) {
			if (other.salesList != null)
				return false;
		} else if (!salesList.equals(other.salesList))
			return false;
		return true;
	}
	public Customer(String customerName, String documentNumber, String address, String phoneNumber,
			ArrayList<String> salesList) {
		super();
		this.customerName = customerName;
		this.documentNumber = documentNumber;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.salesList = salesList;
	}
	public Customer() {
		super();
		// TODO Esbozo de constructor generado automáticamente
	}



}
