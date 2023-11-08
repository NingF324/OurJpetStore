package org.ningf.ourpetstore.web.servlet.account;

import org.apache.log4j.Logger;
import org.ningf.ourpetstore.domain.Account;
import org.ningf.ourpetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/7 14:32
 */
public class SignOutServlet extends HttpServlet {
    private static final String MAIN_FORM = "/WEB-INF/jsp/catalog/main.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account loginAccount = (Account)session.getAttribute("loginAccount");
        if(loginAccount != null){
            String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath() + req.getServletPath() + "?" + (req.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " Log out to return to the main interface";
            logService.insertLogInfo(loginAccount.getUsername(), logInfo);
        }
        session.removeAttribute("loginAccount");
        req.getRequestDispatcher(MAIN_FORM).forward(req,resp);
    }
}
