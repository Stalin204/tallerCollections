package model;

public class SaleHistory {

	private String productCode;
	private String date;
	private String CodeBuyer;
	private String quantityProductsPurchased;
	public String getQuantityProductsPurchased() {
		return quantityProductsPurchased;
	}



	public void setQuantityProductsPurchased(String quantityProductsPurchased) {
		this.quantityProductsPurchased = quantityProductsPurchased;
	}



	public String getProductCode() {
		return productCode;
	}



	@Override
	public String toString() {
		return "SaleHistory [productCode=" + productCode + ", date=" + date + ", CodeBuyer=" + CodeBuyer + "]";
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCodeBuyer() {
		return CodeBuyer;
	}
	public void setCodeBuyer(String codeBuyer) {
		CodeBuyer = codeBuyer;
	}


	public SaleHistory ()
	{}
	public SaleHistory (String productCode, String date, String CodeBuyer)
	{
		this.productCode=productCode;
		this.date=date;
		this.CodeBuyer=CodeBuyer;
	}


}
