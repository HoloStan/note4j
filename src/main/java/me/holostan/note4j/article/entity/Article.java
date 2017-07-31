package me.holostan.note4j.article.entity;

import me.holostan.note4j.article.entity.type.TagType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ghu on 7/26/2017.
 */
@Entity
@Table(name="t_article")
public class Article implements Serializable {

    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    protected String uuid;
    protected String title;
    protected String author;
    protected String mdbody;
    @Column(name="create_time")
    protected Date createdTime;
    @Column(name="modified_time")
    protected Date modifiedTime;
    @Column(name="is_published")
    protected Boolean isPublished;
    @Column(name="is_deleted")
    protected Boolean isDeleted;

    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(
            name = "t_article_tag",
            joinColumns = @JoinColumn(
                    name = "article_id",
                    referencedColumnName = "uuid"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "tag_id",
                    referencedColumnName = "uuid"
            )
    )
    protected List<TagType> tags;

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

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<TagType> getTags() {
        return tags;
    }

    public void setTags(List<TagType> tags) {
        this.tags = tags;
    }
    //endregion

}
