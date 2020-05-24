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

import net.dao.UnitDAO;
import net.model.Unit;

@TestMethodOrder(OrderAnnotation.class)		// Annotation para informar que a ordem da execução dos testes será pela annotation @Order
@RunWith(SpringRunner.class)
@SpringBootTest(classes=BootJpaAllRelationApplication.class)
@Transactional
class UnitDAOTests {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UnitDAO unitDAO;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	@Order(1)		// Direciona a ordem da execução dos testes
	void testFindById() throws Exception {
		logger.info("\n Testing is Running findById()");
		
		
		Unit unit = null;
		
		try {
			unit = unitDAO.findById(1L);
			
			logger.info("\n UnitDAO.findById() {}", unit );
			
			assertEquals("METRO", unit.getName());
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
			List<Unit> units = unitDAO.findAll();
			
			assertNotEquals(0, units.size());
			
			logger.info("\n UnitDAO.findAll() {}", units );
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(3)
	public void testUpdateUnit() throws Exception {
		logger.info("\n Testing is Running update()");
		
		try {
			Unit unit = unitDAO.findById(1L);
			
			unit.setName("METRO Updated");
			
			unit = unitDAO.update(unit);
			
			assertEquals("METRO Updated", unit.getName());		

			logger.info("\n UnitDAO.update() {}", unit );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(4)
	public void testInsertUnit() throws Exception {
		logger.info("\n Testing is Running insert()");
		
		try {
			Unit unit = new Unit("TESTE Inserted");
			
			unit = unitDAO.insert(unit);
			
			assertNotEquals(0, unit.getId());		

			logger.info("\n UnitDAO.insert() {}", unit );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}

	@Test
	@Rollback(true)
	@Order(5)
	public void testDeleteUnit() throws Exception {
		logger.info("\n Testing is Running delete()");
		
		try {
			Unit unit = unitDAO.insert(new Unit("TESTE Deleted"));
			
			unitDAO.delete(unit.getId());
			
			Unit unit2 = unitDAO.findById(unit.getId());
			
			assertNull( unit2 );		
			
			logger.info("\n UnitDAO.delete() {}", unitDAO.findAll() );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
}
