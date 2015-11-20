package com.xpanxion.everest.dao.hibernate;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.xpanxion.everest.dto.employee.Employee;
import com.xpanxion.everest.spring.rest.repository.ApiWriteSecuredCrudRepository;

@RepositoryRestResource
public interface HibernateEmployeeDao extends ApiWriteSecuredCrudRepository<Employee, Long> {

	@Query("select e from Employee e where e.locale.code in :localeCodes")
	List<Employee> findAllByLocaleCode(List<String> localeCodes);

}
