package org.ningf.ourpetstore.web.servlet;

import org.ningf.ourpetstore.domain.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/6 21:53
 */
public class NewOrderFormServlet extends HttpServlet {

    private static final String NEW_ORDER_FORM = "/WEB-INF/jsp/order/newOrder.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        Account loginAccount=(Account) session.getAttribute("loginAccount");
        if(loginAccount==null){
            resp.sendRedirect("signOnForm");
        }else{
            req.getRequestDispatcher(NEW_ORDER_FORM).forward(req,resp);
        }
    }
}
