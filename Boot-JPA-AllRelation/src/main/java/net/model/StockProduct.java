package net.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

//@Entity
public class StockProduct {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="stock_id")
	private Stock stock;
	
	@OneToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	
	public StockProduct() {
		// TODO Auto-generated constructor stub
	}


	public StockProduct(Stock stock, Product product) {
		super();
		this.stock = stock;
		this.product = product;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Stock getStock() {
		return stock;
	}


	public void setStock(Stock stock) {
		this.stock = stock;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	@Override
	public String toString() {
		return "\n StockProduct [id=" + id + ", stock=" + stock + ", product=" + product + "]";
	}


	
}
