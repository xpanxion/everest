package com.xpanxion.wallboard.rest.dao.hibernate;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.xpanxion.wallboard.rest.dto.locale.LocaleAlias;
import com.xpanxion.wallboard.rest.spring.rest.repository.ApiWriteSecuredCrudRepository;

@RepositoryRestResource(exported = false)
public interface HibernateLocaleAliasDao extends ApiWriteSecuredCrudRepository<LocaleAlias, Long> {

	LocaleAlias findByAlias(String alias);
}
