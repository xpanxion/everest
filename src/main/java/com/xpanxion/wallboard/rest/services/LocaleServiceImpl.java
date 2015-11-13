package com.xpanxion.wallboard.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xpanxion.wallboard.rest.dao.hibernate.LocaleAliasDao;
import com.xpanxion.wallboard.rest.dao.hibernate.LocaleDao;
import com.xpanxion.wallboard.rest.dto.locale.Locale;
import com.xpanxion.wallboard.rest.dto.locale.LocaleAlias;

@Service
public class LocaleServiceImpl implements LocaleService {

	@Autowired
	private LocaleDao localeDao;
	
	@Autowired
	private LocaleAliasDao localeAliasDao;
	
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
	
}
