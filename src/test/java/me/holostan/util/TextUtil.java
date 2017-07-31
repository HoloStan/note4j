package me.holostan.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by ghu on 7/24/2017.
 */
public class TextUtil {

    private static Logger logger = LoggerFactory.getLogger(TextUtil.class);

    public static void main(String[] args) throws Exception{

        File path = new File("D:\\out\\code");
        File[] files = path.listFiles();

        StringBuffer buffer = new StringBuffer();

        for(File file : files) {

            buffer.append(file.getName()).append('\n');

            String content = new Scanner(file).useDelimiter("\\Z").next();
            JSONObject o = (JSONObject) JSON.parse(content);
            String legal_msg = o.getString("ro.lb.legal_msg");
            String legal_msg_EU = o.getString("ro.lb.legal_msg.EU");
            String legal_msg_JP = o.getString("ro.lb.legal_msg.JP");

            buffer.append("CheckoutDisclaimer_US=" + htmlRemoveTag(legal_msg)).append('\n');
            buffer.append("CheckoutDisclaimer_EU=" + htmlRemoveTag(legal_msg_EU)).append('\n');
            buffer.append("CheckoutDisclaimer_JP=" + htmlRemoveTag(legal_msg_JP)).append('\n');
            buffer.append('\n');
        }

        PrintWriter out = new PrintWriter("D:\\out\\out.txt");
        try {
            out.print( buffer.toString() );
            out.flush();
        } finally {
            out.close();
        }

    }

    public static String htmlRemoveTag(String inputString) {
        if (inputString == null)
            return null;
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;
        try {
            //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
            //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签
            textStr = htmlStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return textStr;// 返回文本字符串
    }

}
