package net;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;
import java.util.Date;
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

import net.dao.PostDAO;
import net.model.Post;

@TestMethodOrder(OrderAnnotation.class)		// Annotation para informar que a ordem da execução dos testes será pela annotation @Order
@RunWith(SpringRunner.class)
@SpringBootTest(classes=BootJpaAllRelationApplication.class)
@Transactional
class PostDAOTests {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PostDAO postDAO;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	@Order(1)		// Direciona a ordem da execução dos testes
	void testFindById() throws Exception {
		logger.info("\n Testing is Running findById()");
		
		
		Post post = null;
		
		try {
			post = postDAO.findById(1L);
			
			logger.info("\n PostDAO.findById() {}", post );
			
			assertEquals("JOSEPH", post.getOwner());
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
			List<Post> posts = postDAO.findAll();
			
			assertNotEquals(0, posts.size());
			
			logger.info("\n PostDAO.findAll() {}", posts );
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(3)
	public void testUpdatePost() throws Exception {
		logger.info("\n Testing is Running update()");
		
		try {
			Post post = postDAO.findById(1L);
			
			post.setOwner("JOSEPH Updated");
			
			post = postDAO.update(post);
			
			assertEquals("JOSEPH Updated", post.getOwner());		

			logger.info("\n PostDAO.update() {}", post );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
	
	@Test
	@Rollback(true)
	@Order(4)
	public void testInsertPost() throws Exception {
		logger.info("\n Testing is Running insert()");
		
		try {
			Post post = new Post( "TITLE", "OWNER", new Date(), "CONTENT") ;
			
			post = postDAO.insert(post);
			
			assertNotEquals(0, post.getId());		

			logger.info("\n PostDAO.insert() {}", post );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}

	@Test
	@Rollback(true)
	@Order(5)
	public void testDeletePost() throws Exception {
		logger.info("\n Testing is Running delete()");
		
		try {
			Post post = postDAO.insert(new Post( "TITLE", "OWNER", new Date(), "CONTENT"));
			
			postDAO.delete(post.getId());
			
			Post post2 = postDAO.findById(post.getId());
			
			assertNull( post2 );		
			
			logger.info("\n PostDAO.delete() {}", postDAO.findAll() );
		}catch( Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() ); // TODO: handle exception
		}
	}
}
