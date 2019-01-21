package com.mpa.sales;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SaleProcessorTest {


	private SaleProcessor slp = new SaleProcessor();
	
	private HashMap<String, Integer> mapObj = new HashMap<String, Integer>();
	private ArrayList<UpdatedSales> arSalesList = new ArrayList<UpdatedSales>();
	private UpdatedSales objSales = new UpdatedSales();
	
	private String strProduct = "banana";
	private int iPrice = 20;
	private int iQty = 10;
	private int expectVal = 0; 
	
	/*
	 * The test values are populated in this method
	 */
	@Before
	public void init(){
		objSales.setProduct(strProduct);
		objSales.setPrice(iPrice);
		objSales.setQty(iQty);
		arSalesList.add(objSales);

	}
	
	/*
	 * Default Constructor
	 */
	public SaleProcessorTest() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * The UpdateQty function is tested here. 
	 * The expected value is tested with the returned value for a specific input 
	 */
	@Test
	public void testUpdate(){
		
		ArrayList<UpdatedSales> salesList = new ArrayList<UpdatedSales>();

		String product = "banana";
		int price = 10;
		int qty = 5;
		
		expectVal = salesList.size()+ 1;
				
		slp.update(salesList, product, price, qty);
				
		Assert.assertEquals(expectVal, salesList.size());
	}

	/*
	 * The CalculateSales function is tested here. 
	 * The expected value is tested with the returned value for a specific input 
	 */
	
	@Test
	public void testCalculateSales(){

		int iExpectedVal = mapObj.size()+1;
		mapObj = slp.calculateSales(arSalesList);
		
		Assert.assertEquals(iExpectedVal, mapObj.size());
	}
	
	
	@Test
	public void testCalculatesSalesForPopulatedList() {
		UpdatedSales sale = new UpdatedSales();
		
		String product = "banana";
		int price = 2;
		int qty = 3;
		
		sale.setProduct(product);
		sale.setPrice(price);
		sale.setQty(qty);
		
		mapObj = slp.calculateSales(arSalesList);
	
		expectVal = mapObj.get(sale.getProduct()).intValue() + sale.getQty();
		
		arSalesList.add(sale);
		
		mapObj = slp.calculateSales(arSalesList);
		
		Assert.assertEquals(expectVal, mapObj.get(sale.getProduct()).intValue());
	}
	

	@After	
	public void clean(){
		mapObj.clear();
	}
	
	/*
	 * The priceAdjust function is tested here. 
	 * Values for all the different cases for the switch case are tested here 
	 */

	@Test
	public void testPriceAdjust(){
		
		String product = "banana";
		int price = 5;
		
		Assert.assertTrue((calculateExpectedValue(arSalesList, product, price, "ADD") == chooseOp(arSalesList, product, price, "ADD")));
		Assert.assertTrue((calculateExpectedValue(arSalesList, product, price, "SUBTRACT") == chooseOp(arSalesList, product, price, "SUBTRACT")));
		Assert.assertTrue((calculateExpectedValue(arSalesList, product, price, "MULTIPLY") == chooseOp(arSalesList, product, price, "MULTIPLY")));
	}
	
	/*
	 * The priceAdjust function is tested here for negative values 
	 */

	@Test
	public void testPriceAjustNegative(){
		String product = "banana";
		int price = 50;
		
		Assert.assertFalse((calculateExpectedValue(arSalesList, product, price, "SUBTRACT") == chooseOp(arSalesList, product, price, "SUBTRACT")));
	}
	
	/*
	 * This function calculates the expected return value for the priceAdjust function
	 * and returns an int 
	 */
	
	private int calculateExpectedValue(ArrayList<UpdatedSales> testList, String product,int price, String strOption){
		
		int value = arSalesList.get(0).getPrice();
		switch(strOption)
		{
			case "ADD":
				value = value+price;
				break;
				
			case "SUBTRACT":
				value = value - price;
				break;
				
			case "MULTIPLY":
				value = value*price;
				break;
		
		}
		return value;
	}
	
	/*
	 * This function returns the actual value from the Map for the priceAdjust function
	 * for the various cases. 
	 */

	private int chooseOp(ArrayList<UpdatedSales> testList, String product,int price, String strOption){
		slp.adjustSales(testList, product, price, strOption);
		System.out.println("Choose Op -Qty : " + testList.get(0).getPrice());
		return testList.get(0).getPrice();
	}
}
