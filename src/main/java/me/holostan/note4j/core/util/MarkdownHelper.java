package me.holostan.note4j.core.util;

import com.github.rjeschke.txtmark.Processor;

/**
 * Created by ghu on 8/7/2017.
 */
public class MarkdownHelper {

    public static String process(String markdown) {
        return Processor.process(markdown);
    }

}
