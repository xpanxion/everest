package com.xpanxion.everest.controllers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xpanxion.everest.controllers.BaseController;
import com.xpanxion.everest.dto.stock.StockInfo;
import com.xpanxion.everest.services.WebToolsService;

@RestController
public class StockInfoServiceController extends BaseController {

	@Autowired
	private WebToolsService webToolsService;
	
	@RequestMapping(value = BASE_PATH + "/service/stocks/{stockSymbol}", method = RequestMethod.GET)
	public ResponseEntity<StockInfo> getStock(@PathVariable String stockSymbol) {
		final StockInfo stockInfo = this.webToolsService.getStockInfo(stockSymbol);
		return ResponseEntity.ok(stockInfo);
	}
	
	@RequestMapping(value = BASE_PATH + "/service/stocks", method = RequestMethod.GET)
	public ResponseEntity<List<StockInfo>> getStocks(@RequestParam List<String> stockSymbols) {
		final List<StockInfo> stocks = new ArrayList<>();
		for(String stockSymbol : stockSymbols) {
			stocks.add(this.webToolsService.getStockInfo(stockSymbol));
		}
		return ResponseEntity.ok(stocks);
	}
}
