package com.xpanxion.wallboard.rest.services;

import com.xpanxion.wallboard.rest.dto.locale.Locale;

public interface LocaleService {

	/**
	 * 
	 * @param location
	 * @return
	 */
	Locale getLocale(String location);

}
