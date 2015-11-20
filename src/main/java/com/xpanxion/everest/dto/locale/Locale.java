package com.xpanxion.everest.dto.locale;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.xpanxion.everest.dto.employee.Employee;
import com.xpanxion.everest.dto.stock.StockInfo;
import com.xpanxion.everest.dto.weather.Weather;

/**
 * This defines a distinct geographic location such as an office, which may have
 * several boards within it.
 * 
 * @author bsmith
 * 
 * 
 */
@Entity
public class Locale implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1607555926192954412L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String backgroundUrl;

	@Column(unique = true)
	private String code;
	
	@Column
	private String greetingMessage;

	@Column
	private String name;
	
	@Column
	private String newsKeywords;
	
	@Transient
	private List<String> news;

	@Column
	private String stockSymbols;

	@Transient
	private List<StockInfo> stocks;

	@Column
	private String timeZone;

	@Column
	private String weatherCode;

	@Transient
	private Weather weather;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "locale")
	private List<LocaleAlias> aliases;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "locale")
	private List<Employee> employees;

	@ManyToOne(cascade = CascadeType.ALL)
	private LocaleTheme theme;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBackgroundUrl() {
		return backgroundUrl;
	}

	public void setBackgroundUrl(String backgroundUrl) {
		this.backgroundUrl = backgroundUrl;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGreetingMessage() {
		return greetingMessage;
	}

	public void setGreetingMessage(String greetingMessage) {
		this.greetingMessage = greetingMessage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNewsKeywords() {
		return newsKeywords;
	}

	public void setNewsKeywords(String newsKeywords) {
		this.newsKeywords = newsKeywords;
	}

	public String getStockSymbols() {
		return stockSymbols;
	}

	public void setStockSymbols(String stockSymbols) {
		this.stockSymbols = stockSymbols;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getWeatherCode() {
		return weatherCode;
	}

	public void setWeatherCode(String weatherCode) {
		this.weatherCode = weatherCode;
	}
	
	public List<LocaleAlias> getAliases() {
		return aliases;
	}

	public void setAliases(List<LocaleAlias> aliases) {
		this.aliases = aliases;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	public List<String> getNews() {
		return news;
	}

	public void setNews(List<String> news) {
		this.news = news;
	}
	
	public List<StockInfo> getStocks() {
		return stocks;
	}

	public void setStocks(List<StockInfo> stocks) {
		this.stocks = stocks;
	}
	
	/**
	 * @return the theme
	 */
	public LocaleTheme getTheme() {
		return theme;
	}

	/**
	 * @param theme the theme to set
	 */
	public void setTheme(LocaleTheme theme) {
		this.theme = theme;
	}

}