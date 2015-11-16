package com.xpanxion.wallboard.rest.dao.hibernate;

import org.springframework.data.repository.CrudRepository;

import com.xpanxion.wallboard.rest.dto.locale.LocaleAlias;

public interface HibernateLocaleAliasDao extends CrudRepository<LocaleAlias, Long> {

	LocaleAlias findByAlias(String alias);
}
