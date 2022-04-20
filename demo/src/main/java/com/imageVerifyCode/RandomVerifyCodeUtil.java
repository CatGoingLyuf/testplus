package com.imageVerifyCode;

import org.junit.Test;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @Author tangzy
 * @Title: RandomVerifyCodeUtil
 * @Version 1.0
 * @Description: 图片验证码生成
 * @Date 2019/4/4 19:30
 */
public class RandomVerifyCodeUtil {

    /**
     * 图片验证码cookie存储key前缀
     */
    public static final String VERIFY_CODE_COOKIE_KEY="pck_";
    /**
     * 图片的宽度。
     */
    private int width = 160;
    /**
     * 图片的高度。
     */
    private int height = 40;
    /**
     * 验证码字符个数
     */
    private int codeCount = 4;
    /**
     * 验证码干扰线数
     */
    private int lineCount = 20;
    /**
     * 验证码
     */
    private String code = null;
    /**
     * 验证码图片Buffer
     */
    private BufferedImage buffImg = null;
    /**
     * 验证码图片cookie信息
     */
    private Cookie cookie=null;
    /**
     * 错误信息
     */
    private String errString = "";

    Random random = new Random();

    private String[] fontNames = {"Georgia"};
    /**
     * 验证码数组
     */
    private String codes = "23456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";

    /**
     * 生成默认定义的图片
     */
    public RandomVerifyCodeUtil() {
    }

//    /**
//     * 自定义图片宽和高
//     */
//    public RandomVerifyCodeUtil(int width, int height) {
//        this.width = width;
//        this.height = height;
//        creatImage();
//    }
//
//    /**
//     * 自定义图片宽、高和字符个数
//     */
//    public RandomVerifyCodeUtil(int width, int height, int codeCount) {
//        this.width = width;
//        this.height = height;
//        this.codeCount = codeCount;
//        creatImage();
//    }
//
//    /**
//     * 自定义宽、高、字符个数和干扰线条数
//     */
//    public RandomVerifyCodeUtil(int width, int height, int codeCount, int lineCount) {
//        this.width = width;
//        this.height = height;
//        this.codeCount = codeCount;
//        this.lineCount = lineCount;
//        creatImage();
//    }

    /* 获取随机的颜色
     *
     * @return
     */
    private Color randomColor() {
        int r = this.random.nextInt(225);  //这里为什么是225，因为当r，g，b都为255时，即为白色，为了好辨认，需要颜色深一点。
        int g = this.random.nextInt(225);
        int b = this.random.nextInt(225);
        return new Color(r, g, b);            //返回一个随机颜色
    }

    /**
     * 获取随机字体
     *
     * @return
     */
    private Font randomFont() {
        int index = random.nextInt(fontNames.length);  //获取随机的字体
        String fontName = fontNames[index];
        int style = random.nextInt(4);         //随机获取字体的样式，0是无样式，1是加粗，2是斜体，3是加粗加斜体
        int size = random.nextInt(10) + 24;    //随机获取字体的大小
        return new Font(fontName, style, size);   //返回一个随机的字体
    }

    /**
     * 获取随机字符
     *
     * @return
     */
    private char randomChar() {
        int index = random.nextInt(codes.length());
        return codes.charAt(index);
    }

    /**
     * 画干扰线，验证码干扰线用来防止计算机解析图片
     *
     * @param image
     */
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
     *
     * @return
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
     *
     */
    public void creatImage() {
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

    public void write(OutputStream sos) throws IOException {
        ImageIO.write(buffImg, "png", sos);
        sos.close();
    }

    public BufferedImage getBuffImg() {
        return buffImg;
    }

    public String getCode() {
        return code.toLowerCase();
    }
    public Cookie getCookie() {
        return cookie;
    }

    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    public String getErrString() {
        return errString;
    }

    public void setErrString(String errString) {
        this.errString = errString;
    }

    @Test
    public RandomVerifyCodeUtil genVerifyCode(){
        RandomVerifyCodeUtil verifyCode = new RandomVerifyCodeUtil();
        verifyCode.creatImage();
        return verifyCode;
    }

}
