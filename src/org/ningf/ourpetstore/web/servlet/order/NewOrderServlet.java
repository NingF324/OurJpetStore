package org.ningf.ourpetstore.web.servlet.order;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/7 16:05
 */

import org.ningf.ourpetstore.domain.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewOrderServlet extends HttpServlet {
    private static final String SHIPPING_FORM = "/WEB-INF/jsp/order/shipping.jsp";
    private static final String CONFIRM = "confirm";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ship = req.getParameter("Ship");
        Order order = (Order) req.getSession().getAttribute("order");

        if(ship==null|| ship.isEmpty()){
            req.getRequestDispatcher(CONFIRM).forward(req,resp);
        }else{
            req.getRequestDispatcher(SHIPPING_FORM).forward(req,resp);
        }
    }
}
