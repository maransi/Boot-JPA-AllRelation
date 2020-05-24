package net;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import net.model.Article;
import net.repository.ArticleRepository;

@TestMethodOrder(OrderAnnotation.class) // Annotation para informar que a ordem da execução dos testes será pela
										// annotation @Order
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootJpaAllRelationApplication.class)
@Transactional
class ArticleRepositoryTests {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ArticleRepository articleRepository;

	@Test
	void contextLoads() {
	}

	@Test
	@Order(1) // Direciona a ordem da execução dos testes
	void testFindById() throws Exception {
		logger.info("\n Testing is Running findById()");

		try {
			Optional<Article> article = articleRepository.findById(1L);

			logger.info("\n ArticleRepository.findById() {}", article);

			assertTrue(article.isPresent());

			Article articleTitle = articleRepository.findByTitle("Spring Boot Getting Started").get(0);

			assertNotNull(articleTitle);

			logger.info("\n ArticleRepository.findById() {}", articleTitle);
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
			List<Article> articles = (List<Article>) articleRepository.findAll(Sort.by(Sort.Direction.ASC, "title"));

//			articleRepository.findAll(Sort.by("title","category" ));
//			articleRepository.findAll(Sort.by(Sort.Direction.ASC, "title"));
//			articleRepository.findAll(Sort.by(Sort.Direction.DESC, "title"));
			
			assertNotEquals(0, articles.size());

			logger.info("\n ArticleRepository.findAll() {}", articles);
			
			PageRequest pageRequest = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "title") );
			
			Page<Article> firstPage = articleRepository.findAll(pageRequest);
			
			assertFalse(firstPage.isEmpty());

			logger.info("\n ArticleRepository.findAll() {}", firstPage.getContent());
			
			List<Article> articleByName = (List<Article>) articleRepository.findByTitle("Lambda Expressions Java 8 Example");
			
			assertFalse(articleByName.isEmpty());

			logger.info("\n ArticleRepository.findAll() {}", articleByName);
			
			List<Article> articleByCategory = (List<Article>) articleRepository.findByCategoryOrderByTitleDesc("Spring Boot");
			
			assertFalse(articleByCategory.isEmpty());

			logger.info("\n ArticleRepository.findAll() {}", articleByCategory);
			
			List<Article> articleCategoryWithJava = articleRepository.articleCategoryWithJava("Java");

			assertFalse( articleCategoryWithJava.isEmpty() );

			logger.info("\n ArticleRepository.findAll() {}", articleCategoryWithJava);

			List<Article> articleTitleWithSpring = articleRepository.articleTitleWithSpring();

			assertFalse( articleTitleWithSpring.isEmpty() );

			logger.info("\n ArticleRepository.findAll() {}", articleTitleWithSpring);
			
			List<Article> articleQueryByName = articleRepository.articleQueryByName();

			assertFalse( articleQueryByName.isEmpty() );

			logger.info("\n ArticleRepository.findAll() {}", articleQueryByName);
		} catch (Exception e) {
			e.printStackTrace();

			throw new Exception(e.getMessage()); // TODO: handle exception
		}
	}

	@Test
	@Rollback(true)
	@Order(3)
	public void testUpdateArticle() throws Exception {
		logger.info("\n Testing is Running update()");

		try {
			Article article = articleRepository.findById(1L).get();

			article.setTitle("Java Concurrency Updated");

			article = articleRepository.save(article);

			assertEquals("Java Concurrency Updated", article.getTitle());

			logger.info("\n ArticleRepository.save() {}", article);
		} catch (Exception e) {
			e.printStackTrace();

			throw new Exception(e.getMessage()); // TODO: handle exception
		}
	}

	@Test
	@Rollback(true)
	@Order(4)
	public void testInsertArticle() throws Exception {
		logger.info("\n Testing is Running insert()");

		try {
			Article article = new Article("Teste", "Teste");

			article = articleRepository.save(article);

			assertNotEquals(0, article.getId());

			logger.info("\n CustomerDAO.insert() {}", article);
		} catch (Exception e) {
			e.printStackTrace();

			throw new Exception(e.getMessage()); // TODO: handle exception
		}
	}

	@Test
	@Rollback(true)
	@Order(5)
	public void testDeleteArticle() throws Exception {
		logger.info("\n Testing is Running delete()");

		try {
			articleRepository.deleteById(1L);

			Optional<Article> article2 = articleRepository.findById(1L);

			assertFalse(article2.isPresent());

			logger.info("\n ArticleRepository.delete() Order by title {}",
					articleRepository.findAll(Sort.by("title")));
			
			articleRepository.deleteByCategory("Spring Boot");
			
			List<Article> articleByCategory = articleRepository.findByCategoryOrderByTitleDesc("Spring Boot");
			
			assertTrue(articleByCategory.isEmpty());
			
		} catch (Exception e) {
			e.printStackTrace();

			throw new Exception(e.getMessage()); // TODO: handle exception
		}
	}
}
