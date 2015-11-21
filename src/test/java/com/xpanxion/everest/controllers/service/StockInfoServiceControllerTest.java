package com.xpanxion.everest.controllers.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.xpanxion.everest.dto.stock.StockInfo;
import com.xpanxion.everest.services.WebToolsService;

public class StockInfoServiceControllerTest {

	@InjectMocks
	private StockInfoServiceController testee;
	
	@Mock
	private WebToolsService webToolsService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetStock() {
		final StockInfo stockInfo = new StockInfo(); 
		Mockito.when(this.webToolsService.getStockInfo("AAPL")).thenReturn(stockInfo);
		final ResponseEntity<StockInfo> result = this.testee.getStock("AAPL");
		Mockito.verify(this.webToolsService).getStockInfo("AAPL");
		assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(result.getBody(), equalTo(stockInfo));
	}
	
	@Test
	public void testGetStocks() {
		final List<String> stockSymbols = new ArrayList<>();
		stockSymbols.add("AAPL");
		stockSymbols.add("ABCD");
		stockSymbols.add("GOOG");
		final StockInfo stock1 = new StockInfo();
		final StockInfo stock2 = null;
		final StockInfo stock3 = new StockInfo();
		Mockito.when(this.webToolsService.getStockInfo("AAPL")).thenReturn(stock1);
		Mockito.when(this.webToolsService.getStockInfo("ABCD")).thenReturn(stock2);
		Mockito.when(this.webToolsService.getStockInfo("GOOG")).thenReturn(stock3);
		final ResponseEntity<List<StockInfo>> result = this.testee.getStocks(stockSymbols);
		Mockito.verify(this.webToolsService).getStockInfo("AAPL");
		Mockito.verify(this.webToolsService).getStockInfo("ABCD");
		Mockito.verify(this.webToolsService).getStockInfo("GOOG");
		assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(result.getBody(), hasItems(stock1, stock3));
		assertThat(result.getBody().size(), equalTo(2));
	}
}
