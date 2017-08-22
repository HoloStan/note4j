package me.holostan.note4j.blog.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ghu on 7/26/2017.
 */
public class Blog implements Serializable {

    protected String uuid;
    protected String title;
    protected String author;
    protected String mdbody;
    protected Date createdTime;
    protected Date modifiedTime;

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

    //endregion

}
