package me.holostan.note4j.article.service;

import me.holostan.note4j.article.entity.Article;
import me.holostan.note4j.article.entity.Tag;

import java.util.List;

/**
 * Created by ghu on 7/26/2017.
 */
public interface ArticleService {

    void addArticle(Article article);

    void deleteArticleById(String articleId);

    void updateArticleById(Article article);

    Article findArticleById(String articleId);

    List<Article> findArticleByPage(Integer page);

    List<Article> findArticleByTag(Tag Tag);

}
