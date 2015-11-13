package com.xpanxion.wallboard.rest.services;

import java.util.List;

import com.xpanxion.wallboard.rest.dto.employee.Employee;
import com.xpanxion.wallboard.rest.dto.employee.EmployeeSearchRequest;

public interface EmployeeService {

	List<Employee> getEmployees(EmployeeSearchRequest request);

	Employee saveEmployee(Employee employee);

}
