package net;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import net.dao.CustomerDAO;
import net.dao.ProductDAO;
import net.dao.PurchaseOrderDAO;
import net.model.Customer;
import net.model.ItensPurchaseOrder;
import net.model.Product;
import net.model.PurchaseOrder;

@TestMethodOrder(OrderAnnotation.class)		// Annotation para informar que a ordem da execução dos testes será pela annotation @Order
@RunWith(SpringRunner.class)
@SpringBootTest(classes=BootJpaAllRelationApplication.class)
@Transactional
class PurchaseOrderDAOTests {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PurchaseOrderDAO purchaseOrderDAO;

	@Autowired
	CustomerDAO customerDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	@Order(1)		// Direciona a ordem da execução dos testes
	void testFindById() throws Exception {
		logger.info("\n Testing is Running findById()");
		
		
		PurchaseOrder purchaseOrder = null;
		
		try {
			purchaseOrder = purchaseOrderDAO.findById(1L);
			
			logger.info("\n PurchaseOrderDAO.findById() {}", purchaseOrder );
			
			assertEquals(10.50F, purchaseOrder.getTotalAmount());
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
			List<PurchaseOrder> purchaseOrders = purchaseOrderDAO.findAll();
			
			assertNotEquals(0, purchaseOrders.size());
			
			logger.info("\n PurchaseOrderDAO.findAll() {}", purchaseOrders );
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(3)
	public void testUpdatePurchaseOrder() throws Exception {
		logger.info("\n Testing is Running update()");
		
		try {
			PurchaseOrder purchaseOrder = purchaseOrderDAO.findById(1L);
			
			purchaseOrder.setTotalAmount(9.99F);
			
			purchaseOrder = purchaseOrderDAO.update(purchaseOrder);
			
			assertEquals(9.99F,  purchaseOrder.getTotalAmount());		
			
			logger.info("\n PurchaseOrderDAO.update() {}", purchaseOrder );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(4)
	public void testInsertPurchaseOrder() throws Exception {
		logger.info("\n Testing is Running insert()");
		
		try {
			Customer customer = customerDAO.findById(1L);
			
			PurchaseOrder purchaseOrder = new PurchaseOrder("0000", Calendar.getInstance(), customer, 0.01F);
			
			purchaseOrder = purchaseOrderDAO.insert(purchaseOrder);
			
			assertNotEquals(0, purchaseOrder.getId());		

			logger.info("\n PurchaseOrderDAO.insert() {}", purchaseOrder );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}

	@Test
	@Rollback(true)
	@Order(5)
	public void testDeletePurchaseOrder() throws Exception {
		logger.info("\n Testing is Running delete()");
		
		try {
			Customer customer = customerDAO.findById(1L);

			PurchaseOrder purchaseOrder = purchaseOrderDAO.insert(new PurchaseOrder("0000", Calendar.getInstance(), customer, 0.01F));
			
			purchaseOrderDAO.delete(purchaseOrder.getId());
			
			PurchaseOrder purchaseOrder2 = purchaseOrderDAO.findById(purchaseOrder.getId());
			
			assertNull( purchaseOrder2 );		
			
			logger.info("\n PurchaseOrderDAO.delete() {}", purchaseOrderDAO.findAll() );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(6)
	public void testInsertItensInPurchaseOrder() throws Exception{
		logger.info("\n Testing is Running testInsertProductInPurchaseOrder()");

		Customer customer = customerDAO.findById(1L);
		
		Product product = productDAO.findById(1L);
		
		
		
		PurchaseOrder purchaseOrder = new PurchaseOrder("0000", Calendar.getInstance(), customer, 10.01F);
		
		ItensPurchaseOrder item = new ItensPurchaseOrder(purchaseOrder, product, 10F );
		
		purchaseOrder.getItensPurchaseOrder().add(item);
		
		purchaseOrderDAO.insert(purchaseOrder);
		
		assertNotEquals(0, purchaseOrder.getId());		
		
		logger.info("\n PurchaseOrderDAO.insertProductInPurchaseOrder() {}", purchaseOrder);
		
	}
	
	@Test
	@Rollback(true)
	@Order(7)
	public void testRemoveItensPurchaseOrder() throws Exception {
		logger.info("\n Testing is Running testRemoveProductPurchaseOrder()");
		
		PurchaseOrder purchaseOrder = purchaseOrderDAO.findById(1L);
		
		purchaseOrder.getItensPurchaseOrder().remove(0);
		
		purchaseOrderDAO.update(purchaseOrder);
		
		purchaseOrderDAO.getEntityManager().refresh(purchaseOrder);
		
		logger.info("\n PurchaseOrderDAO.removeProductPurchaseOrder() {}", purchaseOrder );	
		logger.info("\n \n \n Itens {}", purchaseOrder.getItensPurchaseOrder() );	
	}
}
