package com.xpanxion.wallboard.rest.services;

import java.util.List;

import com.xpanxion.wallboard.rest.dto.stock.StockInfo;
import com.xpanxion.wallboard.rest.dto.weather.Weather;

public interface WebToolsService {

	/**
	 * 
	 * @param location
	 * @return
	 */
	Weather getWeather(String location);

	/**
	 * 
	 * @param symbol
	 * @return
	 */
	StockInfo getStockInfo(String symbol);
	
	/**
	 * 
	 * @param symbol
	 * @return
	 */
	List<StockInfo> getStockInfos(String symbols);
	
	/**
	 * 
	 * @param location
	 * @return
	 */
	List<String> getNews(String location);
}
