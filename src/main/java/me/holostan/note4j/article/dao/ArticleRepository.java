package me.holostan.note4j.article.dao;

import me.holostan.note4j.article.entity.Article;
import me.holostan.note4j.article.entity.Tag;
import me.holostan.note4j.article.entity.type.ArticleType;
import org.apache.ibatis.annotations.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ghu on 7/19/2017.
 */
@Repository
public interface ArticleRepository extends JpaRepository<ArticleType, String> {

    @Select(selectAllArticlesByPage)
    @Results(value={
            @Result(property="uuid", column="uuid"),
            @Result(property="title", column="title"),
            @Result(property="author", column="author"),
            @Result(property="mdbody", column="mdbody"),
            @Result(property = "createdTime", column = "created_time"),
            @Result(property = "modifiedTime", column = "modified_time"),
            @Result(property="isPublishded", column="is_publishded"),
            @Result(property="isDeleted", column="is_deleted"),
            @Result(property="tags", column="uuid", many=@Many(
                    select="me.holostan.note4j.article.dao.ArticleRepository.selectAllTagsOfOneArticle")),
    })
    public List<Article> selectAllArticlesByPage(@Param("begin") int begin);

    @Select(selectAllTagsOfOneArticle)
    public List<Tag> selectAllTagsOfOneArticle(@Param("articleId") long articleId);


    //region SQL
    String selectAllArticlesByPage = "SELECT * FROM t_article WHERE username=#{username} " +
            "ORDER BY id DESC LIMIT 10 OFFSET #{begin}";

    String selectAllTagsOfOneArticle = "SELECT t.* FROM t_tag AS t INNER JOIN t_article_tag AS at" +
            "ON t.id=at.tag_id WHERE at.article_id=#{articleId}";
    //endregion

}
