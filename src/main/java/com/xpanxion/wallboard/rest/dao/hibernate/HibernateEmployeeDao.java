package com.xpanxion.wallboard.rest.dao.hibernate;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.xpanxion.wallboard.rest.dto.employee.Employee;
import com.xpanxion.wallboard.rest.spring.rest.repository.ApiWriteSecuredCrudRepository;

@RepositoryRestResource
public interface HibernateEmployeeDao extends ApiWriteSecuredCrudRepository<Employee, Long> {

	@Query("select e from Employee e where e.locale.code in :localeCodes")
	List<Employee> findAllByLocaleCode(List<String> localeCodes);

}
