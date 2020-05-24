package net.dao;

import org.springframework.stereotype.Repository;

import net.model.Product;
import net.util.GenericDAO;

@Repository
public class ProductDAO extends GenericDAO<Product, Long> {

	public ProductDAO() {
		// TODO Auto-generated constructor stub
		super( Product.class);
	}

}
