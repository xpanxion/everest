package com.xpanxion.wallboard.rest.dao.hibernate;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.xpanxion.wallboard.rest.dto.kudos.Kudo;
import com.xpanxion.wallboard.rest.spring.rest.repository.ApiWriteSecuredCrudRepository;

@RepositoryRestResource(collectionResourceRel = "kudos", path = "kudos")
public interface HibernateKudoDao extends ApiWriteSecuredCrudRepository<Kudo, Long>{

}
