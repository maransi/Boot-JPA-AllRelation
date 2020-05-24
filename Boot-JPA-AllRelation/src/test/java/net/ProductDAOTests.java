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

import net.dao.ProductDAO;
import net.dao.SupplierDAO;
import net.dao.UnitDAO;
import net.model.Product;
import net.model.Supplier;
import net.model.Unit;

@TestMethodOrder(OrderAnnotation.class)		// Annotation para informar que a ordem da execução dos testes será pela annotation @Order
@RunWith(SpringRunner.class)
@SpringBootTest(classes=BootJpaAllRelationApplication.class)
@Transactional
class ProductDAOTests {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	UnitDAO unitDAO;
	
	@Autowired
	SupplierDAO supplierDAO;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	@Order(1)		// Direciona a ordem da execução dos testes
	void testFindById() throws Exception {
		logger.info("\n Testing is Running findById()");
		
		
		Product product = null;
		
		try {
			product = productDAO.findById(1L);
			
			logger.info("\n ProductDAO.findById() {}", product );
			
			assertEquals("CHAPA DE ACO GALVANIZADA DE 3\"", product.getDescription());
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
			List<Product> products = productDAO.findAll();
			
			assertNotEquals(0, products.size());
			
			logger.info("\n ProductDAO.findAll() {}", products );
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(3)
	public void testUpdateProduct() throws Exception {
		logger.info("\n Testing is Running update()");
		
		try {
			Product product = productDAO.findById(1L);
			
			product.setDescription("CHAPA DE ACO GALVANIZADA DE 3 Updated");
			
			product = productDAO.update(product);
			
			assertEquals("CHAPA DE ACO GALVANIZADA DE 3 Updated", product.getDescription());		

			logger.info("\n ProductDAO.update() {}", product );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(4)
	public void testInsertProduct() throws Exception {
		logger.info("\n Testing is Running insert()");
		
		try {
			Unit unit = unitDAO.findById(1L);
			Supplier supplier = supplierDAO.findById(1001L);
			
			Product product = new Product("TESTE Inserted","TESTE", unit, 10.50f, supplier );
			
			product = productDAO.insert(product);
			
			assertNotEquals(0, product.getId());		

			logger.info("\n ProductDAO.insert() {}", product );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}

	@Test
	@Rollback(true)
	@Order(5)
	public void testDeleteProduct() throws Exception {
		logger.info("\n Testing is Running delete()");
		
		try {
			Unit unit = unitDAO.findById(2L);
			Supplier supplier = supplierDAO.findById(1002L);

			Product product = productDAO.insert(new Product("TESTE deleted","TESTE", unit, 10.50f, supplier ));
			
			productDAO.delete(product.getId());
			
			Product product2 = productDAO.findById(product.getId());
			
			assertNull( product2 );		
			
			logger.info("\n ProductDAO.delete() {}", productDAO.findAll() );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
}
