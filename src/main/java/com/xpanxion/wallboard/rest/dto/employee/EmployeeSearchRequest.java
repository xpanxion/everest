package com.xpanxion.wallboard.rest.dto.employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSearchRequest {

	private List<Long> ids = new ArrayList<>();
	private List<String> locations = new ArrayList<>();
	
	public List<Long> getIds() {
		return ids;
	}
	
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	
	public List<String> getLocations() {
		return locations;
	}
	
	public void setLocations(List<String> locations) {
		this.locations = locations;
	}
	
}
