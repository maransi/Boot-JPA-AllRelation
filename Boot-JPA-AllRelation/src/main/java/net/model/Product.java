package net.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	private String description;
	
	private String alias;
	
	
	@OneToOne
	private Unit unit;
	
	private Float unitPrice;

	@OneToOne
	private Supplier supplier;
	
	
	public Product() {
		// TODO Auto-generated constructor stub
	}


	public Product(String description, String alias, Unit unit, Float unitPrice, Supplier supplier) {
		super();
		this.description = description;
		this.alias = alias;
		this.unit = unit;
		this.unitPrice = unitPrice;
		this.supplier = supplier;
	}


	@Override
	public String toString() {
		return "\n Product [id=" + id + ", description=" + description + ", alias=" + alias + ", unit=" + unit
				+ ", unitPrice=" + unitPrice + ", supplier=" + supplier + "]";
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public Unit getUnit() {
		return unit;
	}


	public void setUnit(Unit unit) {
		this.unit = unit;
	}


	public Float getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}


	public Supplier getSupplier() {
		return supplier;
	}


	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	
	
}
