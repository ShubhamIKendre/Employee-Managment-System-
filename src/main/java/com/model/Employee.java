package com.model;

public class Employee{
	
	int id;
	String firstname;
	String lastname;
	String email;
	String password;
	String city;
	String company_name;
	String contact;
	
	
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Employee(String firstname, String lastname, String email, String password, String city, String company_name,
			String contact) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.city = city;
		this.company_name = company_name;
		this.contact = contact;
	}
	
	public Employee(int id, String firstname, String lastname, String email, String password, String city,
			String company_name, String contact) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.city = city;
		this.company_name = company_name;
		this.contact = contact;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	
}
