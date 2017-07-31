package me.holostan.note4j.article.dao.repository;

import me.holostan.note4j.article.entity.Article;
import me.holostan.note4j.article.entity.type.ArticleType;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ghu on 7/19/2017.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {

    @Query(value = "SELECT * FROM t_article WHERE author = ?1 ORDER BY create_time DESC LIMIT 10 OFFSET ?2", nativeQuery = true)
    List<Article> findArticlesByAuthor(String author, int begin);

    @Query(value = "SELECT * FROM t_article ORDER BY create_time DESC LIMIT 10 OFFSET ?1", nativeQuery = true)
    List<Article> findArticlesByPage(Integer begin);

}
