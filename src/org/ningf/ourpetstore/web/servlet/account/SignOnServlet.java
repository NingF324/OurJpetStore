package org.ningf.ourpetstore.web.servlet.account;

import org.ningf.ourpetstore.domain.Account;
import org.ningf.ourpetstore.domain.Product;
import org.ningf.ourpetstore.service.AccountService;
import org.ningf.ourpetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/6 22:00
 */
public class SignOnServlet extends HttpServlet {
    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/signon.jsp";
    private String username;
    private String password;
    private String signOnMsg;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.username=req.getParameter("username");
        this.password=req.getParameter("password");
        String validationCode = req.getParameter("validation_code");
        //获取用户输入验证码
        if(!checkValidationCode(req, validationCode)){
            this.signOnMsg="The verification code is incorrect";
            req.setAttribute("signOnMsg",this.signOnMsg);
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req,resp);
        }
        if(!validate()){
            req.setAttribute("signOnMsg",this.signOnMsg);
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req,resp);
        }else{
            AccountService accountService=new AccountService();
            Account loginAccount = accountService.getAccount(username, password);
            if(loginAccount==null){
                this.signOnMsg="Wrong username or password";
                req.setAttribute("signOnMsg",this.signOnMsg);
                req.getRequestDispatcher(SIGN_ON_FORM).forward(req,resp);
            }else {
                loginAccount.setPassword(null);
                HttpSession session= req.getSession();
                session.setAttribute("loginAccount",loginAccount);
                if(loginAccount.isListOption()){
                    CatalogService catalogService=new CatalogService();
                    List<Product> myList = catalogService.getProductListByCategory(loginAccount.getFavouriteCategoryId());
                    session.setAttribute("myList",myList);
                }
                resp.sendRedirect("mainForm");
            }

        }
    }

    //判断账号密码，可用正则表达式进行修改
    private boolean validate(){
        if(this.username==null|| this.username.isEmpty()){
            this.signOnMsg="The username cannot be empty";
            return false;
        }
        if(this.password==null|| this.password.isEmpty()){
            this.signOnMsg="The password cannot be empty";
            return false;
        }
        return true;
    }

    //核对用户验证码是否合法
    protected boolean checkValidationCode(HttpServletRequest request,String validationCode){
//从httpSession对象中获取验证码
        String validationCodeSession = (String)request.getSession().getAttribute("validation_code");
//如果validationCodeSession==null说明验证码过期，要刷新后重新获得验证码
        if(validationCodeSession==null){
//result.jsp需要的结果信息
            request.setAttribute("info", "验证码过期");
            return false;
        }
//验证验证码是否正确
        if(!validationCode.equalsIgnoreCase(validationCodeSession)){
//result.jsp需要的结果信息
            request.setAttribute("info", "验证码不正确");
            return false;
        }
        return true;
    }
}
