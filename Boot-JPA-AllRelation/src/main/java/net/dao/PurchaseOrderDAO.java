package net.dao;

import org.springframework.stereotype.Repository;

import net.model.PurchaseOrder;
import net.util.GenericDAO;

@Repository
public class PurchaseOrderDAO extends GenericDAO<PurchaseOrder, Long> {

	public PurchaseOrderDAO() {
		// TODO Auto-generated constructor stub
		super( PurchaseOrder.class);
	}

}
