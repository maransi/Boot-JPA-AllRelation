package net;

import java.math.BigDecimal;
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

import net.dao.ActiveEmployeeDAO;
import net.model.ActiveEmployee;

@TestMethodOrder(OrderAnnotation.class)		// Annotation para informar que a ordem da execução dos testes será pela annotation @Order
@RunWith(SpringRunner.class)
@SpringBootTest(classes=BootJpaAllRelationApplication.class)
@Transactional
class ActiveEmployeeDAOTests {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ActiveEmployeeDAO activeEmployeeDAO;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	@Order(1)		// Direciona a ordem da execução dos testes
	void testFindById() throws Exception {
		logger.info("\n Testing is Running findById()");
		
		
		ActiveEmployee activeEmployee = null;
		
		try {
			activeEmployee = activeEmployeeDAO.findById(1L);
			
			logger.info("\n ActiveEmployeeDAO.findById() {}", activeEmployee );
			
			assertEquals("PETER", activeEmployee.getName());
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
			List<ActiveEmployee> activeEmployees = activeEmployeeDAO.findAll();
			
			assertNotEquals(0, activeEmployees.size());
			
			logger.info("\n ActiveEmployeeDAO.findAll() {}", activeEmployees );
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(3)
	public void testUpdateActiveEmployee() throws Exception {
		logger.info("\n Testing is Running update()");
		
		try {
			ActiveEmployee activeEmployee = activeEmployeeDAO.findById(1L);
			
			activeEmployee.setName("PETER Updated");
			
			activeEmployee = activeEmployeeDAO.update(activeEmployee);
			
			assertEquals("PETER Updated", activeEmployee.getName());		

			logger.info("\n ActiveEmployeeDAO.update() {}", activeEmployee );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(4)
	public void testInsertActiveEmployee() throws Exception {
		logger.info("\n Testing is Running insert()");
		
		try {
			ActiveEmployee activeEmployee = new ActiveEmployee("TESTE Inserted",new BigDecimal("10.00"),1);
			
			activeEmployee = activeEmployeeDAO.insert(activeEmployee);
			
			assertNotEquals(0, activeEmployee.getId());		

			logger.info("\n ActiveEmployeeDAO.insert() {}", activeEmployee );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}

	@Test
	@Rollback(true)
	@Order(5)
	public void testDeleteActiveEmployee() throws Exception {
		logger.info("\n Testing is Running delete()");
		
		try {
			ActiveEmployee activeEmployee = activeEmployeeDAO.insert(new ActiveEmployee("TESTE Deleted",new BigDecimal("10.00"),1));
			
			activeEmployeeDAO.delete(activeEmployee.getId());
			
			ActiveEmployee activeEmployee2 = activeEmployeeDAO.findById(activeEmployee.getId());
			
			assertNull( activeEmployee2 );		
			
			logger.info("\n ActiveEmployeeDAO.delete() {}", activeEmployeeDAO.findAll() );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
}
