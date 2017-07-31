package me.holostan.note4j.article.dao;

import me.holostan.note4j.article.dao.repository.ArticleRepository;
import me.holostan.note4j.article.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by ghu on 7/19/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleRepositoryTest {
    private Logger logger = LoggerFactory.getLogger(ArticleRepositoryTest.class);

    @Autowired
    private ArticleRepository articleRepository;

    @Test
//    @Rollback
    public void addOne() throws Exception {
    }

    @Test
    @Rollback
    public void delOne() throws Exception {

    }

    @Test
    @Rollback
    public void modOne() throws Exception {

    }


    @Test
    public void findOne() throws Exception {
        Article article = articleRepository.findOne("e6fe54c8-583b-4d10-a451-e07087174939");
        logger.info(article.toString());
    }

    @Test
    public void findAll() throws Exception {
        List<Article> articles =  articleRepository.findArticlesByAuthor("ghu", 0);
        logger.info(articles.toString());
    }


}
