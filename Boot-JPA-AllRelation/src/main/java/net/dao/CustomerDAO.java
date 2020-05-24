package net.dao;

import org.springframework.stereotype.Repository;

import net.model.Customer;
import net.util.GenericDAO;

@Repository
public class CustomerDAO extends GenericDAO<Customer, Long> {

	public CustomerDAO() {
		// TODO Auto-generated constructor stub
		super( Customer.class);
	}

}
