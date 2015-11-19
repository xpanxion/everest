package com.xpanxion.wallboard.rest.services;

import java.util.List;

import com.xpanxion.wallboard.rest.dto.locale.Locale;

public interface LocaleService {

	/**
	 * 
	 * @param location
	 * @return
	 */
	Locale getLocale(String location);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Locale getLocale(Long id);

	/**
	 * 
	 * @return
	 */
	List<Locale> getAllLocales();

	/**
	 * 
	 * @param locale
	 */
	Locale populateWebContent(Locale locale);

}
