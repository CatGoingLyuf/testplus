package com.sh;

import ch.ethz.ssh2.*;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * @Author lyuf
 * @Date 2021/11/26 17:05
 * @Version 1.0
 */
@Slf4j
public class ShUtil {

    public static void main(String[] args) {
        String jobName = "aoa";
        testExecCmd(jobName,43);
    }

    public static void testExecCmd(String jobName,Integer id) {
//        String serverIp = "192.168.211.118";
//        String userName = "root";
//        String pwd = "5FL1dpW7";
//        String auth = "bearer 8aafd7e1-ab09-4033-9c21-de3ecf623beb";
//        String cmd2 = "cd /var/lib/jenkins/workspace/" + jobName;
//        exec(serverIp, userName, pwd, cmd2);
//        String[] cmd = new String[]{
//                "zip -r /opt/upload/" + jobName + ".zip /var/lib/jenkins/workspace/" + jobName,
//                "./opt/upload/upload.sh "+ jobName + " " + auth + " " + id};
//        loginAndExec(serverIp, userName, pwd, cmd);
    }


    public static void loginAndExec(String ip, String userName, String pwd, String[] cmd) {
        Connection con = new Connection(ip);

        try {
            con.connect();
            boolean isAuthed = con.authenticateWithPassword(userName, pwd);
            if (!isAuthed) {
                log.error("ssh connection username & pwd authed failed");
                return;
            }

//            SCPClient scpClient = con.c.createSCPClient(); //
//            SFTPv3Client sftpClient = new SFTPv3Client(con);
            //ConnectionInfo info = con.getConnectionInfo();
//            ch.ethz.ssh2.Session session =con
//                    .openSession();
//            // 这句非常重要，开启远程的客户端
//            session.requestPTY("vt100", 80, 24, 640, 480, null);
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            for (int i = 0; i < cmd.length; i++) {
                execConnection(con, "source ~/.bash_profile ;" + cmd[i]); //
                System.err.println(cmd[i]);
//                Thread.sleep(2000);
            }


        } catch (IOException e) {
            log.error("ssh failed ", e);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.close();
        }
    }

    public static void execConnection(Connection connection, String command) throws Exception {
        Session session = connection.openSession();
        log.debug("command:" + command);
        //  session.requestPTY("vt100", 80, 24, 640, 480, null);
        session.execCommand(command);
        InputStream stdout = null;
        BufferedReader br = null;
        try {
            stdout = new StreamGobbler(session.getStdout());
            br = new BufferedReader(new InputStreamReader(stdout));
        } catch (Exception e) {
            log.error("error in print ssh mkdir log", e);
            e.printStackTrace();
        } finally {
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                log.debug(line);
                System.out.println(line);
            }
            if (br != null) {
                br.close();
            }
            if (stdout != null) {
                stdout.close();
            }
        }
        session.close();
    }

    public static void exec(String ip, String userName, String pwd, String cmd) {
        Connection con = new Connection(ip);

        try {
            con.connect();
            boolean isAuthed = con.authenticateWithPassword(userName, pwd);
            if (!isAuthed) {
                log.error("ssh upload file username & pwd authed failed");
                return;
            }

            SCPClient scpClient = con.createSCPClient(); //
            SFTPv3Client sftpClient = new SFTPv3Client(con);

            ch.ethz.ssh2.Session session = con.openSession();

            try {
                exec(session, cmd); //
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                session.close();
            }
        } catch (IOException e) {
            log.error("ssh upload file failed ", e);

        } finally {
            con.close();
        }
    }

    public static void exec(ch.ethz.ssh2.Session session, String command) throws Exception {
        session.execCommand(command); //

        InputStream stdout = null;
        BufferedReader br = null;
        try {
            stdout = new StreamGobbler(session.getStdout());
            br = new BufferedReader(new InputStreamReader(stdout));
        } catch (Exception e) {
            log.error("error in print ssh mkdir log", e);
        } finally {
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                log.debug(line);
                System.out.println(line);
            }
            if (br != null) {
                br.close();
            }
            if (stdout != null) {
                stdout.close();
            }
        }
    }

}
