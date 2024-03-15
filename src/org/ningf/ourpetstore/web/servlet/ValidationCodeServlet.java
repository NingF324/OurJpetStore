package org.ningf.ourpetstore.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/7 8:18
 */
public class ValidationCodeServlet extends HttpServlet {
    //图形验证码的字符集
    private static String codeChars = "%#23456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //返回一个随机颜色
    private static Color getRandomColor(int minColor, int maxColor){
        Random random = new Random();
        if(minColor>255){
            minColor=255;
        }
        if(maxColor>255){
            maxColor=255;
        }
        int red = minColor +random.nextInt(maxColor-minColor);
        int green = minColor +random.nextInt(maxColor-minColor);
        int blue = minColor +random.nextInt(maxColor-minColor);
        return new Color(red,green,blue);
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取验证码集合的长度
        int charLength = codeChars.length();
        //关闭浏览器的缓冲区
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //设置图像验证码的长和宽
        int width = 90,height = 20;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(getRandomColor(180,250));//随机设置要填充的颜色
        g.fillRect(0, 0, width, height);
        //设置初始字体
        g.setFont(new Font("Times New Roman",Font.ITALIC,height));
        g.setColor(getRandomColor(180,250));
        StringBuilder validationCode = new StringBuilder();
        String[] fontNames = {"Times New Roman","Book antiqua","Arial"};
        //随机生成3~5个验证码
        for (int i = 0; i < 3+random.nextInt(3); i++) {
            //随机设置当前验证码字符的字体
            g.setFont(new Font(fontNames[random.nextInt(3)],Font.ITALIC,height));
            char codeChar = codeChars.charAt(random.nextInt(charLength));
            validationCode.append(codeChar);
            g.setColor(getRandomColor(10,100));//随机设置字体颜色
            //在图形上输出验证码字符，x和y都是随机生成的
            g.drawString(String.valueOf(codeChar), 16*i+random.nextInt(7), height-random.nextInt(6));
        }

        HttpSession session = request.getSession();
        session.setAttribute("validation_code", validationCode.toString());
        //设置5分钟失效
        session.setMaxInactiveInterval(5*60);
        g.dispose();
        OutputStream os = response.getOutputStream();
        //以JPEG格式向客户端发送图形验证码
        ImageIO.write(image, "JPEG", os);
    }
}
