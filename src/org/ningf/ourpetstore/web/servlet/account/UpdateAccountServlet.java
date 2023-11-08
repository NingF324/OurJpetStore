package org.ningf.ourpetstore.web.servlet.account;

import org.ningf.ourpetstore.domain.Account;
import org.ningf.ourpetstore.service.AccountService;
import org.ningf.ourpetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/7 12:36
 */
public class UpdateAccountServlet extends HttpServlet {
    private static final String MY_ACCOUNT_FORM = "/WEB-INF/jsp/account/updateaccount.jsp";

    AccountService accountService;

    public UpdateAccountServlet(){
        this.accountService= new AccountService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("password")==null|| req.getParameter("password").isEmpty()){
            req.setAttribute("errorMsg","Blank Password Detected");
            req.getRequestDispatcher(MY_ACCOUNT_FORM).forward(req,resp);
        }else if(!req.getParameter("password").equals(req.getParameter("repeatedPassword"))){
            req.setAttribute("errorMsg","The Two Inputs Are Inconsistent");
            req.getRequestDispatcher(MY_ACCOUNT_FORM).forward(req,resp);
        }else {
            Account account = (Account) req.getSession().getAttribute("loginAccount");
            account.setPassword(req.getParameter("password"));
            account.setFirstName(req.getParameter("account.firstName"));
            account.setLastName(req.getParameter("account.lastName"));
            account.setEmail(req.getParameter("account.email"));
            account.setPhone(req.getParameter("account.phone"));
            account.setAddress1(req.getParameter("account.address1"));
            account.setAddress2(req.getParameter("account.address2"));
            account.setCity(req.getParameter("account.city"));
            account.setState(req.getParameter("account.state"));
            account.setZip(req.getParameter("account.zip"));
            account.setCountry(req.getParameter("account.country"));

            account.setLanguagePreference(req.getParameter("account.languagePreference"));
            account.setFavouriteCategoryId(req.getParameter("account.favouriteCategoryId"));

            if(req.getParameter("account.listOption")!=null){
                account.setListOption(true);
            }
            if(req.getParameter("account.bannerOption")!=null){
                account.setBannerOption(true);
            }

            String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath() + req.getServletPath() + "?" + (req.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " Jump to the Edit Account Information page";
            logService.insertLogInfo(account.getUsername(), logInfo);
            accountService.updateAccount(account);
            req.getRequestDispatcher(MY_ACCOUNT_FORM).forward(req,resp);
        }
    }
}
