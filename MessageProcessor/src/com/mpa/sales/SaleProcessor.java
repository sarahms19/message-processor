package com.mpa.sales;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SaleProcessor {

	static ArrayList<UpdatedSales> updatedList = new ArrayList<UpdatedSales>();
	static Map<String, String> finalReport = new HashMap<String, String>();
	static int adjCounter = 0;
	
	public static ArrayList<UpdatedSales> getUpdatedList() {
		return updatedList;
	}

	public static void setUpdatedList(ArrayList<UpdatedSales> updatedList) {
		SaleProcessor.updatedList = updatedList;
	}

	public static Map<String, String> getFinalReport() {
		return finalReport;
	}

	public static void setFinalReport(Map<String, String> finalReport) {
		SaleProcessor.finalReport = finalReport;
	}

	/*
	 * Processes incoming messages
	 */

	public void processor(Sale msgVar) {
		// TODO Auto-generated method stub

		UpdatedSales us = new UpdatedSales();

		String type = msgVar.getMessageType();
		String product = msgVar.getProduct();
		int price = msgVar.getPrice();
		int quantity = msgVar.getQty();
		String adjstmnt = msgVar.getAdjustment();
				
		if (type.equals("MessageType3") && updatedList.isEmpty())
			System.out.println("No sales recorded yet");
		else {

			if (updatedList.isEmpty()) {
				us.setProduct(product);
				us.setPrice(price);
				us.setQty(quantity);
				updatedList.add(us);

			} else {
				switch (type) {
				case "MessageType1":
					update(updatedList, product, price, quantity);
					break;

				case "MessageType2":
					update(updatedList, product, price, quantity);
					break;

				case "MessageType3":
					adjustSales(updatedList, product, price, adjstmnt);
					break;
				}
			}
		}

	
	}

	/*
	 * Records values for adjustments made to each sale
	 */
	protected void adjustSales(ArrayList<UpdatedSales> updatedList2, String product, int price, String adjstmnt) {
		// TODO Auto-generated method stub

		int curPrice;
		int temp = 0;
		int i = 0;

		while (i < updatedList2.size()) {
			if (updatedList2.get(i).getProduct().equals(product)) {
				if (updatedList.size() != 0) {
					curPrice = updatedList.get(i).getPrice();
				} else {
					curPrice = updatedList2.get(i).getPrice();
				}
				switch (adjstmnt) {
				case "ADD":
					temp = curPrice + price;
					updatedList2.get(i).setPrice(temp);
					break;
				case "SUBTRACT":
					temp = curPrice - price;
					if (temp < 0) {
						adjstmnt="SUBTRACT - NO CHANGE NEGATIVE PRICE";
						break;
					} else {
						updatedList2.get(i).setPrice(temp);
						break;
					}
				case "MULTIPLY":
					temp = curPrice * price;
					updatedList2.get(i).setPrice(temp);
					break;
				}

			}
			i++;
			reportLog(product, price, adjstmnt);
		}
	}

	/*
	 * New products added to product list if it does not already exist
	 */

	protected void update(ArrayList<UpdatedSales> updatedList2, String product, int price, int quantity) {
		// TODO Auto-generated method stub
		UpdatedSales tempSale = new UpdatedSales();

		tempSale.setProduct(product);
		tempSale.setPrice(price);
		tempSale.setQty(quantity);
		updatedList2.add(tempSale);

	}
	
	/*
	 * Logs a report of adjustments made to each sale
	 */
	
	private void reportLog(String product, int value, String adj) {
		// TODO Auto-generated method stub
		String t = Integer.toString(value);
		String temp = t + "p " + adj;

		finalReport.put(product, temp);
		
	}

	/*
	 * Calculates sales of each product
	 */

	public HashMap<String, Integer> calculateSales(ArrayList<UpdatedSales> arrayList) {

		HashMap<String, Integer> h = new HashMap<String, Integer>();
		int j = 0;
		for (int i = 0; i < arrayList.size(); i++) {
			String product = arrayList.get(i).getProduct();
			int qty1 = arrayList.get(i).getQty();

			if (!(h.containsKey(product))) {
				for (j = i; j < arrayList.size(); j++) {

					String temp1 = arrayList.get(i).getProduct();
					String temp2 = arrayList.get(j).getProduct();

					int qty2 = arrayList.get(j).getQty();

					if (temp1.equals(temp2)) {
						if (!h.containsKey(temp1)) {

							h.put(temp1, qty1);

						} else {

							qty2 = qty1 + qty2;
							h.put(temp1, qty2);
							qty1 = qty2;
						}
					}
				}
			}
		}

		return h;
	}

}
