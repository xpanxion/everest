package com.xpanxion.everest.dao.hibernate;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.xpanxion.everest.dto.locale.Locale;
import com.xpanxion.everest.spring.rest.repository.ApiWriteSecuredCrudRepository;

@RepositoryRestResource
public interface HibernateLocaleDao extends ApiWriteSecuredCrudRepository<Locale, Long> {

	Locale findByCode(String code);
}
