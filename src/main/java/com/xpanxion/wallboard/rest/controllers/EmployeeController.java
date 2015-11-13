package com.xpanxion.wallboard.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xpanxion.wallboard.rest.dto.employee.Employee;
import com.xpanxion.wallboard.rest.dto.employee.EmployeeSearchRequest;
import com.xpanxion.wallboard.rest.services.EmployeeService;
import com.xpanxion.wallboard.rest.util.ListUtils;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = "/api/{rendition}/{version}/employees", method = RequestMethod.GET)
	public List<Employee> getAllEmployees(@RequestParam(required = false) String location) {
		
		final EmployeeSearchRequest searchRequest = new EmployeeSearchRequest();
		ListUtils.nullSafeAdd(searchRequest.getLocations(), location);
		
		return this.employeeService.getEmployees(searchRequest);
	}
	
	@RequestMapping(value = "/api/{rendition}/{version}/employees/{id}", method = RequestMethod.GET)
	public Employee getEmployeeById(@PathVariable Long id) {
		final EmployeeSearchRequest searchRequest = new EmployeeSearchRequest();
		ListUtils.nullSafeAdd(searchRequest.getIds(), id);
		return this.employeeService.getEmployees(searchRequest).get(0);
	}
	
	@RequestMapping(value = "/api/{rendition}/{version}/employees/{id}", method = RequestMethod.POST)
	public Employee updateEmployee(@PathVariable Long id) {
		// TODO
		throw new UnsupportedOperationException();
	}
	
	@RequestMapping(value = "/api/{rendition}/{version}/employees/{id}", method = RequestMethod.DELETE)
	public Employee deleteEmployee(@PathVariable Long id) {
		// TODO
		throw new UnsupportedOperationException();
	}
	
	@RequestMapping(value = "/api/{rendition}/{version}/employees/add", method = RequestMethod.PUT)
	public Employee addNewEmployee(Employee employee) {
		return this.employeeService.saveEmployee(employee);
	}
}
