package me.holostan.note4j.blog.service;

import me.holostan.note4j.blog.entity.Blog;

import java.util.List;

/**
 * Created by ghu on 7/26/2017.
 */
public interface BlogService {

    void addBlog(Blog blog);

    void deleteBlogById(String blogId);

    void updateBlogById(Blog blog);

    Blog findBlogById(String blogId);

    List<Blog> findBlogByPage(Integer page);

}
