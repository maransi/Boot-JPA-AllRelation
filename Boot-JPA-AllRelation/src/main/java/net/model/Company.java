package net.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Company {

	@EmbeddedId
	private CompanyId id;

	private String name;
	
	@Embedded
	@AttributeOverrides({
	    @AttributeOverride(name = "postcode", column = @Column(name = "zip")),
	    @AttributeOverride(name = "city", column = @Column(name = "town"))
	})	
	private Address address;
	
	public Company() {
		// TODO Auto-generated constructor stub
	}

	public Company(String name, Address address) {
		super();
		this.name = name;
		this.address = address;
	}


	public CompanyId getId() {
		return id;
	}

	public void setId(CompanyId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	
}
