package net.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

@Entity
//@MappedSuperclass
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person implements Serializable {

	private static final long serialVersionUID = 8475640398662281671L;

	@Id
	// Devido ao tipo de herança InheritanceType.TABLE_PER_CLASS fui obrigado colocar com GenerationType.TABLE.
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pessoa_sequence")
	@SequenceGenerator(name="pessoa_sequence", sequenceName="pes_seq",initialValue = 1, allocationSize = 1)
//	@GeneratedValue( strategy=GenerationType.TABLE)
	private Long id;
	
	private String name;
	
	public Person() {
		// TODO Auto-generated constructor stub
	}

	public Person(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "\n Person [id=" + id + ", name=" + name + "]";
	}

	

}
