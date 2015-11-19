package com.xpanxion.wallboard.rest.dao.hibernate;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.xpanxion.wallboard.rest.dto.system.ApiToken;
import com.xpanxion.wallboard.rest.spring.rest.repository.ApiWriteSecuredCrudRepository;

@RepositoryRestResource(exported = false)
public interface HibernateApiTokenDao extends ApiWriteSecuredCrudRepository<ApiToken, Long> {

	/**
	 * 
	 * @param token
	 * @return
	 */
	ApiToken findOneByValue(String token);

}
