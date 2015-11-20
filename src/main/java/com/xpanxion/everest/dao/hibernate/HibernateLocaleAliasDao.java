package com.xpanxion.everest.dao.hibernate;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.xpanxion.everest.dto.locale.LocaleAlias;
import com.xpanxion.everest.spring.rest.repository.ApiWriteSecuredCrudRepository;

@RepositoryRestResource(exported = false)
public interface HibernateLocaleAliasDao extends ApiWriteSecuredCrudRepository<LocaleAlias, Long> {

	LocaleAlias findByAlias(String alias);
}
