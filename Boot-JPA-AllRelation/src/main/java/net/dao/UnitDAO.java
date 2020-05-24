package net.dao;

import org.springframework.stereotype.Repository;

import net.model.Unit;
import net.util.GenericDAO;

@Repository
public class UnitDAO extends GenericDAO<Unit, Long> {

	public UnitDAO() {
		// TODO Auto-generated constructor stub
		
		super( Unit.class );
	}

}
