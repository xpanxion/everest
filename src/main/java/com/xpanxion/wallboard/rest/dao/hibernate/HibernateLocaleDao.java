package com.xpanxion.wallboard.rest.dao.hibernate;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.xpanxion.wallboard.rest.dto.locale.Locale;
import com.xpanxion.wallboard.rest.spring.rest.repository.ApiWriteSecuredCrudRepository;

@RepositoryRestResource
public interface HibernateLocaleDao extends ApiWriteSecuredCrudRepository<Locale, Long> {

	Locale findByCode(String code);
}
