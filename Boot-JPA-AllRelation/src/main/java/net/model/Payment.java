package net.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Payment {

	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private PurchaseOrder purchaseOrder;
	
	private Float totalAmount;
	
	@ManyToOne
	private FormPayment formPayment;
	
	@Temporal(TemporalType.DATE)
	private Calendar invoiceDueDate;
	
	@Temporal(TemporalType.DATE)
	private Calendar paymentDate;
	
	public Payment() {
		// TODO Auto-generated constructor stub
	}


	public Payment(PurchaseOrder purchaseOrder, Float totalAmount, FormPayment formPayment, Calendar invoiceDueDate,
			Calendar paymentDate) {
		super();
		this.purchaseOrder = purchaseOrder;
		this.totalAmount = totalAmount;
		this.formPayment = formPayment;
		this.invoiceDueDate = invoiceDueDate;
		this.paymentDate = paymentDate;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}


	public FormPayment getFormPayment() {
		return formPayment;
	}


	public void setFormPayment(FormPayment formPayment) {
		this.formPayment = formPayment;
	}


	public Calendar getInvoiceDueDate() {
		return invoiceDueDate;
	}

	public void setInvoiceDueDate(Calendar invoiceDueDate) {
		this.invoiceDueDate = invoiceDueDate;
	}

	public Calendar getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Calendar paymentDate) {
		this.paymentDate = paymentDate;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}


	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}


	@Override
	public String toString() {
		return "Payment [id=" + id + ", purchaseOrder=" + purchaseOrder + ", totalAmount=" + totalAmount
				+ ", formPayment=" + formPayment + ", invoiceDueDate=" + invoiceDueDate + ", paymentDate="
				+ paymentDate + "]";
	}


}
