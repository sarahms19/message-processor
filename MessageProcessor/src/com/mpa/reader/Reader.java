package com.mpa.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;
import java.util.Scanner;

import com.mpa.sales.Sale;

/*
 * Reads messages from text file
 */

public class Reader {

	Map<Integer, ArrayList<String>> list = new HashMap<Integer, ArrayList<String>>();
	Map<Integer, Sale> saleList = new HashMap<Integer, Sale>();

	ArrayList<String> itemData = new ArrayList<>();
	Sale sales;
	/*
	 * Code to read text file which has all the messages For the purpose of this
	 * exercise a text file with messages was used as input
	 */

	public Map<Integer, Sale> readFile() {
		try {
			/*
			 * For the purpose of this exercise the file path has been hard coded
			 */
			File file = new File("./resources/MessageList.txt");

			Scanner sc = new Scanner(file);
			int index = 0, counter = 0;

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (line.equals(";")) {
					String stype = itemData.get(counter);
					String sproduct = itemData.get(counter + 1);
					int sprice = Integer.parseInt(itemData.get(counter + 2));
					int qty;
					String sadj;

					if (itemData.get(counter).equals("MessageType3")) {
						sadj = itemData.get(counter + 3);
						qty = 0;

					} else {
						sadj = "NULL";
						qty = Integer.parseInt(itemData.get(counter + 3));

					}

					sales = new Sale(stype, sproduct, sprice, qty, sadj);
					saleList.put(index, sales);

					index++;
					counter = counter + 4;
				} else {
					itemData.add(line);

				}

			}
			sc.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return (saleList);
	}
}
