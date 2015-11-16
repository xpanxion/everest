package com.xpanxion.wallboard.rest.dao.hibernate;

import org.springframework.data.repository.CrudRepository;

import com.xpanxion.wallboard.rest.dto.kudos.Kudo;

public interface HibernateKudoDao extends CrudRepository<Kudo, Long>{

}
