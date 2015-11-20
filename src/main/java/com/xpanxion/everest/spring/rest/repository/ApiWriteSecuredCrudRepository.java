package com.xpanxion.everest.spring.rest.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.access.annotation.Secured;

import com.xpanxion.everest.spring.security.SecurityProperties.SecurityRoles;

/**
 * Interface for generic CRUD operations on a repository for a specific type. All write operations
 * utilize the @{@link Secured} for the defined role at {@link SecurityRoles#API_ADMIN}.
 * 
 * @author tlopez
 *
 */
@NoRepositoryBean
public interface ApiWriteSecuredCrudRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {

	@Secured({ SecurityRoles.API_ADMIN })
	@Override
	<S extends T> S save(S entity);

	@Secured({ SecurityRoles.API_ADMIN })
	@Override
	<S extends T> Iterable<S> save(Iterable<S> entities);

	@Secured({ SecurityRoles.API_ADMIN })
	@Override
	void delete(ID id);

	@Secured({ SecurityRoles.API_ADMIN })
	@Override
	void delete(T entity);

	@Secured({ SecurityRoles.API_ADMIN })
	@Override
	void delete(Iterable<? extends T> entities);
}
