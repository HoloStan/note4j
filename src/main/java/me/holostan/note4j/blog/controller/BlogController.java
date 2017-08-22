package me.holostan.note4j.blog.controller;

import me.holostan.note4j.blog.entity.Blog;
import me.holostan.note4j.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ghu on 7/26/2017.
 */
@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blogs/{page}")
    public List<Blog> blogs(@PathVariable Integer page) {
        return blogService.findBlogByPage(page);
    }

    @GetMapping("/blog/{blogId}")
    public Blog findBlog(@PathVariable String blogId) {
        return blogService.findBlogById(blogId);
    }

    @PostMapping(value = "/blog")
    public void addBlog(@RequestBody Blog blog) {
        blogService.addBlog(blog);
    }

    @PutMapping(value = "/blog/{blogId}")
    public void modBlog(@PathVariable String blogId, Blog blog) {
        blog.setUuid(blogId);
        blogService.updateBlogById(blog);
    }

    @DeleteMapping(value = "/blog/{blogId}")
    public void delBlog(@PathVariable String blogId) {
        blogService.deleteBlogById(blogId);
    }

}
