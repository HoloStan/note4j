package me.holostan.note4j.article.entity.type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="t_tag")
public class TagType implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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

    //endregion

}

