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
import net.dao.StockDAO;
import net.dao.SupplierDAO;
import net.dao.UnitDAO;
import net.model.Product;
import net.model.Stock;
import net.model.Supplier;
import net.model.Unit;

@TestMethodOrder(OrderAnnotation.class)		// Annotation para informar que a ordem da execução dos testes será pela annotation @Order
@RunWith(SpringRunner.class)
@SpringBootTest(classes=BootJpaAllRelationApplication.class)
@Transactional
class StockDAOTests {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StockDAO stockDAO;
	
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
		
		
		Stock stock = null;
		
		try {
			stock = stockDAO.findById(1L);
			
			logger.info("\n StockDAO.findById() {}", stock );
			
			assertEquals("CENTRAL", stock.getName());
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
			List<Stock> stocks = stockDAO.findAll();
			
			assertNotEquals(0, stocks.size());
			
			logger.info("\n StockDAO.findAll() {}", stocks );
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(3)
	public void testUpdateStock() throws Exception {
		logger.info("\n Testing is Running update()");
		
		try {
			Stock stock = stockDAO.findById(1L);
			
			stock.setName("CENTRAL Updated");
			
			stock = stockDAO.update(stock);
			
			assertEquals("CENTRAL Updated", stock.getName());		

			logger.info("\n StockDAO.update() {}", stock );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(4)
	public void testInsertStock() throws Exception {
		logger.info("\n Testing is Running insert()");
		
		try {
			Stock stock = new Stock("TESTE Inserted", "RUA DE TESTE", "CIDADE TESTE", "000000", "00000");
			
			stock = stockDAO.insert(stock);
			
			assertNotEquals(0, stock.getId());		

			logger.info("\n StockDAO.insert() {}", stock );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}

	@Test
	@Rollback(true)
	@Order(5)
	public void testDeleteStock() throws Exception {
		logger.info("\n Testing is Running delete()");
		
		try {
			Stock stock = stockDAO.insert(new Stock("TESTE Inserted", "RUA DE TESTE", "CIDADE TESTE", "000000", "00000"));
			
			stockDAO.delete(stock.getId());
			
			Stock stock2 = stockDAO.findById(stock.getId());
			
			assertNull( stock2 );		
			
			logger.info("\n StockDAO.delete() {}", stockDAO.findAll() );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(6)
	public void testInsertProductInStock() throws Exception{
		logger.info("\n Testing is Running testInsertProductInStock()");
		
		Product product = productDAO.findById(1L);
		
		Unit unit = unitDAO.findById(1L);
		Supplier supplier = supplierDAO.findById(1001L);
		
		Product productNew = new Product("TESTE InsertedProductInStock","TESTE", unit, 10.50f, supplier );
				
		
		Stock stock = new Stock("TESTE InsertedProductInStock", "RUA DE TESTE", "CIDADE TESTE", "000000", "00000");
		
		stock.getStockProduct().add( product );
		stock.getStockProduct().add(productNew);
		
		stockDAO.insert(stock);
		
		assertNotEquals(0, stock.getId());		
		
		logger.info("\n StockDAO.insertProductInStock() {}", stock);
		logger.info("\n productDAO.findAll() {}", productDAO.findAll());
		
	}
	
	@Test
	@Rollback(true)
	@Order(7)
	public void testRemoveProductStock() throws Exception {
		logger.info("\n Testing is Running testRemoveProductStock()");
		
		Stock stock = stockDAO.findById(1L);
		
		stock.getStockProduct().remove(0);
		
		stockDAO.update(stock);
		
		logger.info("\n StockDAO.removeProductStock() {}", stock );	
	}
}
