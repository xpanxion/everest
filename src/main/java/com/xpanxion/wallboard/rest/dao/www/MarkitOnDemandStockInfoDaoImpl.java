package com.xpanxion.wallboard.rest.dao.www;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpanxion.wallboard.rest.dao.StockInfoDao;
import com.xpanxion.wallboard.rest.dto.stock.StockInfo;
import com.xpanxion.wallboard.rest.exception.StockNotFoundException;
import com.xpanxion.wallboard.rest.tasks.StockRetrievalTask;

@Repository
public class MarkitOnDemandStockInfoDaoImpl implements StockInfoDao {

	private static final Logger LOG = LoggerFactory.getLogger(MarkitOnDemandStockInfoDaoImpl.class);
	
	public static final int MAX_NUMBER_STOCK_TICKERS = 10;
	
	private final Map<String, StockInfo> cache = new HashMap<String, StockInfo>();	
	private final String stockURLPattern = "http://dev.markitondemand.com/MODApis/Api/v2/Quote/json?symbol=%s";
	private final Integer timeInCache = 300000;

	@Override
	public StockInfo getStockInfo(String symbol) throws StockNotFoundException {
		StockInfo cacheValue = this.cache.get(symbol);
		if (cacheValue == null || (System.currentTimeMillis() - cacheValue.getRetrievalTime() > this.timeInCache)) {
			LOG.debug("Cache MISS for stock symbol '{}'. Fetching live data...", symbol);
			final StockInfo stockInfo;
			stockInfo = this.parseStockFromFeed(symbol);
			stockInfo.setRetrievalTime(new Date().getTime());
			if (null != stockInfo) {
				this.cache.put(symbol, stockInfo);
				cacheValue = stockInfo;
			}
		} else {
			LOG.debug("Cache HIT for stock symbol '{}'. Using cached value.", symbol);
		}
		return cacheValue;
	}

	/**
	 * Pulls stock information from source feed
	 * 
	 * @param symbol
	 *            symbol of company pulling information for
	 * @return StockTicker object
	 * @throws StockNotFoundException 
	 * @throws Exception
	 *             Exception for if the stock symbol isn't found
	 */
	private StockInfo parseStockFromFeed(String symbol) throws StockNotFoundException {
		StockInfo stockInfo = null;
		
		final ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.USE_ANNOTATIONS, true);
		final String url = String.format(this.stockURLPattern, symbol);
		try (final BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
			
			final String jsonData = new String(reader.readLine());
			if (jsonData.contains("No symbol matches found for")) {
				throw new StockNotFoundException(symbol);
			}
			
			stockInfo = mapper.readValue(jsonData, StockInfo.class);
			
		} catch (IOException e) {
			LOG.error("Unable to connect to stock feed for {}", symbol);
			throw new RuntimeException(e);
		}
		
		return stockInfo;
	}
	
	public Map<String, StockInfo> getCache() {
		return this.cache;
	}
	
	@Scheduled(fixedDelay = 60000)
	public void executeStockRetrievalJob() {
		
		for(Entry<String, StockInfo> entry : this.cache.entrySet()) {
			(new Thread(new StockRetrievalTask(this, entry.getKey()))).start();
		}
		LOG.info("*** Stock Retrieval Job executed ***");
	}
}
