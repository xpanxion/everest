package com.xpanxion.everest.controllers.data;

import com.xpanxion.everest.dto.locale.Locale;
import com.xpanxion.everest.dto.news.NewsContent;
import com.xpanxion.everest.dto.stock.StockInfo;
import com.xpanxion.everest.dto.weather.Weather;
import com.xpanxion.everest.services.LocaleService;
import com.xpanxion.everest.services.WebToolsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiDataLocaleControllerTest {

	@InjectMocks
	private ApiDataLocaleController testee;
	
	@Mock
	private LocaleService localeService;
	
	@Mock
	private WebToolsService webToolsService;
	
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetWeather() {
		final Locale locale = new Locale();
		locale.setWeatherCode("ABCDE");
		locale.setTimeZone("zone");
		final Weather weather = new Weather();
		Mockito.when(this.localeService.getLocale(5L)).thenReturn(locale);
		Mockito.when(this.webToolsService.getWeather(locale.getWeatherCode(), locale.getTimeZone())).thenReturn(weather);
		final ResponseEntity<Weather> result = this.testee.getWeather(5l);
		assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(result.getBody(), equalTo(weather));
		Mockito.verify(this.localeService).getLocale(5L);
		Mockito.verify(this.webToolsService).getWeather(locale.getWeatherCode(), locale.getTimeZone());
	}
	
	@Test
	public void testGetWeatherUnknownLocale() {
		Mockito.when(this.localeService.getLocale(5L)).thenReturn(null);
		final ResponseEntity<Weather> result = this.testee.getWeather(5l);
		assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(result.getBody(), equalTo(null));
		Mockito.verify(this.localeService).getLocale(5L);
		Mockito.verifyZeroInteractions(this.webToolsService);
	}
	
	@Test
	public void testGetNews() {
		final String newsKeywords = "ABC,BCD,CDE,DEF";
		final Locale locale = new Locale();
		locale.setNewsKeywords(newsKeywords);
		final List<NewsContent> news = new ArrayList<>();
		Mockito.when(this.localeService.getLocale(5L)).thenReturn(locale);
		Mockito.when(this.webToolsService.getNews(newsKeywords)).thenReturn(news);
		final ResponseEntity<List<NewsContent>> result = this.testee.getNews(5L);
		assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(result.getBody(), equalTo(news));
		Mockito.verify(this.localeService).getLocale(5L);
		Mockito.verify(this.webToolsService).getNews(newsKeywords);
	}
	
	@Test
	public void testGetNewsUnknownLocale() {
		Mockito.when(this.localeService.getLocale(5L)).thenReturn(null);
		final ResponseEntity<List<NewsContent>> result = this.testee.getNews(5L);
		assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(result.getBody(), notNullValue());
		assertThat(result.getBody(), empty());
		Mockito.verify(this.localeService).getLocale(5L);
		Mockito.verifyZeroInteractions(this.webToolsService);
	}
	
	@Test
	public void testGetStocks() {
		final String stockSymbols = "ABC,BCD,CDE,DEF";
		final Locale locale = new Locale();
		locale.setStockSymbols(stockSymbols);
		final List<StockInfo> stocks = new ArrayList<>();
		Mockito.when(this.localeService.getLocale(5L)).thenReturn(locale);
		Mockito.when(this.webToolsService.getStockInfos(stockSymbols)).thenReturn(stocks);
		final ResponseEntity<List<StockInfo>> result = this.testee.getStocks(5L);
		assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(result.getBody(), equalTo(stocks));
		Mockito.verify(this.localeService).getLocale(5L);
		Mockito.verify(this.webToolsService).getStockInfos(stockSymbols);
	}
	
	@Test
	public void testGetStocksUnknownLocale() {
		Mockito.when(this.localeService.getLocale(5L)).thenReturn(null);
		final ResponseEntity<List<StockInfo>> result = this.testee.getStocks(5L);
		assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(result.getBody(), empty());
		Mockito.verify(this.localeService).getLocale(5L);
		Mockito.verifyZeroInteractions(this.webToolsService);
	}
}
