package com.xpanxion.everest.services;

import java.util.List;

import com.xpanxion.everest.dto.locale.Locale;

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
