package net;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

import net.dao.CustomerDAO;
import net.dao.SupplierDAO;
import net.dao.UnitDAO;
import net.model.Customer;
import net.model.Supplier;
import net.model.Unit;

@TestMethodOrder(OrderAnnotation.class)		// Annotation para informar que a ordem da execução dos testes será pela annotation @Order
@RunWith(SpringRunner.class)
@SpringBootTest(classes=BootJpaAllRelationApplication.class)
@Transactional
class CustomerDAOTests {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CustomerDAO customerDAO;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	@Order(1)		// Direciona a ordem da execução dos testes
	void testFindById() throws Exception {
		logger.info("\n Testing is Running findById()");
		
		
		Customer customer = null;
		Customer customer2 = null;
		
		try {
			customer = customerDAO.findById(1L);
			
			logger.info("\n CustomerDAO.findById() {}", customer );
			
			customer2 = customerDAO.findById(2L);
			
			logger.info("\n CustomerDAO.findById() {}", customer2 );

			assertEquals("ANTUNES ALVES SOUZA", customer.getName());
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
		
		
		
	}
	
	@Test
	@Order(2)		// Direciona a ordem da execução dos testes
	void testFindAll() throws Exception {
		logger.info("\n Testing is Running findAll()");
		
		try {
			List<Customer> customers = customerDAO.findAll();
			
			assertNotEquals(0, customers.size());
			
			logger.info("\n CustomerDAO.findAll() {}", customers );
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(3)
	public void testUpdateCustomer() throws Exception {
		logger.info("\n Testing is Running update()");
		
		try {
			Customer customer = customerDAO.findById(1L);
			
			customer.setName("ANTUNES ALVES SOUZA Updated");
			
			customer = customerDAO.update(customer);
			
			assertEquals("ANTUNES ALVES SOUZA Updated", customer.getName());		

			logger.info("\n CustomerDAO.update() {}", customer );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(4)
	public void testInsertCustomer() throws Exception {
		logger.info("\n Testing is Running insert()");
		
		try {
			Customer customer = new Customer("TESTE Inserted","TESTE Address", "TESTE City", "000000","0000000");
			
			customer = customerDAO.insert(customer);
			
			assertNotEquals(0, customer.getId());		

			logger.info("\n CustomerDAO.insert() {}", customer );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}

	@Test
	@Rollback(true)
	@Order(5)
	public void testDeleteCustomer() throws Exception {
		logger.info("\n Testing is Running delete()");
		
		try {
			Customer customer = customerDAO.insert(new Customer("TESTE Inserted","TESTE Address", "TESTE City", "000000","0000000"));
			
			customerDAO.delete(customer.getId());
			
			Customer customer2 = customerDAO.findById(customer.getId());
			
			assertNull( customer2 );		
			
			logger.info("\n CustomerDAO.delete() {}", customerDAO.findAll() );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
}
