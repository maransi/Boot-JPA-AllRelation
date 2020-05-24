package net.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
public class PurchaseOrder {

	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String orderNumber;
	
	@Temporal(TemporalType.DATE)
	private Calendar orderDate;
	
	
	@ManyToOne
	private Customer customer;
	
	private Float totalAmount;
	
	@OneToMany(mappedBy = "purchaseOrder",
				orphanRemoval = true,
				cascade = { CascadeType.PERSIST, CascadeType.REMOVE} )
	private List<ItensPurchaseOrder> itensPurchaseOrder = new ArrayList<>();
	
	public PurchaseOrder() {
		// TODO Auto-generated constructor stub
	}

	public PurchaseOrder(String orderNumber, Calendar orderDate, Customer customer, Float totalAmount) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.customer = customer;
		this.totalAmount = totalAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Calendar getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Calendar orderDate) {
		this.orderDate = orderDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<ItensPurchaseOrder> getItensPurchaseOrder() {
		return itensPurchaseOrder;
	}

	public void setItensPurchaseOrder(List<ItensPurchaseOrder> itensPurchaseOrder) {
		this.itensPurchaseOrder = itensPurchaseOrder;
	}

	@Override
	public String toString() {
		SimpleDateFormat yourDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		return "PurchaseOrder [id=" + id + ", orderNumber=" + orderNumber + ", orderDate=" + ", customer="
				+ customer + ", totalAmount=" + totalAmount + ", " + itensPurchaseOrder ;
	}

//	@Override
//	public String toString() {
//		
//		return "\n PurchaseOrder [id=" + id + ", orderNumber=" + orderNumber + ", orderDate=" + yourDateFormat.format(orderDate.getTime()) + ", customer="
//				+ customer + ", totalAmount=" + totalAmount + "], itensOrder=[ " + itensPurchaseOrder + "]";
//	}

	
}
