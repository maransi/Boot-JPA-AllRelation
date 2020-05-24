package net.dao;

import org.springframework.stereotype.Repository;

import net.model.CompanyPerson;
import net.util.GenericDAO;

@Repository
public class CompanyPersonDAO extends GenericDAO<CompanyPerson, Long> {

	public CompanyPersonDAO() {
		// TODO Auto-generated constructor stub
		super( CompanyPerson.class);
	}

}
