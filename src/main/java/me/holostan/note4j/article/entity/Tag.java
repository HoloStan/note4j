package me.holostan.note4j.article.entity;

import me.holostan.note4j.article.entity.type.ArticleType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ghu on 7/26/2017.
 */
@Entity
@Table(name="t_tag")
public class Tag implements Serializable {

    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    protected String uuid;
    protected String name;
    protected String description;
    @Column(name = "user_id")
    protected String userId;
    protected String username;
    @Column(name = "create_time")
    protected Date createdTime;
    @Column(name = "modified_time")
    protected Date modifiedTime;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "t_article_tag",
            joinColumns = @JoinColumn(
                    name = "tag_id",
                    referencedColumnName = "uuid"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "article_id",
                    referencedColumnName = "uuid"
            )
    )
    protected List<ArticleType> articles;

    //region getter & setter

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public List<ArticleType> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleType> articles) {
        this.articles = articles;
    }
    //endregion
}
