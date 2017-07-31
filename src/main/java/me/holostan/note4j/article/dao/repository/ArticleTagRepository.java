package me.holostan.note4j.article.dao.repository;

import me.holostan.note4j.article.entity.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ghu on 7/26/2017.
 */
@Repository
public interface ArticleTagRepository extends JpaRepository<ArticleTag,String> {
}
