package com.xpanxion.wallboard.rest.dao.hibernate;

import org.springframework.data.repository.CrudRepository;

import com.xpanxion.wallboard.rest.dto.locale.Locale;

public interface LocaleDao extends CrudRepository<Locale, Long>{

	Locale findByCode(String code);
}
