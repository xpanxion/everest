package com.xpanxion.wallboard.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xpanxion.wallboard.rest.dao.hibernate.HibernateLocaleAliasDao;
import com.xpanxion.wallboard.rest.dao.hibernate.HibernateLocaleDao;
import com.xpanxion.wallboard.rest.dto.locale.Locale;
import com.xpanxion.wallboard.rest.dto.locale.LocaleAlias;

@Service
public class LocaleServiceImpl implements LocaleService {

	@Autowired
	private HibernateLocaleDao localeDao;
	
	@Autowired
	private HibernateLocaleAliasDao localeAliasDao;
	
	@Autowired
	private WebToolsService webToolsService;
	
	@Override
	public Locale getLocale(String location) {
		if (StringUtils.isEmpty(location)) {
			return null;
		}
		
		Locale locale = this.localeDao.findByCode(location.toUpperCase());
		if (null == locale) {
			final LocaleAlias localeAlias = this.localeAliasDao.findByAlias(location);
			if (null != localeAlias) {
				locale = localeAlias.getLocale();
			}
		}
		
		return locale;
	}

	@Override
	public Locale getLocale(Long id) {
		return this.localeDao.findOne(id);
	}

	@Override
	public List<Locale> getAllLocales() {
		return (List<Locale>) this.localeDao.findAll();
	}

	@Override
	public Locale populateWebContent(Locale locale) {
		if (null != locale) {
			locale.setNews(this.webToolsService.getNews(locale.getNewsKeywords()));
			locale.setWeather(this.webToolsService.getWeather(locale.getWeatherCode()));
			locale.setStocks(this.webToolsService.getStockInfos(locale.getStockSymbols()));
		}
		return locale;
	}
	
}
