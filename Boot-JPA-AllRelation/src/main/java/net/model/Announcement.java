package net.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Announcement")
@DiscriminatorValue("A")
public class Announcement extends Topic {
 
    @Temporal(TemporalType.TIMESTAMP)
    private Date validUntil;

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	public Announcement(String title, String owner, Date createdOn, Date validUntil) {
		super(title, owner, createdOn);
		this.validUntil = validUntil;
	}

	@Override
	public String toString() {
		return "\n Announcement [validUntil=" + validUntil + ", getId()=" + getId() + ", getTitle()=" + getTitle()
				+ ", getOwner()=" + getOwner() + ", getCreatedOn()=" + getCreatedOn() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
 
}
