package com.sh;

import com.imageVerifyCode.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author lyuf
 * @Date 2022/2/18 14:30
 * @Version 1.0
 */
@Slf4j
public class sshpass {


    public void sshpass() {
        StringBuilder getTokenCmd = new StringBuilder("sshpass -p '");
        getTokenCmd.append("625989").append("' ssh root@").append("192.168.155.51").append(" ")
                .append("echo test-print");

        System.out.println("获取集群级别token指令："+ getTokenCmd.toString());

//        result = executeCmd(clusterId, getTokenCmd.toString());
    }

    private ThreadPoolExecutor executor;

    private static final String BASH = "sh";
    private static final String BASH_PARAM = "-c";
    public ResponseJson executeCmd(String clusterId, String cmd) {
        ResponseJson result = new ResponseJson();
        Process p = null;
        String res;
        log.debug("命令行信息 : {}", cmd);
        try {
            // need to pass command as bash's param,
            // so that we can compatible with commands: "echo a >> b.txt" or "bash a && bash b"
            List<String> cmds = new ArrayList<>();
            cmds.add(BASH);
            cmds.add(BASH_PARAM);
            cmds.add(cmd);
            ProcessBuilder pb = new ProcessBuilder(cmds);
            p = pb.start();

            Future<String> errorFuture = executor.submit(new ReadTask(clusterId, p.getErrorStream()));
            Future<String> resFuture = executor.submit(new ReadTask(clusterId, p.getInputStream()));
            int exitValue = p.waitFor();
            if (exitValue > 0) {
                log.info("执行cmd命令错误: {} ", errorFuture.get());
                res = errorFuture.get();
                result.setErrorCode("-1");
//                throw new RuntimeException(res);
            } else {
                log.info("执行cmd命令成功 ");
                res = resFuture.get();
                result.setErrorCode(ResponseJson.EnumCode.CODE_OK.getCode());
            }
        } catch (Exception e) {
            log.info("执行cmd命令错误: {} ", e.getMessage());
            res = e.getMessage();
            result.setErrorCode("-1");
//            throw new RuntimeException(res);
        } finally {
            if (p != null) {
                p.destroy();
            }
        }
        // remove System.lineSeparator() (actually it's '\n') in the end of res if exists
        if (StringUtils.isNotBlank(res) && res.endsWith(System.lineSeparator())) {
            res = res.substring(0, res.lastIndexOf(System.lineSeparator()));
        }
        result.setErrorMsg(res);
        return result;
    }

    class ReadTask implements Callable<String> {
        String clusterId;
        InputStream is;

        ReadTask(String clusterId, InputStream is) {
            this.clusterId = clusterId;
            this.is = is;
        }

        @Override
        public String call() throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String finalLine = "";
            String line;
            while ((line = br.readLine()) != null) {
                log.info(line);
                // 将输出信息放入队列（kafka）
//                saveLogs(clusterId, line, false);
                finalLine = line;
            }
            return finalLine;
        }
    }
}
