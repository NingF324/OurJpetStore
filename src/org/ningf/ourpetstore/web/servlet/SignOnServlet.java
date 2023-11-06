package org.ningf.ourpetstore.web.servlet;

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

        if(!validate()){
            req.setAttribute("signOnMsg",this.signOnMsg);
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req,resp);
        }else{
            AccountService accountService=new AccountService();
            Account loginAccount = accountService.getAccount(username, password);
            if(loginAccount==null){
                this.signOnMsg="用户名或密码错误";
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
            this.signOnMsg="用户名不能为空";
            return false;
        }
        if(this.password==null|| this.password.isEmpty()){
            this.signOnMsg="密码不能为空";
            return false;
        }
        return true;
    }
}
