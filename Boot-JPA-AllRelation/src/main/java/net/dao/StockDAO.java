package net.dao;

import org.springframework.stereotype.Repository;

import net.model.Stock;
import net.util.GenericDAO;

@Repository
public class StockDAO extends GenericDAO<Stock, Long> {

	public StockDAO() {
		// TODO Auto-generated constructor stub
		super( Stock.class);
	}

}
