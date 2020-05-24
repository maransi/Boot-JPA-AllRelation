package net.model;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class RetiredEmployee extends Employee {

	private static final long serialVersionUID = -4573850784352158034L;
	
	private BigDecimal pension;  
	 
	public RetiredEmployee() {
		// TODO Auto-generated constructor stub
	}

	public RetiredEmployee(BigDecimal pension) {
		super();
		this.pension = pension;
	}

	public BigDecimal getPension() {
		return pension;
	}

	public void setPension(BigDecimal pension) {
		this.pension = pension;
	}

	@Override
	public String toString() {
		return "\n RetiredEmployee [pension=" + pension + "]";
	}

	
}
