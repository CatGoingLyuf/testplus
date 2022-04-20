package com.imageVerifyCode;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


/**
 * @Author lyuf
 * @Date 2022/1/7 11:43
 * @Version 1.0
 */
public class demo {

    @Test
    public void testee(){
        Class<? extends demo> aClass = getClass();
        String simpleName = getClass().getSimpleName();
        System.out.println(simpleName);
    }


    Random random = new Random();
    //图片的宽度
    private int width = 160;
    //图片的高度
    private int height = 40;
    //验证码字符个数
    private int codeCount = 4;

    private String[] fontNames = {"Georgia"};

    //验证码
    private String code = null;

    // 验证码数组
    private String codes = "23456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";

    //获取随机字符
    private char randomChar() {
        int index = random.nextInt(codes.length());
        return codes.charAt(index);
    }

    //获取随机字体
    private Font randomFont() {
        int index = random.nextInt(fontNames.length);  //获取随机的字体
        String fontName = fontNames[index];
        int style = random.nextInt(4);         //随机获取字体的样式，0是无样式，1是加粗，2是斜体，3是加粗加斜体
        int size = random.nextInt(10) + 24;    //随机获取字体的大小
        return new Font(fontName, style, size);   //返回一个随机的字体
    }

    //获取随机的颜色
    private Color randomColor() {
        int r = this.random.nextInt(225);  //这里为什么是225，因为当r，g，b都为255时，即为白色，为了好辨认，需要颜色深一点。
        int g = this.random.nextInt(225);
        int b = this.random.nextInt(225);
        return new Color(r, g, b);            //返回一个随机颜色
    }

    //画干扰线，验证码干扰线用来防止计算机解析图片
    private void drawLine(BufferedImage image) {
        int num = random.nextInt(10); //定义干扰线的数量
        Graphics2D g = (Graphics2D) image.getGraphics();
        for (int i = 0; i < num; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            g.setColor(randomColor());
            g.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * 创建图片的方法
     */
    private BufferedImage getImage() {
        //创建图片缓冲区
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //获取画笔
        Graphics2D g = (Graphics2D) image.getGraphics();
        //设置背景色随机

        g.setColor(new Color(255, 255, random.nextInt(245) + 10));
        g.fillRect(0, 0, width, height);
        //返回一个图片
        return image;
    }

    /**
     * 获取验证码图片的方法
     */
    @Test
    public void creatImage() {
        RandomVerifyCodeUtil verifyCode = new RandomVerifyCodeUtil();
        BufferedImage buffImg = null;
        buffImg = getImage();
        Graphics2D g = (Graphics2D) buffImg.getGraphics(); //获取画笔
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++)             //画四个字符即可
        {
            String s = randomChar() + "";      //随机生成字符，因为只有画字符串的方法，没有画字符的方法，所以需要将字符变成字符串再画
            sb.append(s);                      //添加到StringBuilder里面
            float x = i * 1.0F * width / 4;   //定义字符的x坐标
            g.setFont(randomFont());           //设置字体，随机
            g.setColor(randomColor());         //设置颜色，随机
            g.drawString(s, x, height - 5);
        }
        this.code = sb.toString();
        drawLine(buffImg);
    }























    @Test
    public void genCode(){

//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        request.setAttribute("uuid","1234556");
        String params = "{\r\n    \"uuid\": \"15463125\"\r\n}";

        String uuid = getUuidFromQueriesOrHeader(params, null);

        System.out.println(uuid);
    }
    /**
     * 获取 uuid
     * @param request
     * @return
     */
    private  String getUuidFromQueriesOrHeader(String params, HttpServletRequest request) {
//        String params = request.getParameter("params");
        if (StringUtils.isNotBlank(params)) {
            JSONObject jsonObject = JSONObject.parseObject(params);
            String uuid = jsonObject.getString("uuid");
            if (StringUtils.isNotBlank(uuid)) {
                return uuid;
            }
        }
        // 请求参数没有 params 或者 params 中没有 uuid
        String uuid = request.getHeader("uuid");
        if (StringUtils.isNotBlank(uuid)) {
            return uuid;
        }
        // 请求头中也没有携带 uuid 参数
        String code = ResponseJson.EnumCode.CODE_PARAMETER_INVALID.getCode();
        throw new ValidateCodeException(code, "请在请求参数params中或请求头中携带 uuid 参数");
    }
}
