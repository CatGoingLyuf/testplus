package com.jenkins;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class JenkinsTest {


    @Test
    public void test() {

        String JENKINS_URL = "http://192.168.209.28:8088/";
        String JENKINS_USERNAME = "admin";
        String JENKINS_PASSWORD = "91fed975fb8e487ba7246beeeeeb433d";

        JenkinsHttpClient jenkinsHttpClient = null;
        JenkinsServer jenkinsServer = null;
        try {
            //设置客户端连接 Jenkins
            jenkinsHttpClient = new JenkinsHttpClient(new URI(JENKINS_URL), JENKINS_USERNAME, JENKINS_PASSWORD);
            //连接 Jenkins
            jenkinsServer = new JenkinsServer(jenkinsHttpClient);

            // 获取 Job 信息
            JobWithDetails job = jenkinsServer.getJob("test");
            // 获取 Job 名称
            System.out.println("Job 名称" + job.getName());
            // 获取 Job URL
            System.out.println("Job URL" + job.getUrl());
            // 获取 Job 下一个 build 编号
            System.out.println("Job 下一个 build 编号" + job.getNextBuildNumber());
            // 获取 Job 显示的名称
            System.out.println("Job 显示的名称" + job.getDisplayName());
            // 输出 Job 描述信息
            System.out.println("Job 描述信息" + job.getDescription());
            // 获取 Job 下游任务列表
            System.out.println("Job 下游任务列表" + job.getDownstreamProjects());
            // 获取 Job 上游任务列表
            System.out.println("Job 上游任务列表" + job.getUpstreamProjects());

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }


//        /**创建一个流水线任务，且设置一个简单的脚本**/
//        // 创建 Pipeline 脚本
//        String script = "node(){ \n" +
//                "echo 'hello world!' \n" +
//                "}";
//        // xml配置文件，且将脚本加入到配置中
//        String xml = "<flow-definition plugin=\"workflow-job@2.32\">\n" +
//                "<description>测试项目</description>\n" +
//                "<definition class=\"org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition\" plugin=\"workflow-cps@2.66\">\n" +
//                "<script>" + script + "</script>\n" +
//                "<sandbox>true</sandbox>\n" +
//                "</definition>\n" +
//                "</flow-definition>";
//        // 创建 Job
//        jenkinsServer.createJob("test-job",xml,true);


    }
    public String elapsed(Long duration,StringBuilder elapsedTime) {

        if (duration > 1000) {
            Long sec = duration / 1000;
            Long ms = duration % 1000;
            if (sec > 60) {
                Long min = sec / 60;
                sec = sec % 60;
                elapsedTime.replace(0, elapsedTime.length(), min + "分" + sec + "秒" + ms + "毫秒");
                return elapsedTime.toString();
            }
            elapsedTime.replace(0, elapsedTime.length(), sec + "秒" + ms + "毫秒");
        } else {
            elapsedTime.replace(0, elapsedTime.length(), duration + "毫秒");
        }
        return elapsedTime.toString();
    }

    public void ttt(JobWithDetails jobWithDetails) throws IOException {
        jobWithDetails.build();
    }
    @Test
    public void test2() {

        String JENKINS_URL = "http://192.168.211.118:8089/";
        String JENKINS_USERNAME = "jenkins";
        String JENKINS_PASSWORD = "rdp@123";

        JenkinsHttpClient jenkinsHttpClient = null;
        JenkinsServer jenkinsServer = null;

        StringBuilder elapsedTime = new StringBuilder();

        try {
            //设置客户端连接 Jenkins
            jenkinsHttpClient = new JenkinsHttpClient(new URI(JENKINS_URL), JENKINS_USERNAME, JENKINS_PASSWORD);
            //连接 Jenkins
            jenkinsServer = new JenkinsServer(jenkinsHttpClient);

            JobWithDetails job = jenkinsServer.getJob("aoa"); /*根据名称获取job*/

            int nextBuildNumber = job.details().getNextBuildNumber();
            job.build();
            while(true) {
                JobWithDetails jobWithDetails = jenkinsServer.getJob("aoa");
                if (jobWithDetails.getBuildByNumber(nextBuildNumber) == null){
                    System.out.println("第" + nextBuildNumber + "次构建为空，请等待，1S后继续查询");
                    Thread.sleep(1000);
                } else {
                    boolean building = jobWithDetails.getBuildByNumber(nextBuildNumber).details().isBuilding();
                    if (building) {
                        System.out.println("正在构件中请等待，10S后继续查询");
                        Thread.sleep(10000);
                    } else {
                        System.out.println("构建完成");
                        break;
                    }
                }
            }

            jenkinsServer.close();


//            QueueReference build = job.build();


//            int nextBuildNumber = job.getNextBuildNumber();/*获取下一次构建的构建编号，可以用于在触发构建前，先记录构建编号。在后续获取指定编号的构建结果*/
//            System.out.println("下一次构建的构建编号: " + nextBuildNumber);
//
//            QueueReference queueReference = job.build();/*执行指定任务的构建操作*/
//            System.out.println("执行指定任务的构建操作: " + queueReference);
//
//            Build build = job.getBuildByNumber(1);  /*获取某任务第一次构建的构建对象*/
//            System.out.println("获取某任务第一次构建的构建对象: " + build);
//
//            BuildWithDetails buildWithDetails = build.details(); /*子类转型*/
//            System.out.println("子类转型: " + buildWithDetails);
//
//            String log = buildWithDetails.getConsoleOutputText(); /*获取构建的控制台输出信息 ，即构建日志*/
//            System.out.println("构建日志：" + log);

        } catch (IOException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {

        String JENKINS_URL = "http://192.168.56.101:8088/";
        String JENKINS_USERNAME = "admin";
        String JENKINS_PASSWORD = "85ad0b81a272460b81eb9294d05ce13f";

        JenkinsHttpClient jenkinsHttpClient = null;
        JenkinsServer jenkinsServer = null;
        try {
            //设置客户端连接 Jenkins
            jenkinsHttpClient = new JenkinsHttpClient(new URI(JENKINS_URL), JENKINS_USERNAME, JENKINS_PASSWORD);
            //连接 Jenkins
            jenkinsServer = new JenkinsServer(jenkinsHttpClient);

            // 获取 Job 信息
            JobWithDetails job = jenkinsServer.getJob("test"); /*根据名称获取job*/


            System.out.println("根据名称获取job:" + job.getDescription());
            // 获得最后编译信息
            Build lastBuild = job.getLastBuild();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("获得最后编译信息:" + format.format(lastBuild.details().getTimestamp()));
            BuildWithDetails details = lastBuild.details();
            System.out.println("获得最后编译时间:" + lastBuild.details().getDuration() * 0.001 + "秒");
            System.out.println("获得最后编译估计持续时间:" + lastBuild.details().getEstimatedDuration());
            // 获取最后成功的编译信息
            Build lastSuccessfulBuild = job.getLastSuccessfulBuild();
            long duration = job.getLastSuccessfulBuild().details().getDuration();
            System.out.println("根据获取最后成功的编译信息名称获取job:" + lastSuccessfulBuild);
            System.out.println("根据获取最后成功的编译时间:" + duration);
            // 获取最后事变的编译信息
            Build lastFailedBuild = job.getLastFailedBuild();
            System.out.println("获取最后事变的编译信息:" + lastFailedBuild);
            // 获取最后完成的编译信息
            Build lastCompletedBuild = job.getLastCompletedBuild();
            System.out.println("获取最后完成的编译信息:" + lastCompletedBuild);
            // 获取最后稳定的编译信息
            Build lastStableBuild = job.getLastStableBuild();
            System.out.println("获取最后稳定的编译信息:" + lastStableBuild);
            // 获取最后不稳定的编译信息
            Build lastUnstableBuild = job.getLastUnstableBuild();
            System.out.println("获取最后不稳定的编译信息:" + lastUnstableBuild);
            // 获取最后未成功的编译信息
            Build lastUnsuccessfulBuild = job.getLastUnsuccessfulBuild();
            System.out.println("获取最后未成功的编译信息:" + lastUnsuccessfulBuild);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {

        String JENKINS_URL = "http://192.168.56.101:8088/";
        String JENKINS_USERNAME = "admin";
        String JENKINS_PASSWORD = "85ad0b81a272460b81eb9294d05ce13f";

        JenkinsHttpClient jenkinsHttpClient = null;
        JenkinsServer jenkinsServer = null;
        try {
            //设置客户端连接 Jenkins
            jenkinsHttpClient = new JenkinsHttpClient(new URI(JENKINS_URL), JENKINS_USERNAME, JENKINS_PASSWORD);
            //连接 Jenkins
            jenkinsServer = new JenkinsServer(jenkinsHttpClient);

            // 获取 Job 列表
            Map<String, Job> jobs = jenkinsServer.getJobs();
            Map<String, Long> stringLongMap = new HashMap<>();
            for (Job job : jobs.values()) {
                stringLongMap.put(job.getName(), job.details().getLastBuild().details().getDuration());
            }
            System.out.println(stringLongMap);

            Map<String, BuildResult> jobAndState = new HashMap<>();
            for (Job job : jobs.values()) {
                jobAndState.put(job.getName(), job.details().getLastBuild().details().getResult());
            }
            System.out.println(jobAndState);

        } catch (URISyntaxException | IOException e) {
        }
    }

    @Test
    public void test5() throws URISyntaxException, IOException {

        String JENKINS_URL = "http://192.168.56.101:8088/";
        String JENKINS_USERNAME = "admin";
        String JENKINS_PASSWORD = "85ad0b81a272460b81eb9294d05ce13f";

        JenkinsHttpClient jenkinsHttpClient = null;
        JenkinsServer jenkinsServer = null;

        //设置客户端连接 Jenkins
        jenkinsHttpClient = new JenkinsHttpClient(new URI(JENKINS_URL), JENKINS_USERNAME, JENKINS_PASSWORD);
        //连接 Jenkins
        jenkinsServer = new JenkinsServer(jenkinsHttpClient);

        // 获取 Job 列表
        JobWithDetails job = jenkinsServer.getJob("test");




        boolean inQueue = job.isInQueue();

        String description = job.getLastBuild().details().getDescription();
        String consoleOutputText = job.getLastBuild().details().getConsoleOutputText();

        System.out.println(consoleOutputText);

//        job.getBuilds().clear();
        boolean empty = job.getBuilds().isEmpty();


        int number = job.getLastBuild().getNumber();
        BuildWithDetails details = job.details().getBuildByNumber(number).details();
        int queueId = job.details().getBuildByNumber(number).details().getQueueId();


        boolean building = job.details().getBuildByNumber(number).details().isBuilding();


        QueueItem queueItem1 = job.getQueueItem();
        List<Build> builds = job.details().getBuilds();
        List<Build> allBuilds = job.details().getAllBuilds();
        String displayName = job.details().getDisplayName();


        Build builtOn = job.details().getBuildByNumber(1);

        job.details().getBuildByNumber(1).details().isBuilding();


        QueueItem queueItem = job.details().getQueueItem();


    }

    @Test
    public void test6() {
        String JENKINS_URL = "http://192.168.56.101:8088/";
        String JENKINS_USERNAME = "admin";
        String JENKINS_PASSWORD = "85ad0b81a272460b81eb9294d05ce13f";

        String uri = JENKINS_URL + "job/Job/4/doDelete";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders() {{
            String auth = JENKINS_USERNAME + ":" + JENKINS_PASSWORD;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(uri, new HttpEntity<String>(httpHeaders), String.class);
        System.out.println(stringResponseEntity.toString());

    }

}