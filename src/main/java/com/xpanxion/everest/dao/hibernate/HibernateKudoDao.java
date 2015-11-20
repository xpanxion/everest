package com.xpanxion.everest.dao.hibernate;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.xpanxion.everest.dto.kudos.Kudo;
import com.xpanxion.everest.spring.rest.repository.ApiWriteSecuredCrudRepository;

@RepositoryRestResource(collectionResourceRel = "kudos", path = "kudos")
public interface HibernateKudoDao extends ApiWriteSecuredCrudRepository<Kudo, Long>{

}
