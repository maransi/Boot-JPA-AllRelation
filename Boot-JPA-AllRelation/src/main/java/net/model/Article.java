package net.model;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findAllArticle", query = "Select a from Article a")
@Cacheable
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String category;

	public Article() {
		// TODO Auto-generated constructor stub
	}

	public Article(String title, String category) {
		super();
		this.title = title;
		this.category = category;
	}

	@Override
	public String toString() {
		return "\n Article [id=" + id + ", title=" + title + ", category=" + category + "]";
	}

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
