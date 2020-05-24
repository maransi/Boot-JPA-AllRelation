package net.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import net.model.Article;

@RepositoryRestResource( path="articles" )
public interface ArticleRepository extends JpaRepository<Article, Long>  {
    List<Article> findByTitle(String title);
    List<Article> findDistinctByCategory(String category);
    List<Article> findByTitleAndCategory(String title, String category);
    List<Article> findByCategoryOrderByTitleDesc(String category);
    List<Article> deleteByCategory(String name);
    
    @Query("Select a From Article a where category like 'Java%'")
    List<Article> articleCategoryWithJava(String category);
    
    @Query(value="SELECT * FROM article WHERE title like '%Spring%'", nativeQuery = true )
    List<Article> articleTitleWithSpring();
    
    @Query(name="findAllArticle")
    List<Article> articleQueryByName();
    
    // Verificar na aula 119 aos 7 min mais opções de utilização com annotations queries
} 