package com.xpanxion.everest.services;

import java.util.List;

import com.xpanxion.everest.dto.news.NewsContent;
import com.xpanxion.everest.dto.stock.StockInfo;
import com.xpanxion.everest.dto.weather.Weather;

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
	List<NewsContent> getNews(String location);
}
