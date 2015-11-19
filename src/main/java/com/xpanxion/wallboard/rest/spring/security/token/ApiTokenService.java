package com.xpanxion.wallboard.rest.spring.security.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpanxion.wallboard.rest.dao.hibernate.HibernateApiTokenDao;
import com.xpanxion.wallboard.rest.dto.system.ApiToken;

@Service
public class ApiTokenService {

	@Autowired
	private HibernateApiTokenDao apiTokenDao;
	
	public ApiToken getToken(String token) {
		return this.apiTokenDao.findOneByValue(token);
	}
}
