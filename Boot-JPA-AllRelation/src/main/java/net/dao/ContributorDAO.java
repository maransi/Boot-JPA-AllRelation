package net.dao;

import org.springframework.stereotype.Repository;

import net.model.Contributor;
import net.util.GenericDAO;

@Repository
public class ContributorDAO extends GenericDAO<Contributor, Long> {

	public ContributorDAO() {
		// TODO Auto-generated constructor stub
		super( Contributor.class);
	}

}
