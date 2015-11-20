package com.xpanxion.everest.spring.rest.dtoprocessors;

import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import com.xpanxion.everest.dto.employee.Employee;
import com.xpanxion.everest.util.RestResourceUtils;

@Component
public class EmployeeDtoProcessor {
	
	@Bean
	public ResourceProcessor<Resource<Employee>> employeeProcessor() {
		
		return new ResourceProcessor<Resource<Employee>>() {
			
			@Override
			public Resource<Employee> process(Resource<Employee> resource) {
				// TODO - Why is employee added in the first place?
				RestResourceUtils.removeAllLinksByRel(resource.getLinks(), "employee");
				return resource;
			}
		};
	}
}