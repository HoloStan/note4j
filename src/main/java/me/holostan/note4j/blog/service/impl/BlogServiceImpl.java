package me.holostan.note4j.blog.service.impl;

import me.holostan.note4j.blog.entity.Blog;
import me.holostan.note4j.blog.service.BlogService;
import me.holostan.note4j.core.util.MarkdownHelper;
import me.holostan.note4j.core.util.UUIDWorker;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by ghu on 8/22/2017.
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Override
    public void addBlog(Blog blog) {
        blog.setUuid(UUIDWorker.next());
        MarkdownHelper.generate(
                blog.getTitle(),
                new Date().toLocaleString(),
                null,
                null,
                false,
                false,
                blog.getMdbody()
        );
    }

    @Override
    public void deleteBlogById(String blogId) {

    }

    @Override
    public void updateBlogById(Blog blog) {

    }

    @Override
    public Blog findBlogById(String blogId) {
        return null;
    }

    @Override
    public List<Blog> findBlogByPage(Integer page) {
        return null;
    }
}
