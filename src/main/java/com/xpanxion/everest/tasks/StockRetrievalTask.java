package com.xpanxion.everest.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xpanxion.everest.dao.www.MarkitOnDemandStockInfoDaoImpl;
import com.xpanxion.everest.dto.stock.StockInfo;

public class StockRetrievalTask implements Runnable {

	public static final Logger LOG = LoggerFactory.getLogger(StockRetrievalTask.class);
	
	private final MarkitOnDemandStockInfoDaoImpl stockInfoDao;
	private final String stockSymbol;
	
	private static final int MAX_ALLOWABLE_RETRIEVAL_ATTEMPTS = 5;
	
	public StockRetrievalTask(MarkitOnDemandStockInfoDaoImpl stockInfoDao, String stockSymbol) {
		this.stockInfoDao = stockInfoDao;
		this.stockSymbol = stockSymbol;
	}
	
	@Override
	public void run() {
		StockInfo stockInfo = null;
		int numAttempts = 1;
		while (null == stockInfo && numAttempts <= MAX_ALLOWABLE_RETRIEVAL_ATTEMPTS) {
			LOG.debug("Attempting to retrieve stock {}. (Attempt {})", this.stockSymbol, numAttempts);
			try {
				stockInfo = this.stockInfoDao.getStockInfo(this.stockSymbol);
			} catch (Exception e) {
				this.stockInfoDao.getCache().remove(this.stockSymbol);
				return;
			}
			
			if (null == stockInfo) {
				
				try {
					LOG.debug("FAILED to retrieve stock for {} on attempt {}.", this.stockSymbol, numAttempts);
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					LOG.error("Error sleeping.", e);
				}
			} else {
				LOG.debug("SUCCESSFULLY retrieved stock {}. (Attempt {})", this.stockSymbol, numAttempts);
			}
			numAttempts++;
		}
		
		this.stockInfoDao.getCache().put(this.stockSymbol, stockInfo);
	}

}
