package com.xpanxion.wallboard.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpanxion.wallboard.rest.dao.hibernate.HibernateEmployeeDao;
import com.xpanxion.wallboard.rest.dto.employee.Employee;
import com.xpanxion.wallboard.rest.dto.employee.EmployeeSearchRequest;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private HibernateEmployeeDao employeeDao;
	
	@Override
	public List<Employee> getEmployees(EmployeeSearchRequest request) {
	
		if (null == request) {
			throw new RuntimeException("Bad Employee Search Request.");
		}
		
		Iterable<Employee> employees;
		if (!request.getIds().isEmpty()) {
			employees = this.employeeDao.findAll(request.getIds());
		} else if (!request.getLocations().isEmpty()) {
			employees = this.employeeDao.findAllByLocaleCode(request.getLocations());
		} else {
			employees = this.employeeDao.findAll();
		}
		
		return (List<Employee>) employees;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return this.employeeDao.save(employee);
	}
}
