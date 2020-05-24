package net.dao;

import org.springframework.stereotype.Repository;

import net.model.ActiveEmployee;
import net.util.GenericDAO;

@Repository
public class ActiveEmployeeDAO extends GenericDAO<ActiveEmployee, Long> {

	public ActiveEmployeeDAO() {
		// TODO Auto-generated constructor stub
		super( ActiveEmployee.class);
	}

}
