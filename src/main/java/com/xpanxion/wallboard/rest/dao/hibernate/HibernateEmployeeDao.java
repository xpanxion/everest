package com.xpanxion.wallboard.rest.dao.hibernate;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xpanxion.wallboard.rest.dto.employee.Employee;

@Repository
public interface HibernateEmployeeDao extends CrudRepository<Employee, Long> {

	@Query("select e from Employee e where e.locale.code in :localeCodes")
	List<Employee> findAllByLocaleCode(List<String> localeCodes);

}
