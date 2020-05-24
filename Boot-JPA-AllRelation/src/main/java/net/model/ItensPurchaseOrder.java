package net.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ItensPurchaseOrder {

	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
//	@JoinColumn(name = "purchase_order_id", referencedColumnName = "id")	
	private PurchaseOrder purchaseOrder;
	
	@OneToOne
	private Product product;
	
	private Float quantity;
	
	
	public ItensPurchaseOrder() {
		// TODO Auto-generated constructor stub
	}


	public ItensPurchaseOrder(PurchaseOrder purchaseOrder, Product product, Float quantity) {
		super();
		this.purchaseOrder = purchaseOrder;
		this.product = product;
		this.quantity = quantity;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}


	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public Float getQuantity() {
		return quantity;
	}


	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "\n ItensPurchaseOrder [id=" + id +  ", product=" + product
				+ ", quantity=" + quantity + "]";
		
//		", purchaseOrder=" + purchaseOrder +		
	}

	
	
	
}
