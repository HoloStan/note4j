package me.holostan.note4j.core.filter;

import com.sun.javafx.sg.prism.CacheFilter;
import me.holostan.note4j.core.util.MarkdownHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by ghu on 8/7/2017.
 */
@Order(1)
@WebFilter(filterName = "redisCacheFilter", urlPatterns = "/*")
@Component
public class RedisCacheFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CacheFilter.class);

    @Value("#{'${redis.cache.cacheUris}'.split(',')}")
    private static List<String> cacheUris;
    @Value("${redis.cache.minutes}")
    private static long minutes;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        // if the page is not cachable ,do filter and return.
        if (!isCachable(req)) {
            filterChain.doFilter(servletRequest, resp);
            return;
        }

        // try get cached html from redis.
        String articleId = getArticleId(req);
        String html = getHtmlFromCache(articleId);
        //if no cache found in redis, get markdown from data base and cache html before response.
        if (null == html) {
            // 缓存中没有
            // 截取生成的html并放入缓存
            logger.info("缓存不存在，生成缓存");
            ResponseWrapper wrapper = new ResponseWrapper(resp);
            // ***** 以上代码在请求被处理前执行 *****

            filterChain.doFilter(servletRequest, wrapper);

            // ***** 以下代码在请求被处理后执行 *****

            // get markdown from data source
            String markdown = wrapper.getResult();
            //convert to  html
            html = MarkdownHelper.process(markdown);
            //cache html
            setHtmlIntoCache(articleId,html);
        }

        // 返回响应
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().print(html);
    }

    @Override
    public void destroy() {

    }

    private boolean isCachable(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        boolean isMatched = false;
        for (String uri : cacheUris){
            if(requestUri.matches(uri)){
                isMatched = true;
                break;
            }
        }
        return isMatched;
    }

    private String getHtmlFromCache(String articleId) {
        return redisTemplate.opsForValue().get(articleId);
    }

    private void setHtmlIntoCache(String articleId, String html) {
        redisTemplate.opsForValue().set(articleId, html, TimeUnit.MINUTES.toSeconds(minutes)); // 10分钟
    }

    private String getArticleId(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        return requestUri.replace("/article/","");
    }

}
