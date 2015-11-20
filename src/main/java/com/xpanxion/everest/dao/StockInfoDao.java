package com.xpanxion.everest.dao;

import com.xpanxion.everest.dto.stock.StockInfo;
import com.xpanxion.everest.exception.StockNotFoundException;

/**
 * Dao that will get the information for a particular company's stock
 * 
 * @author apofahl
 */

public interface StockInfoDao {
	/**
	 * Returns stock quote info for given company symbol
	 * 
	 * @param symbol
	 *            the company stock symbol
	 * @return stock quote object
	 * @throws StockNotFoundException 
	 * @throws Exception
	 */
	StockInfo getStockInfo(String symbol) throws StockNotFoundException;
}
