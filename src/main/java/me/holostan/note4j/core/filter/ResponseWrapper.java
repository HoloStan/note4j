package me.holostan.note4j.core.filter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.PrintWriter;

/**
 * Created by ghu on 8/7/2017.
 */
public class ResponseWrapper extends HttpServletResponseWrapper {

    private PrintWriter cachedWriter;
    private CharArrayWriter bufferedWriter;

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        // 这个是我们保存返回结果的地方
        bufferedWriter = new CharArrayWriter();
        // 这个是包装PrintWriter的，让所有结果通过这个PrintWriter写入到bufferedWriter中
        cachedWriter = new PrintWriter(bufferedWriter);
    }

    @Override
    public PrintWriter getWriter() {
        return cachedWriter;
    }

    /**
     * 获取原始的HTML页面内容。
     *
     * @return
     */
    public String getResult() {
        return bufferedWriter.toString();
    }

}
