package com.xpanxion.wallboard.rest.dao.hibernate;

import org.springframework.data.repository.CrudRepository;

import com.xpanxion.wallboard.rest.dto.system.ApiToken;

public interface HibernateApiTokenDao extends CrudRepository<ApiToken, Long> {

	/**
	 * 
	 * @param token
	 * @return
	 */
	ApiToken findOneByValue(String token);

}
