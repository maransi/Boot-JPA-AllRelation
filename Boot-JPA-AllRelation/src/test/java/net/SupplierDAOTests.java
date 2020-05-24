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

import net.dao.SupplierDAO;
import net.model.Supplier;

@TestMethodOrder(OrderAnnotation.class)		// Annotation para informar que a ordem da execução dos testes será pela annotation @Order
@RunWith(SpringRunner.class)
@SpringBootTest(classes=BootJpaAllRelationApplication.class)
@Transactional
class SupplierDAOTests {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SupplierDAO supplierDAO;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	@Order(1)		// Direciona a ordem da execução dos testes
	void testFindById() throws Exception {
		logger.info("\n Testing is Running findById()");
		
		
		Supplier supplier = null;
		
		try {
			supplier = supplierDAO.findById(1001L);
			
			logger.info("\n SupplierDAO.findById() {}", supplier );
			
			assertEquals("ALCOA", supplier.getName());
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
			List<Supplier> suppliers = supplierDAO.findAll();
			
			assertNotEquals(0, suppliers.size());
			
			logger.info("\n SupplierDAO.findAll() {}", suppliers );
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(3)
	public void testUpdateSupplier() throws Exception {
		logger.info("\n Testing is Running update()");
		
		try {
			Supplier supplier = supplierDAO.findById(1001L);
			
			supplier.setName("ALCOA Updated");
			
			supplier = supplierDAO.update(supplier);
			
			assertEquals("ALCOA Updated", supplier.getName());		

			logger.info("\n SupplierDAO.update() {}", supplier );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(4)
	public void testInsertSupplier() throws Exception {
		logger.info("\n Testing is Running insert()");
		
		try {
			Supplier supplier = new Supplier("TESTE Inserted");
			
			supplier = supplierDAO.insert(supplier);
			
			assertNotEquals(0, supplier.getId());		

			logger.info("\n SupplierDAO.insert() {}", supplier );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}

	@Test
	@Rollback(true)
	@Order(5)
	public void testDeleteSupplier() throws Exception {
		logger.info("\n Testing is Running delete()");
		
		try {
			Supplier supplier = supplierDAO.insert(new Supplier("TESTE Deleted"));
			
			supplierDAO.delete(supplier.getId());
			
			Supplier supplier2 = supplierDAO.findById(supplier.getId());
			
			assertNull( supplier2 );		
			
			logger.info("\n SupplierDAO.delete() {}", supplierDAO.findAll() );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
}
