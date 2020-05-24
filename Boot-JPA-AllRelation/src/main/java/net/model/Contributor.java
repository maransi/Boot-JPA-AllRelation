package net.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Contributor extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2315603624314038422L;

	private String socialSecurityNumber;
	
	@Temporal(TemporalType.DATE)
	private Calendar birthdayDate;

	public Contributor() {
		super();
	}

	public Contributor(String name, String socialSecurityNumber, Calendar birthdayDate) {
		super(name);
		this.socialSecurityNumber = socialSecurityNumber;
		this.birthdayDate = birthdayDate;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public Calendar getBirthdayDate() {
		return birthdayDate;
	}

	public void setBirthdayDate(Calendar birthdayDate) {
		this.birthdayDate = birthdayDate;
	}

	@Override
	public String toString() {
		DateFormat	formatador = new SimpleDateFormat("dd/MM/yyyy");
		
		return "\n Contributor [socialSecurityNumber=" + socialSecurityNumber + ", birthdayDate=" + formatador.format(birthdayDate.getTime())
				+ ", getId()=" + getId() + ", getName()=" + getName() + "]";
	}

}
