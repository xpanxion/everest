package com.xpanxion.everest.controllers.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xpanxion.everest.controllers.BaseController;
import com.xpanxion.everest.dto.locale.Locale;
import com.xpanxion.everest.dto.news.NewsContent;
import com.xpanxion.everest.dto.stock.StockInfo;
import com.xpanxion.everest.dto.weather.Weather;
import com.xpanxion.everest.services.LocaleService;
import com.xpanxion.everest.services.WebToolsService;

@RestController
public class ApiDataLocaleController extends BaseController {

	@Autowired
	private LocaleService localeService;
	
	@Autowired
	private WebToolsService webToolsService;
	
	@RequestMapping(value = BASE_PATH + "/data/locales/{id}/weather", method = RequestMethod.GET)
	public ResponseEntity<Weather> getWeather(@PathVariable Long id) {
		final Locale locale = this.localeService.getLocale(id);
		Weather weather = null;
		if (null != locale) {
			weather = this.webToolsService.getWeather(locale.getWeatherCode());
		}
		return ResponseEntity.ok(weather);
	}
	
	@RequestMapping(value = BASE_PATH + "/data/locales/{id}/news")
	public ResponseEntity<List<NewsContent>> getNews(@PathVariable Long id) {
		final Locale locale = this.localeService.getLocale(id);
		List<NewsContent> news = new ArrayList<>();
		if (null != locale) {
			news = this.webToolsService.getNews(locale.getNewsKeywords());
		}
		return ResponseEntity.ok(news);
	}
	
	@RequestMapping(value = BASE_PATH + "/data/locales/{id}/stocks", method = RequestMethod.GET)
	public ResponseEntity<List<StockInfo>> getStocks(@PathVariable Long id) {
		List<StockInfo> stocks = new ArrayList<>();
		final Locale locale = this.localeService.getLocale(id);
		if (null != locale) {
			stocks = this.webToolsService.getStockInfos(locale.getStockSymbols());
		}
		return ResponseEntity.ok(stocks);
	}
}
