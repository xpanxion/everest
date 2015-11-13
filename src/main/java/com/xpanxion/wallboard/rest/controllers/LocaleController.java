package com.xpanxion.wallboard.rest.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xpanxion.wallboard.rest.dto.employee.Employee;
import com.xpanxion.wallboard.rest.dto.locale.Locale;
import com.xpanxion.wallboard.rest.dto.stock.StockInfo;
import com.xpanxion.wallboard.rest.dto.weather.Weather;
import com.xpanxion.wallboard.rest.services.LocaleService;
import com.xpanxion.wallboard.rest.services.WebToolsService;

@RestController
public class LocaleController {

	@Autowired
	private LocaleService localeService;
	
	@Autowired
	private WebToolsService webToolsService;
	
	@RequestMapping(value = "/api/{rendition}/{version}/locales/{location}")
	public Locale getLocale(@PathVariable String location) {
		final Locale locale = this.localeService.getLocale(location);
		if (null != locale) {
			locale.setNews(this.webToolsService.getNews(locale.getNewsKeywords()));
			locale.setWeather(this.webToolsService.getWeather(locale.getWeatherCode()));
			locale.setStocks(this.webToolsService.getStockInfos(locale.getStockSymbols()));
		}
		return locale;
	}
	
	@RequestMapping(value = "/api/{rendition}/{version}/locales/{location}/weather", method = RequestMethod.GET)
	public Weather getWeather(@PathVariable String location) {
		final Locale locale = this.localeService.getLocale(location);
		if (null != locale) {
			return this.webToolsService.getWeather(locale.getWeatherCode());
		}
		return null;
	}
	
	@RequestMapping(value = "/api/{rendition}/{version}/locales/{location}/news")
	public List<String> getNews(@PathVariable String location) {
		final Locale locale = this.localeService.getLocale(location);
		if (null != locale) {
			return this.webToolsService.getNews(locale.getNewsKeywords());
		}
		return new ArrayList<>();
	}
	
	@RequestMapping(value = "/api/{rendition}/{version}/locales/{location}/stocks", method = RequestMethod.GET)
	public List<StockInfo> getStocks(@PathVariable String location) {
		List<StockInfo> stocks = new ArrayList<>();
		final Locale locale = this.localeService.getLocale(location);
		if (null != locale) {
			stocks = this.webToolsService.getStockInfos(locale.getStockSymbols());
		}
		return stocks;
	}
	
	@RequestMapping(value = "/api/{rendition}/{version}/locales/{location}/employees", method = RequestMethod.GET)
	public List<Employee> getEmployees(@PathVariable String location) {
		List<Employee> employees = new ArrayList<>();
		
		final Locale locale = this.localeService.getLocale(location);
		if (null != locale) {
			employees = locale.getEmployees();
		}
		
		return employees;
	}
}
