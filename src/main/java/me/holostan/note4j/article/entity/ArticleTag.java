package me.holostan.note4j.article.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ghu on 7/26/2017.
 */
@Entity
@Table(name="t_article_tag")
public class ArticleTag implements Serializable{

    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    protected String uuid;
    @Column(name = "article_id")
    protected String articleId;
    @Column(name = "tag_id")
    protected String tagId;

    //region getter & setter
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
    //endregion

}
