package me.holostan.note4j.core.util;

import com.github.rjeschke.txtmark.Processor;

/**
 * Created by ghu on 8/7/2017.
 */
public class MarkdownHelper {

    /**
     * convert markdown to html
     * @param markdown
     * @return
     */
    public static String process(String markdown) {
        return Processor.process(markdown);
    }

    /*---
    title: Hexo Markdown-it 简明语法手册
    date: 2016-01-15 20:19:32
    tags: [Markdown,LaTex,教程]
    categories: 学习
    toc: true
    mathjax: true
    ---*/
    public static void generate(String title, String date, String[] tags, String[] categories, boolean toc, boolean mathjax, String mdbody) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("---\n")
                .append("title: ").append(title).append("\n")
                .append("date: ").append(date).append("\n")
                .append("tags: ")
                .append("categories: ")
                .append("toc: ")
                .append("mathjax: ")
                .append("---\n")
                .append("\n")
                .append(mdbody);
    }

}
