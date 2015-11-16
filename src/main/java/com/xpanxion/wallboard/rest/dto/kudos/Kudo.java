package com.xpanxion.wallboard.rest.dto.kudos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.xpanxion.wallboard.rest.dto.employee.Employee;

@Entity
public class Kudo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String type;
	
	@Column
	private String title;
	
	@Column
	private String description;
	
	@Column
	private String imageURL;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Employee employee;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Employee referringEmployee;

	@Column
	private Long createTime;
	
	@Column
	private Long expirationTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getReferringEmployee() {
		return referringEmployee;
	}

	public void setReferringEmployee(Employee referringEmployee) {
		this.referringEmployee = referringEmployee;
	}
	
	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Long expirationTime) {
		this.expirationTime = expirationTime;
	}
}
