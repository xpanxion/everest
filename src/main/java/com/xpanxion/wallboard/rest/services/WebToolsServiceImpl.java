package com.xpanxion.wallboard.rest.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpanxion.wallboard.rest.dao.NewsDao;
import com.xpanxion.wallboard.rest.dao.StockInfoDao;
import com.xpanxion.wallboard.rest.dao.WeatherDao;
import com.xpanxion.wallboard.rest.dto.stock.StockInfo;
import com.xpanxion.wallboard.rest.dto.weather.Weather;
import com.xpanxion.wallboard.rest.exception.StockNotFoundException;
import com.xpanxion.wallboard.rest.util.ListUtils;

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
	public List<String> getNews(String keywords) {
		
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
