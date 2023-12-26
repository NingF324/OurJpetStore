package org.ningf.ourpetstore.web.servlet.account;

import com.alibaba.fastjson.JSON;
import org.ningf.ourpetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/12/22 15:11
 */
public class IsUserNameExistServlet extends HttpServlet {
    private AccountService accountService=new AccountService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        boolean userNameExist = accountService.isUserNameExist(username);

        String result= JSON.toJSONString(userNameExist);
        resp.setContentType("text/json");
        PrintWriter out =resp.getWriter();
        out.println(result);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        boolean userNameExist = accountService.isUserNameExist(username);

        String result= JSON.toJSONString(userNameExist);
        resp.setContentType("text/json");
        PrintWriter out =resp.getWriter();
        out.println(result);
    }
}
