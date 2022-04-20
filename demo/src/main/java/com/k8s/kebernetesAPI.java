package com.k8s;


import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1NamespaceList;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author lyuf
 * @Date 2021/12/16 9:25
 * @Version 1.0
 */
public class kebernetesAPI {

    //获取所有的namespace
    @Test
    public void getNamespace() throws IOException, ApiException {
        //使用token 连接api客户端
        ApiClient client = Init.getConnection();

        //创建一个api
        CoreV1Api api = new CoreV1Api();
        //打印namespace
        V1NamespaceList list = api.listNamespace(null, null, null, null, null, null, null, null, null);
        for (V1Namespace l : list.getItems()) {
            System.out.println(list);
        }
    }

    //打印所有的pod
    @Test
    public void getPod() throws IOException, ApiException {
        //加载k8s 配置 连接api客户端
        ApiClient client = Init.getConnection2();
        //将加载config的client设置为默认的client
//        Configuration.setDefaultApiClient(client);
        //创建一个api
        CoreV1Api api=new CoreV1Api();
        //打印所有的pod
        V1PodList list=api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
        for (V1Pod item:list.getItems()) {
            System.out.println( item.getMetadata().getName());
        }
    }


}
