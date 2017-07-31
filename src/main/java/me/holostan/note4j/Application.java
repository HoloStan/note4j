package me.holostan.note4j;

import me.holostan.note4j.article.entity.ResponseMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by ghu on 6/1/2017.
 */
@SpringBootApplication
public class Application {

    protected static Logger logger= LoggerFactory.getLogger(Application.class);

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter () {
        return new MappingJackson2HttpMessageConverter () {
            // override function writeInternal to package result before output.
            @Override
            protected void writeInternal (Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
                ResponseMap responseMap = new ResponseMap();
                responseMap.setStatus(200);
                responseMap.setMessage("SUCCESS");
                responseMap.setData(object);
                this.writeInternal(responseMap, (Type) null, outputMessage);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> Start up Application Success <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
