package me.holostan.note4j.core.util.template.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by ghu on 7/11/2017.
 */
public class RestClient {

    private static Logger logger = LoggerFactory.getLogger(RestClient.class);

    @Autowired
    public RestTemplate restTemplate;

    public String execute(String url, HttpMethod httpMethod) {
        return execute(url, httpMethod, null, null);
    }


    public String execute(String url, HttpMethod httpMethod, String requestBody) {
        return execute(url, httpMethod, requestBody, null);
    }

    public String execute(String url, HttpMethod httpMethod, Map<String,String> headers) {
        return execute(url, httpMethod, null, headers);
    }

    public String execute(String url, HttpMethod httpMethod, String requestBody, Map<String,String> headers, Object...uriVariables) {

        HttpHeaders httpHeaders = new HttpHeaders();
        setHeaders(httpHeaders,headers);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody,httpHeaders);

        logger.info("=========== EXECUTING REQUEST: " + httpMethod.name() + " =============");
        logger.info("=========== URL: " + url);
//        if( uriVariables != null ){
//            logger.info("=========== URI VARIABLES: " + uriVariables.toString());
//        }
        if( httpHeaders.size() != 0 ){
            logger.info("=========== HEADERS: " + getHeaders(httpHeaders));
        }
        if( requestBody != null ){
            logger.info("=========== BODY: \n" + requestBody);
        }

        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url, httpMethod, requestEntity, byte[].class);
        String responseBody = null;
        if( responseEntity.getBody() != null ){
            responseBody = new String(responseEntity.getBody());
        }

        logger.info("=========== RECEIVE RESPONSE =============");
        logger.info("=========== HEADERS: " + getHeaders(responseEntity.getHeaders()));
        logger.info("=========== Status Code: " + responseEntity.getStatusCode());
        logger.info("=========== BODY: \n" + (responseBody == null ? "" : responseBody));

        HttpStatus status = responseEntity.getStatusCode();
        if(status.is4xxClientError() || status.is5xxServerError()){
            throw new RuntimeException("Error: "+ status.value() + url);
        }

        return responseBody;
    }


    public ResponseEntity executeForResponse(String url, HttpMethod httpMethod) {
        return executeForResponse(url, httpMethod, null, null);
    }


    public ResponseEntity executeForResponse(String url, HttpMethod httpMethod, String requestBody) {
        return executeForResponse(url, httpMethod, requestBody, null);
    }

    public ResponseEntity executeForResponse(String url, HttpMethod httpMethod, Map<String,String> headers) {
        return executeForResponse(url, httpMethod, null, headers);
    }

    public ResponseEntity executeForResponse(String url, HttpMethod httpMethod, String requestBody, Map<String,String> headers, Object...uriVariables) {

        HttpHeaders httpHeaders = new HttpHeaders();
        setHeaders(httpHeaders,headers);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody,httpHeaders);

        logger.info("=========== EXECUTING REQUEST: " + httpMethod.name() + " =============");
        logger.info("=========== URL: " + url);
//        if( uriVariables != null ){
//            logger.info("=========== URI VARIABLES: " + uriVariables.toString());
//        }
        if( headers != null ){
            logger.info("=========== HEADERS: " + getHeaders(httpHeaders));
        }
        if( requestBody != null ){
            logger.info("=========== BODY: \n" + requestBody);
        }

        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class);
        String responseBody = null;
        if( responseEntity.getBody() != null ){
            responseBody = new String(responseEntity.getBody());
        }

        logger.info("=========== RECEIVE RESPONSE =============");
        logger.info("=========== HEADERS: " + getHeaders(responseEntity.getHeaders()));
        logger.info("=========== Status Code: " + responseEntity.getStatusCode());
        logger.info("=========== BODY: \n" + (responseBody == null ? "" : responseBody));

        HttpStatus status = responseEntity.getStatusCode();
        if(status.is4xxClientError() || status.is5xxServerError()){
            throw new RuntimeException("Error: " + status.value() + url);
        }

        return responseEntity;
    }

    private String getHeaders(HttpHeaders httpHeaders) {
        StringBuffer headerBuffer = new StringBuffer();
        for(Map.Entry entry : httpHeaders.entrySet()){
            headerBuffer.append(entry.getKey()).append(':').append(entry.getValue()).append(',');
        }
        return headerBuffer.substring(0, headerBuffer.length() - 1);
    }

    private void setHeaders(HttpHeaders httpHeaders, Map<String,String> headers) {

        httpHeaders.set("Nucleus-RequestorId","Ebisu-Platform");
        httpHeaders.set("Accept", "application/xml");
        httpHeaders.set("X-Source", "dev");
        httpHeaders.set("X-Expand-Results", "true");
        httpHeaders.set("X-Include-Underage", "true");

        if(headers == null){
            return;
        }
        for(Map.Entry<String,String> entry : headers.entrySet()){
            httpHeaders.set(entry.getKey(), entry.getValue());
        }
    }

}
