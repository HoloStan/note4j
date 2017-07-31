package me.holostan.note4j.article.service.impl;

import me.holostan.note4j.article.dao.repository.ArticleRepository;
import me.holostan.note4j.article.dao.repository.ArticleTagRepository;
import me.holostan.note4j.article.entity.Article;
import me.holostan.note4j.article.entity.ArticleTag;
import me.holostan.note4j.article.entity.Tag;
import me.holostan.note4j.article.entity.type.TagType;
import me.holostan.note4j.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ghu on 7/26/2017.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleTagRepository articleTagRepository;

    @Override
    @Transactional
    public void addArticle(Article article) {
        //todo:insert t_article -> insert t_article_tag
        Article articleType = articleRepository.save(article);
        List<TagType> tags = article.getTags();
        tags.stream().filter(tag -> tag != null && tag.getUuid() != null).forEach(tag -> {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(articleType.getUuid());
            articleTag.setTagId(tag.getUuid());
            articleTagRepository.save(articleTag);
        });
    }

    @Override
    public void deleteArticleById(String articleId) {
        articleRepository.delete(articleId);
    }

    @Override
    public void updateArticleById(Article article) {
        articleRepository.save(article);
    }

    @Override
    public Article findArticleById(String articleId) {
        return articleRepository.findOne(articleId);
    }

    @Override
    public List<Article> findArticleByPage(Integer page) {
        return articleRepository.findArticlesByPage(page);
    }

    @Override
    public List<Article> findArticleByTag(Tag Tag) {
        return null;
    }


}
