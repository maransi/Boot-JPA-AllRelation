package net.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CompanyId implements Serializable{

	private Long id;
	
	private String EIN;
	
	public CompanyId() {
		// TODO Auto-generated constructor stub
	}

	public CompanyId(Long id, String eIN) {
		super();
		this.id = id;
		EIN = eIN;
	}

	public Long getId() {
		return id;
	}

	public String getEIN() {
		return EIN;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((EIN == null) ? 0 : EIN.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyId other = (CompanyId) obj;
		if (EIN == null) {
			if (other.EIN != null)
				return false;
		} else if (!EIN.equals(other.EIN))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
