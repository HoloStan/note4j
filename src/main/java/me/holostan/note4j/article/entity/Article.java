package me.holostan.note4j.article.entity;


import me.holostan.note4j.article.entity.type.ArticleType;

import java.io.Serializable;
import java.util.List;

public class Article extends ArticleType implements Serializable {

    protected List<Tag> tags;

    //region getter & setter

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    //endregion

}
