package com.xpanxion.everest.dto.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xpanxion.everest.dto.locale.Locale;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@ManyToOne
	@JoinColumn(name="locale_code", referencedColumnName = "code")
	private Locale locale;
	
	@Column
	private String title;
	
	@Column
	private String emailAddress;
	
	@Column
	private String workPhone;
	
	@Column
	private String cellPhone;
	
	@Column
	private String biography;
	
	@Column
	private String profileImageUrl;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getWorkPhone() {
		return workPhone;
	}
	
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	
	public String getCellPhone() {
		return cellPhone;
	}
	
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	
	public String getBiography() {
		return biography;
	}
	
	public void setBiography(String biography) {
		this.biography = biography;
	}
	
	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	
	public Locale getLocale() {
		return locale;
	}

	public void setLocaleCode(Locale locale) {
		this.locale = locale;
	}
}
