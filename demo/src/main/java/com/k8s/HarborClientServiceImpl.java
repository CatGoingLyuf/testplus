package com.k8s;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.ConnectException;
import java.net.URI;
import java.sql.SQLDataException;
import java.util.*;


/**
 * 创建日期：2021/12/24
 * 创建作者：hupenghao
 * 文件名称：
 * 版本：1.0
 * 功能：
 * 最后修改时间：
 * 修改记录:
 */
@Slf4j
@Service
public class HarborClientServiceImpl implements HarborClientService {

    private static final int SUCCESSCODE = 200;
    private static final int SUCCESSCODE2 = 201;

    private static final String HTTP_PREFIX = "http://";

    /**
     * get请求
     * @param request
     * @param harborIpAddr harbor ip端口
     * @return
     */
    @Override
    public String get(String harborIpAddr,HttpServletRequest request) throws Exception{
        Long projectId  = (Long) request.getAttribute("projectId");
        Long repositoryId  = (Long) request.getAttribute("repositoryId");
        HttpGet get = new HttpGet();
        get = putToken(repositoryId,harborIpAddr,projectId,get);
        HttpResponse response = executeHttp(harborIpAddr,get,String.valueOf(request.getAttribute(RepositoryConstants.REQURLPARAMS)));
        System.out.println(response.getStatusLine().getStatusCode());
        if(response.getStatusLine().getStatusCode() != SUCCESSCODE
                && response.getStatusLine().getStatusCode() != SUCCESSCODE2  )
        {
            throw  new ConnectException("仓库连接失败，无法查询");
        }
        String result = EntityUtils.toString(response.getEntity());
        return  result;
    }

    /**
     * post请求
     * @param request
     * @param harborIpAddr harbor ip端口
     * @return
     */
    @Override
    public String post(String harborIpAddr,HttpServletRequest request,String params) throws  Exception {
        Long projectId  = (Long) request.getAttribute("projectId");
        Long repositoryId  = (Long) request.getAttribute("repositoryId");
        HttpPost post = new HttpPost();
        //放置头信息 （token）
        post = putToken(repositoryId,harborIpAddr,projectId,post);
        //执行请求
        HttpResponse response = executeHttp(harborIpAddr,post,params,String.valueOf(request.getAttribute(RepositoryConstants.REQURLPARAMS)));
        String result = String.valueOf(response.getStatusLine().getStatusCode());
        //post的执行结果返回
        return result;
    }

    /**
     * put请求
     * @param request
     * @param harborIpAddr harbor ip端口
     * @return
     */
    @Override
    public String put(String harborIpAddr,HttpServletRequest request,String params) throws  Exception{
        Long projectId  = (Long) request.getAttribute("projectId");
        Long repositoryId  = (Long) request.getAttribute("repositoryId");
        HttpPut put = new HttpPut();
        put = putToken(repositoryId,harborIpAddr,projectId,put);
        HttpResponse response = executeHttp(harborIpAddr,put,params,String.valueOf(request.getAttribute(RepositoryConstants.REQURLPARAMS)));
        String result = String.valueOf(response.getStatusLine().getStatusCode());
        return result;
    }

    /**
     * delete请求
     * @param request
     * @param harborIpAddr harbor ip端口
     * @return
     */
    @Override
    public String delete(String harborIpAddr,HttpServletRequest request) throws  Exception{
        Long projectId  = (Long) request.getAttribute("projectId");
        Long repositoryId  = (Long) request.getAttribute("repositoryId");
        HttpDelete delete = new HttpDelete();
        delete = putToken(repositoryId,harborIpAddr,projectId,delete);
        HttpResponse response = executeHttp(harborIpAddr,delete,String.valueOf(request.getAttribute(RepositoryConstants.REQURLPARAMS)));
        String result = String.valueOf(response.getStatusLine().getStatusCode());
        return result;
    }




    /**
     * 为http请求设置token信息
     * @param t
     * @param <T>
     * @return
     */
    private <T extends HttpRequestBase> T  putToken(Long repositoryId,String harborIpAddr,Long projectId,T t) throws Exception
    {
//        Map<String,String> map = getToken(repositoryId,harborIpAddr,projectId);
        Map<String,String> map = new HashMap<>();
        t.setHeader("Cookie",map.get("cookie"));
        t.setHeader("X-Harbor-CSRF-Token",map.get("token"));
        t.setHeader("Content-Type","application/json");
        return  t;
    }

    /**
     * 执行无需Entity的Http请求
     * @param t
     * @param <T>
     * @return
     * @throws Exception
     */
    private <T extends  HttpRequestBase> HttpResponse  executeHttp(String harborIpAddr,T t,String url) throws Exception
    {
        HttpClient client = HttpClientBuilder.create().build();
        t.setURI(URI.create(HTTP_PREFIX+harborIpAddr+url));
        HttpResponse response = client.execute(t);
        return  response;
    }

    /**
     * 执行需要带参数的Http
     * @param t
     * @param params
     * @param <T>
     * @return
     * @throws Exception
     */
    private <T extends HttpEntityEnclosingRequestBase> HttpResponse  executeHttp(String harborIpAddr,T t ,String params,String url) throws Exception
    {
        HttpClient client = HttpClientBuilder.create().build();
        t.setURI(URI.create(HTTP_PREFIX+harborIpAddr+url));
        t.setEntity(new StringEntity(params));
        HttpResponse response = client.execute(t);
        log.info("");
        return  response;
    }

    /**
     * 创建harbor用户
     * @param projectId
     * @return
     * @throws Exception
     */
    private String createHarborUser(Long repositoryId,String harborIpAddr,Long projectId) throws Exception
    {

        //生成随机的账号密码
        String username = UUID.randomUUID().toString().replace("-","");
        String password = "Cn1"+getStringRandom(10);
        //生成harbor所需要的用户对象
        HarborUser harborUser = new HarborUser();
        harborUser.setUsername(username);
        harborUser.setRealname(projectId.toString());
        harborUser.setPassword(password);
        harborUser.setEmail(username+"@qq.com");
        //创建用户
        HttpPost post = new HttpPost();
        HttpClient client = HttpClientBuilder.create().build();
        post.setURI(URI.create(HTTP_PREFIX+harborIpAddr+"/api/v2.0/users"));
//        post.setEntity(new StringEntity(JSON.toJSONString(harborUser)));
        post = putToken(repositoryId,harborIpAddr,new Long(0),post);
        log.info("创建用户的信息："+harborUser);
        HttpResponse execute = client.execute(post);
        log.info("创建仓库时创建用户操作执行结果:"+execute.getStatusLine().toString());

        return "";
    }

    /**
     *生成随机用户名，数字和字母组成,
     * @param length
     * @return
     */
    private String getStringRandom(int length) {
        String val = "";
        Random random = new Random();

        for(int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
}
