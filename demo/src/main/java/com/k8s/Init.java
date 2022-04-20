package com.k8s;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import io.kubernetes.client.util.credentials.AccessTokenAuthentication;

import java.io.FileReader;
import java.io.IOException;

public class Init {
//    @Value("${kubernetes.http}")
    private static String http = "http://192.168.195.1:6443";
//    @Value("${kubernetes.token}")
    private static String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImFXdkQtV19ZWnl5eF9Kd0pQaU54MXgtcXltc05UNmpKQlFyWGUwT3Y3WlkifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlLXN5c3RlbSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJrOHMtYWRtaW4tdG9rZW4tY2RiZzciLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiazhzLWFkbWluIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQudWlkIjoiNjI1YzljY2ItOWRmZi00NDkxLTlmMmMtMjhlOTI3YTgzNTMwIiwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50Omt1YmUtc3lzdGVtOms4cy1hZG1pbiJ9.CGFEvElNFkQ9rU3_pkXEM-Y8NTYdBbLppsWCTTFOMDI0UxMq3xMXj8zAivAD_jB-Z7Xvy125QLdgbYtBUqCEo8LqxgnGteHGCwaEAO9oDo3SUjxKhx4lcbJnO3-IramOtPTgmc4jsiNfrdR5-c_vQORcp31QdUfECQGeMtaJGMttpabQwcjVRhdNHkP0MDoaf3h2RHiGmusGyVbUV58CLSUhoRz2spLWbjaGmLlgKLy2nzTX60oniqxKb-YBXXil6DZLgUAbn2npiq5i0bLZiILhSdrtjIfcD9btgfD4jki4E5Bl9L7KNkdcQWSON0BeVLLN-yRCs7PoGiT1D7d3Kw";
    //使用token 连接api客户端
    public static ApiClient getConnection() {
        ApiClient client = new ClientBuilder().
                setBasePath(http).setVerifyingSsl(false).
                setAuthentication(new AccessTokenAuthentication(token)).build();
        //将加载config的client设置为默认的client
        Configuration.setDefaultApiClient(client);
        return client;
    }

    //使用yml 连接api客户端
    public static ApiClient getConnection2() throws IOException {
        //config path
        String kubeConfigPath = "D:\\IdeaProjects\\testplus\\demo\\config.yml";

        ApiClient client = null;
        //加载k8s, config
        //client = Config.fromConfig(kubeConfigPath);
        client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();
        //将加载config的client设置为默认的client
        Configuration.setDefaultApiClient(client);
        return client;
    }


}