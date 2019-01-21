package com;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import com.mpa.message.MessageTest;
import com.mpa.sales.SaleProcessorTest;


	@RunWith(Suite.class)
	@Suite.SuiteClasses({MessageTest.class, SaleProcessorTest.class})
	public class SuiteTest {

	}


