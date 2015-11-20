package com.xpanxion.everest.dao.hibernate;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.xpanxion.everest.dto.system.ApiToken;
import com.xpanxion.everest.spring.rest.repository.ApiWriteSecuredCrudRepository;

@RepositoryRestResource(exported = false)
public interface HibernateApiTokenDao extends ApiWriteSecuredCrudRepository<ApiToken, Long> {

	/**
	 * 
	 * @param token
	 * @return
	 */
	ApiToken findOneByValue(String token);

}
