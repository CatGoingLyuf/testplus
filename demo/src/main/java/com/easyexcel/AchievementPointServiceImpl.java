package com.easyexcel;//package com.easyexcel;
//
//import com.awifi.capacity.achievement.api.feign.FeignAchievementPointService;
//import com.awifi.capacity.achievement.consumer.service.AchievementPointService;
//import feign.Response;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.URLEncoder;
//
//
//@Service
//public class AchievementPointServiceImpl implements AchievementPointService {
//
//    @Autowired
//    private FeignAchievementPointService feignAchievementPointService;
//
//    @Override
//    public String pointExport(String achievementPointPersonal, HttpServletResponse httpServletResponse) {
//        httpServletResponse.setCharacterEncoding("utf-8");
//        httpServletResponse.setContentType("application/vnd.ms-excel");
//        try {
//            String fileName = URLEncoder.encode("个人积分", "UTF-8");
//            httpServletResponse.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
//            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
//
//            Response response = feignAchievementPointService.pointExport(achievementPointPersonal);
//            Response.Body body = response.body();
//            //将响应转为InputStream输入流
//            InputStream inputStream = body.asInputStream();
//            OutputStream outputStream = httpServletResponse.getOutputStream();
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            byte[] buffer = new byte[1024 * 5];
//            int n = 0;
//            while (-1 != (n = inputStream.read(buffer))) {
//                output.write(buffer, 0, n);
//            }
//            //获取输入流的长度
//            byte[] b = output.toByteArray();
//            //将输入流的数据读取到字节数组b中
//            inputStream.read(b);
//            //将字节数组b中的数据写到响应输出流中
//            outputStream.write(b);
//            outputStream.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public String pointApplyExport(String achievementPointApply, HttpServletResponse httpServletResponse) {
//        Response response = feignAchievementPointService.pointApplyExport(achievementPointApply);
//        return null;
//    }
//}
