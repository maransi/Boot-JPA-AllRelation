package net.dao;

import org.springframework.stereotype.Repository;

import net.model.Supplier;
import net.util.GenericDAO;

@Repository
public class SupplierDAO extends GenericDAO<Supplier, Long> {

	public SupplierDAO() {
		// TODO Auto-generated constructor stub
		super( Supplier.class);
	}

}
