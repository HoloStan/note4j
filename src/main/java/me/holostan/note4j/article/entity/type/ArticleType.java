package me.holostan.note4j.article.entity.type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ghu on 7/19/2017.
 */
@Entity
@Table(name="t_article")
public class ArticleType implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected String uuid;
    protected String title;
    protected String author;
    protected String mdbody;
    protected Date createdTime;
    protected Date modifiedTime;
    protected Boolean isPublishded;
    protected Boolean isDeleted;

    //region getter & setter
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMdbody() {
        return mdbody;
    }

    public void setMdbody(String mdbody) {
        this.mdbody = mdbody;
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

    public Boolean getIsPublishded() {
        return isPublishded;
    }

    public void setIsPublishded(Boolean isPublishded) {
        this.isPublishded = isPublishded;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    //endregion
}
