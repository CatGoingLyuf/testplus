package com.jenkins;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import lombok.SneakyThrows;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Author lyuf
 * @Date 2022/3/16 10:30
 * @Version 1.0
 */
public class test {

    String JENKINS_URL = "http://192.168.209.28:8088/";
    String JENKINS_USERNAME = "root";
    String JENKINS_PASSWORD = "Awifi@123";

    @Test
    public void link() throws URISyntaxException {
        JenkinsHttpClient jenkinsHttpClient = null;
        JenkinsServer jenkinsServer = null;
        //设置客户端连接 Jenkins
        jenkinsHttpClient = new JenkinsHttpClient(new URI(JENKINS_URL), JENKINS_USERNAME, JENKINS_PASSWORD);
        //连接 Jenkins
        jenkinsServer = new JenkinsServer(jenkinsHttpClient);

        System.out.println(jenkinsServer);
    }


}
