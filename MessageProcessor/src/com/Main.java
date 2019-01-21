package com;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mpa.message.Message;
import com.mpa.reader.Reader;
import com.mpa.sales.Sale;
import com.mpa.sales.SaleProcessor;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			FileHandler handler = new FileHandler("./logReport.log");
			Logger logger = Logger.getLogger("com");
			logger.addHandler(handler);

			Reader r = new Reader();
			Message msg = new Message();
			SaleProcessor sp = new SaleProcessor();

			long count;
			Map<String, String> report = new HashMap<String, String>();
			HashMap<String, Integer> hReport = new HashMap<String, Integer>();
			Map<Integer, Sale> listMap = r.readFile();

			while (true) {

				Sale msgVar = msg.sendReceive(listMap); // acts as a message receiver
				System.out.println(msgVar.getMessageType() + " " + msgVar.getProduct() + " "
						+ msgVar.getPrice() + " " + msgVar.getQty() + " " + msgVar.getAdjustment());
				sp.processor(msgVar); // message received is sent for processing

				count = Message.getCounter();
				if(count%10==0)
				{
					hReport = sp.calculateSales(sp.getUpdatedList());
					Set<Entry<String, Integer>> st = hReport.entrySet();

					for (Entry entry : st) {
						System.out.println("Product "+entry.getKey() + " - Sales - " + entry.getValue());
						logger.log(Level.INFO, "Product "+entry.getKey() + " - Sales - " + entry.getValue());

					}
					
				}
				
				/*
				 *After 50 messages stops and logs report
				 */
				if (count == 50) {
					report = SaleProcessor.getFinalReport();
					Set<Entry<String, String>> st = report.entrySet();

					logger.info("Pause - Stop Accepting Messages");
					for (Entry entry : st) {
						
						System.out.println(entry.getKey() + " - Adjustment - " + entry.getValue());
						logger.log(Level.INFO, entry.getKey() + " - Adjustment - " + entry.getValue());
						
					}

					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
