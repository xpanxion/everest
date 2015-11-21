package com.xpanxion.everest.dao.www;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.xpanxion.everest.dto.stock.StockInfo;
import com.xpanxion.everest.exception.StockNotFoundException;

public class MarkitOnDemandStockInfoDaoImplTest {

	private static final String STOCK_API_URL = "http://dev.markitondemand.com/MODApis/Api/v2/Quote/json?symbol=%s";
	private static final String STOCK_SYMBOL = "CAB";
	private MarkitOnDemandStockInfoDaoImpl testee;

	@Before
	public void setUp() {
		this.testee = new MarkitOnDemandStockInfoDaoImpl();
		this.testee.setStockURLPattern(STOCK_API_URL);
	}

	@Test(expected = RuntimeException.class)
	public void testBadURL() throws StockNotFoundException {
		this.testee.setStockURLPattern("http://0.0.0.0");
		Assert.assertNull(this.testee.getStockInfo(STOCK_SYMBOL));
	}

	@Test
	public void testGetStockForCabelasInitalAndCached() throws StockNotFoundException {
		this.testee.setCacheTime(Integer.MAX_VALUE);
		long beginTime = System.currentTimeMillis();
		StockInfo nonCached = this.testee.getStockInfo(STOCK_SYMBOL);
		long endTime = System.currentTimeMillis();
		StockInfo cached = this.testee.getStockInfo(STOCK_SYMBOL);

		Assert.assertTrue(nonCached.getName().equalsIgnoreCase("Cabela's Inc"));
		Assert.assertTrue(beginTime <= nonCached.getRetrievalTime()
				&& nonCached.getRetrievalTime() <= endTime);
		Assert.assertTrue(EqualsBuilder.reflectionEquals(nonCached, cached,
				true));

	}

	@Test
	public void testGetStockForCabelasInitalAndExpiredCache() throws StockNotFoundException {
		this.testee.setCacheTime(0);
		long beginTime1 = System.currentTimeMillis();
		StockInfo nonCached1 = this.testee.getStockInfo(STOCK_SYMBOL);
		long endTime1 = System.currentTimeMillis();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long beginTime2 = System.currentTimeMillis();
		StockInfo nonCached2 = this.testee.getStockInfo(STOCK_SYMBOL);
		long endTime2 = System.currentTimeMillis();

		Assert.assertTrue(nonCached1.getName().equalsIgnoreCase("Cabela's Inc"));
		Assert.assertTrue(beginTime1 <= nonCached1.getRetrievalTime()
				&& nonCached1.getRetrievalTime() <= endTime1);

		Assert.assertTrue(nonCached2.getName().equalsIgnoreCase("Cabela's Inc"));
		Assert.assertTrue(beginTime2 <= nonCached2.getRetrievalTime()
				&& nonCached2.getRetrievalTime() <= endTime2);

		Assert.assertFalse(EqualsBuilder.reflectionEquals(nonCached1,
				nonCached2, true));

	}

	@Test(expected = RuntimeException.class)
	public void testMalformedURL() throws StockNotFoundException {
		this.testee.setStockURLPattern("NOT A URL PATTERN");
		Assert.assertNull(this.testee.getStockInfo(STOCK_SYMBOL));
	}

	@Test(expected = StockNotFoundException.class)
	public void testNoSymbolFound() throws StockNotFoundException {
		this.testee.getStockInfo("SNE");
	}
}
