package net.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("P")
public class Post extends Topic {
 
    private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Post(String title, String owner, Date createdOn, String content) {
		super(title, owner, createdOn);
		this.content = content;
	}

	
	public Post() {
		super();
	}

	@Override
	public String toString() {
		return "\n Post [content=" + content + ", getId()=" + getId() + ", getTitle()=" + getTitle() + ", getOwner()="
				+ getOwner() + ", getCreatedOn()=" + getCreatedOn() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	
	
 
}
