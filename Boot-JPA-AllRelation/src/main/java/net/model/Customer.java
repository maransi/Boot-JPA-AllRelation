package net.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Customer {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String address;
	
	private String city;
	
	private String zipCode;
	
	private String phoneNumber;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, String address, String city, String zipCode, String phoneNumber) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "\n Customer [id=" + id + ", name=" + name + ", address=" + address + ", city=" + city + ", zipCode="
				+ zipCode + ", phoneNumber=" + phoneNumber + "]";
	}

	
}
