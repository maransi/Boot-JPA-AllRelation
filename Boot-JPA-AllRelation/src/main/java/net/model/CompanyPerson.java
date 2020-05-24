package net.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CompanyPerson extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2130106796723626887L;

	private String EIN;
	
	@Temporal(TemporalType.DATE)
	private Calendar foundationDate;
	
	public CompanyPerson() {
		super();
	}

	public CompanyPerson(String name, String eIN, Calendar foundationDate) {
		super(name);
		EIN = eIN;
		this.foundationDate = foundationDate;
	}

	public String getEIN() {
		return EIN;
	}

	public void setEIN(String eIN) {
		EIN = eIN;
	}

	public Calendar getFoundationDate() {
		return foundationDate;
	}

	public void setFoundationDate(Calendar foundationDate) {
		this.foundationDate = foundationDate;
	}

	@Override
	public String toString() {
		DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		
		return "\n CompanyPerson [EIN=" + EIN + ", foundationDate=" + formatador.format(foundationDate.getTime()) + ", getId()=" + getId()
				+ ", getName()=" + getName() + "]";
	}

	
	
	

}
