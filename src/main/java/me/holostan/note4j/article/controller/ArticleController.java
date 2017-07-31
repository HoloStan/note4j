package me.holostan.note4j.article.controller;

import me.holostan.note4j.article.entity.Article;
import me.holostan.note4j.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by ghu on 7/26/2017.
 */
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles/{page}")
    public List<Article> articles(@PathVariable Integer page, Principal principal) {
        return articleService.findArticleByPage(page);
    }

    @GetMapping("/article/{articleId}")
    public Article findArticle(@PathVariable String articleId) {
        return articleService.findArticleById(articleId);
    }

    @PostMapping(value = "/article")
    public void addArticle(@RequestBody Article article, Principal principal) {
        articleService.addArticle(article);
    }

    @PutMapping(value = "/article/{articleId}")
    public void modArticle(@PathVariable String articleId, Article article, Principal principal) {
        article.setUuid(articleId);
        articleService.updateArticleById(article);
    }

    @DeleteMapping(value = "/article/{articleId}")
    public void delArticle(@PathVariable String articleId, Principal principal) {
        articleService.deleteArticleById(articleId);
    }

}
