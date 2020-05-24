package net.model;

import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "topic")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    discriminatorType = DiscriminatorType.STRING,
    name = "topic_type",
    columnDefinition = "VARCHAR(1)"
)
public class Topic {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String title;
 
    private String owner;
 
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn = new Date();
 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}


	public Topic(String title, String owner, Date createdOn) {
		super();
		this.title = title;
		this.owner = owner;
		this.createdOn = createdOn;
	}

	public Topic() {
		super();
	}
	
	@Override
	public String toString() {
		return "\n Topic [id=" + id + ", title=" + title + ", owner=" + owner + ", createdOn=" + createdOn + "]";
	}
 
}