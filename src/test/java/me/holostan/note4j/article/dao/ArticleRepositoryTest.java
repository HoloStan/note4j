package me.holostan.note4j.article.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

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
    @Rollback
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
    @Rollback
    public void getOne() throws Exception {

    }

    @Test
    @Rollback
    public void getAll() throws Exception {

    }


}
