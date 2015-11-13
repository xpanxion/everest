package com.xpanxion.wallboard.rest.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xpanxion.wallboard.rest.dto.stock.StockInfo;
import com.xpanxion.wallboard.rest.services.WebToolsService;

@RestController
public class StockInfoController {

	@Autowired
	private WebToolsService webToolsService;
	
	@RequestMapping(value = "/api/{rendition}/{version}/stocks/{stockSymbol}", method = RequestMethod.GET)
	public StockInfo getStock(@PathVariable String stockSymbol) {
		return this.webToolsService.getStockInfo(stockSymbol);
	}
	
	@RequestMapping(value = "/api/{rendition}/{version}/stocks", method = RequestMethod.GET)
	public List<StockInfo> getStocks(@RequestParam List<String> stockSymbols) {
		final List<StockInfo> stocks = new ArrayList<>();
		for(String stockSymbol : stockSymbols) {
			stocks.add(this.webToolsService.getStockInfo(stockSymbol));
		}
		return stocks;
	}
}
