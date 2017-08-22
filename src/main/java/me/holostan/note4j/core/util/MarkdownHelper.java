package me.holostan.note4j.core.util;

import com.github.rjeschke.txtmark.Processor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

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
                .append("tags: ").append("\n")
                .append("categories: ").append("\n")
                .append("toc: ").append(toc).append("\n")
                .append("mathjax: ").append(mathjax).append("\n")
                .append("---\n")
                .append("\n")
                .append(mdbody);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("d:/demo.md")))) {
            //write contents of StringBuffer to a file
            bufferedWriter.write(buffer.toString());
            //flush the stream
            bufferedWriter.flush();
            //close the stream
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
