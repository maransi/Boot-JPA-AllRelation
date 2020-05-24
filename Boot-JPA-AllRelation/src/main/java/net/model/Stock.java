package net.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

@Entity
public class Stock {

	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String address;
	
	private String city;
	
	private String zipCode;
	
	private String phoneNumber;
	
	@ManyToMany( cascade = {CascadeType.PERSIST, CascadeType.REMOVE}) 
	@JoinTable(	name="stock_product",
				joinColumns=@JoinColumn(name = "stock_id"),
				inverseJoinColumns = @JoinColumn(name="product_id"),
				uniqueConstraints = @UniqueConstraint( columnNames = {"stock_id", "product_id"} ) )
	private List<Product> stockProduct = new ArrayList<>();
	
	public Stock() {
		// TODO Auto-generated constructor stub
	}

	public Stock(String name, String address, String city, String zipCode, String phoneNumber) {
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

	public List<Product> getStockProduct() {
		return stockProduct;
	}

	public void setStockProduct(List<Product> stockProduct) {
		this.stockProduct = stockProduct;
	}

	@Override
	public String toString() {
		return "\n Stock [id=" + id + ", name=" + name + ", address=" + address + ", city=" + city + ", zipCode=" + zipCode
				+ ", phoneNumber=" + phoneNumber + ", stockProduct=" + stockProduct + "]";
	}

	
	
}
