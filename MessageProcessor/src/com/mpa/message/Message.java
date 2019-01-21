package com.mpa.message;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.mpa.sales.Sale;

public class Message {

	Sale values = new Sale();
	ArrayList<Integer> keyValues = new ArrayList<Integer>();
	private static long counter = 0;
	int key;

	public static long getCounter() {
		return counter;
	}

	public static void setCounter(long counter) {
		Message.counter = counter;
	}

	/*
	 * Goes through the map of messages and chooses a random key value The
	 * corresponding message is then sent as a new message
	 */

	public Sale create(Map<Integer, Sale> listMap) {

		for (Entry<Integer, Sale> ee : listMap.entrySet()) {
			keyValues.add(ee.getKey());
		}
		key = getRandomElement(keyValues);

		values = listMap.get(key);

		return values;

	}

	/*
	 * Selects a key value at random from the map of messages
	 */
	private int getRandomElement(ArrayList<Integer> list) {
		Random rand = new Random();
		return list.get(rand.nextInt(list.size()));
	}

	/*
	 * Sends the ready message to the calling main function Simulates an external
	 * message sending interface
	 */

	public Sale sendReceive(Map<Integer, Sale> listMap) {

		

		Sale retSale = create(listMap);
		counter++;

		return retSale;
	}
}
