package net.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FormPayment {

	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	
	public FormPayment() {
		// TODO Auto-generated constructor stub
	}

	public FormPayment(String description) {
		super();
		this.description = description;
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

	@Override
	public String toString() {
		return "FormPayment [id=" + id + ", description=" + description + "]";
	}

	
}
