package me.holostan.note4j.article.rest;

import me.holostan.note4j.core.util.template.client.RestClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ghu on 7/26/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleRestTest {

    private static Logger logger = LoggerFactory.getLogger(ArticleRestTest.class);

    @Autowired
    public RestClient restClient;

    @Test
    public void findOne() throws Exception {
        String httpResponse = restClient.execute("http://localhost:8888/article/e6fe54c8-583b-4d10-a451-e07087174939", HttpMethod.GET);
        logger.info(httpResponse);
    }

}
