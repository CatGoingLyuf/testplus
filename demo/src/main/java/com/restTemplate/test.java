package com.restTemplate;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

import java.nio.charset.Charset;

import static org.springframework.http.HttpMethod.GET;

/**
 * @Author lyuf
 * @Date 2022/5/7 15:06
 * @Version 1.0
 */
public class test {

    public static void main(String[] args) {

        HttpHeaders httpHeaders = new HttpHeaders() {{
            String auth = "JENKINS_USERNAME" + ":" + "JENKINS_PASSWORD";
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};

        RestTemplate restTemplate = new RestTemplate();
        String uri = "www.baidu.com";

        restTemplate.postForEntity(uri, new HttpEntity<String>(httpHeaders), String.class);
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(uri, GET, new HttpEntity<String>(httpHeaders), JSONObject.class);


    }

}
