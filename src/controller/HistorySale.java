package controller;

import java.util.List;

import interfaces.ISaleHistory;
import model.SaleHistory;

public class HistorySale implements ISaleHistory {

	ModelFactoryController modelcontroller;




	public HistorySale() {

		this.modelcontroller =ModelFactoryController.getInstance();
	}

	@Override
	public List<SaleHistory> CollectDataModelFactory() {

		return modelcontroller.returnSalesStory();
	}


	public void addDatList(SaleHistory E) {
		SaleHistory dat = new SaleHistory();
		dat.setQuantityProductsPurchased("2");
		modelcontroller.AddProductSoldToHIsotrialSales(dat);

	}





}
