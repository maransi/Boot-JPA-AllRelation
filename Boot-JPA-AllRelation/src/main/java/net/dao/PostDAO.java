package net.dao;

import org.springframework.stereotype.Repository;

import net.model.Post;
import net.util.GenericDAO;

@Repository
public class PostDAO extends GenericDAO<Post, Long> {

	public PostDAO() {
		// TODO Auto-generated constructor stub
		super( Post.class);
	}

}
