package org.ningf.ourpetstore.web.servlet;

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
public class UpdateAccountFormServlet extends HttpServlet {
    private static final String MY_ACCOUNT_FORM = "/WEB-INF/jsp/account/updateaccount.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(MY_ACCOUNT_FORM).forward(req,resp);
    }
}
