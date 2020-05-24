package net;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import net.dao.CompanyPersonDAO;
import net.dao.ContributorDAO;
import net.model.CompanyPerson;
import net.model.Contributor;

@TestMethodOrder(OrderAnnotation.class) // Annotation para informar que a ordem da execução dos testes será pela
										// annotation @Order
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootJpaAllRelationApplication.class)
@Transactional
class PersonDAOTests {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CompanyPersonDAO companyPersonDAO;

	@Autowired
	ContributorDAO contributorDAO;

	@Test
	void contextLoads() {
	}

	@Test
	@Order(1) // Direciona a ordem da execução dos testes
	void testFindById() throws Exception {
		logger.info("\n Testing is Running findById()");

		CompanyPerson companyPerson = null;

		Contributor contributor = null;

		try {
			companyPerson = companyPersonDAO.findById(1L);

			logger.info("\n CompanyPersonAO.findById() {}", companyPerson);

			assertEquals("SAMSUNG", companyPerson.getName());

			contributor = contributorDAO.findById(3L);

			logger.info("\n contributorDAO.findById() {}", contributor);

			assertEquals("JOSEPH", contributor.getName());
		} catch (Exception e) {
			e.printStackTrace();

			throw new Exception(e.getMessage()); // TODO: handle exception
		}

	}

	@Test
	@Order(2) // Direciona a ordem da execução dos testes
	void testFindAll() throws Exception {
		logger.info("\n Testing is Running findAll()");

		try {
			List<CompanyPerson> companyPersons = companyPersonDAO.findAll();

			assertNotEquals(0, companyPersons.size());

			logger.info("\n PersonDAO.findAll() {}", companyPersons);

			List<Contributor> contributors = contributorDAO.findAll();

			assertNotEquals(0, contributors.size());

			logger.info("\n PersonDAO.findAll() {}", contributors);
		} catch (Exception e) {
			e.printStackTrace();

			throw new Exception(e.getMessage()); // TODO: handle exception
		}
	}

	@Test
	@Rollback(true)
	@Order(3) 
	public void testUpdatePerson() throws Exception {
		logger.info("\n Testing is Running update()");

		try {
			CompanyPerson companyPerson = companyPersonDAO.findById(1L);

			companyPerson.setName("SAMSUNG Updated");

			companyPerson = companyPersonDAO.update(companyPerson);

			assertEquals("SAMSUNG Updated", companyPerson.getName());

			logger.info("\n personDAO.update() {}", companyPerson);
			
			Contributor contributor = contributorDAO.findById(3L);

			contributor.setName("JOSEPH Updated");

			contributor = contributorDAO.update(contributor);

			assertEquals("JOSEPH Updated", contributor.getName());

			logger.info("\n personDAO.update() {}", contributor);
		} catch (Exception e) {
			e.printStackTrace();

			throw new Exception(e.getMessage()); // TODO: handle exception
		}
	}

	@Test
	@Rollback(true)
	@Order(4)
	public void testInsertPerson() throws Exception {
		logger.info("\n Testing is Running insert()");

		try {
			Contributor contributor = new Contributor("TESTE", "11111", Calendar.getInstance());

			contributor = contributorDAO.insert(contributor);

			assertNotEquals(0, contributor.getId());

			logger.info("\n PersonDAO.insert() {}", contributor);

			CompanyPerson companyPerson = new CompanyPerson("TESTE", "11111", Calendar.getInstance());

			companyPerson = companyPersonDAO.insert(companyPerson);

			assertNotEquals(0, companyPerson.getId());

			logger.info("\n PersonDAO.insert() {}", companyPerson);
		} catch (Exception e) {
			e.printStackTrace();

			throw new Exception(e.getMessage()); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(5)
	public void testDeletePerson() throws Exception {
		logger.info("\n Testing is Running delete()");

		try {
			Contributor contributor = contributorDAO.insert(new Contributor("TESTE", "11111", Calendar.getInstance()));

			contributorDAO.delete(contributor.getId());

			Contributor contributor2 = contributorDAO.findById(contributor.getId());

			assertNull(contributor2);

			logger.info("\n personDAO.delete() {}", contributorDAO.findAll());

			CompanyPerson companyPerson = companyPersonDAO.insert(new CompanyPerson("TESTE", "11111", Calendar.getInstance()));

			companyPersonDAO.delete(companyPerson.getId());

			CompanyPerson companyPerson2 = companyPersonDAO.findById(companyPerson.getId());

			assertNull(companyPerson2);

			logger.info("\n personDAO.delete() {}", companyPersonDAO.findAll());
		} catch (Exception e) {
			e.printStackTrace();

			throw new Exception(e.getMessage()); // TODO: handle exception
		}
	}
	 
}
