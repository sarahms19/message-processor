package com.mpa.message;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import com.mpa.sales.Sale;


public class MessageTest {
	
	private Message msg = new Message();

	public MessageTest() {
		// TODO Auto-generated constructor stub
	}
	
		
	@Test	 
	public void testCreateEquals(){
				
		Integer i = 0;
		Map<Integer, Sale> listMap = new HashMap<Integer, Sale>();
		Sale testListObj = new Sale();
		
		testListObj.setMessageType("MessageType1");
		testListObj.setProduct("apple");
		testListObj.setPrice(10);
		testListObj.setQty(12);
		testListObj.setAdjustment("NULL");
		listMap.put(i, testListObj);
		
		Assert.assertEquals(testListObj, msg.create(listMap));
		
	}
	
}
