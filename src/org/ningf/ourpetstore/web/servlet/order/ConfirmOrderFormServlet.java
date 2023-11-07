package org.ningf.ourpetstore.web.servlet.order;

import org.ningf.ourpetstore.domain.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/7 16:14
 */
public class ConfirmOrderFormServlet extends HttpServlet {
    private static final String CONFIRM_FORM = "/WEB-INF/jsp/order/confirmorder.jsp";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean shipping = req.getParameter("shipping")!=null;
        Order order = (Order) req.getSession().getAttribute("order");
        if(shipping){
            order.setShipToFirstName(req.getParameter("shipToFirstName"));
            order.setShipToLastName(req.getParameter("shipToLastName"));
            order.setShipAddress1(req.getParameter("shipAddress1"));
            order.setShipAddress2(req.getParameter("shipAddress2"));
            order.setShipCity(req.getParameter("shipCity"));
            order.setShipState(req.getParameter("shipState"));
            order.setShipZip(req.getParameter("shipZip"));
            order.setShipCountry(req.getParameter("shipCountry"));
        }
        req.getRequestDispatcher(CONFIRM_FORM).forward(req,resp);
    }
}
