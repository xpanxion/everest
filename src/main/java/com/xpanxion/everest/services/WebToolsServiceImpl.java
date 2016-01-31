package com.xpanxion.everest.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpanxion.everest.dao.NewsDao;
import com.xpanxion.everest.dao.StockInfoDao;
import com.xpanxion.everest.dao.WeatherDao;
import com.xpanxion.everest.dto.news.NewsContent;
import com.xpanxion.everest.dto.stock.StockInfo;
import com.xpanxion.everest.dto.weather.Weather;
import com.xpanxion.everest.exception.StockNotFoundException;
import com.xpanxion.everest.util.ListUtils;

@Service
public class WebToolsServiceImpl implements WebToolsService {

	public static final String COMMA = ",";
	
	@Autowired
	private WeatherDao weatherDao;
	
	@Autowired
	private StockInfoDao stockInfoDao;
	
	@Autowired
	private NewsDao newsDao;
	
	@Override
	public Weather getWeather(String location) {
		return this.weatherDao.getWeather(location);
	}

	@Override
	public StockInfo getStockInfo(String symbol) {
		try {
			return this.stockInfoDao.getStockInfo(symbol);
		} catch (StockNotFoundException e) {
			return null;
		}
	}

	/**
	 * TODO - Return a <tt>List</tt> of POJO objects (e.g. <tt>NewsContent</tt> instead of <tt>String</tt>)
	 */
	@Override
	public List<NewsContent> getNews(String keywords) {
		
		if (null == keywords) {
			return new ArrayList<>();
		}
		
		final List<String> keywordsList = Arrays.asList(keywords.split(COMMA));
		return this.newsDao.getNews(keywordsList);
	}

	@Override
	public List<StockInfo> getStockInfos(String symbols) {
		final List<StockInfo> stocks = new ArrayList<>();
		
		if (null == symbols) {
			return stocks;
		}
		
		final String[] stockSymbols = symbols.split(COMMA);
		for(final String stockSymbol : stockSymbols) {
			ListUtils.nullSafeAdd(stocks, this.getStockInfo(stockSymbol.trim()));
		}
		
		return stocks;
	}
}
