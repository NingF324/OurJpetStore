package org.ningf.ourpetstore.web.servlet.order;

import org.ningf.ourpetstore.domain.Cart;
import org.ningf.ourpetstore.domain.Order;
import org.ningf.ourpetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/7 16:15
 */
public class ViewOrderFormServlet extends HttpServlet {
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/vieworder.jsp";

    private OrderService orderService;

    public ViewOrderFormServlet(){
        this.orderService = new OrderService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Order order;

        String orderId = req.getParameter("orderId");
        if(orderId==null|| orderId.isEmpty()){//如果是从确认来的，则从session中取出order，入数据库
            order = (Order) session.getAttribute("order");
            orderService.insertOrder(order);
            Cart cart = new Cart();
            session.setAttribute("cart",cart);
            req.getRequestDispatcher(VIEW_ORDER).forward(req,resp);
        }else {//如果是从Orders来的，则从数据库中取出order，入session
            order = orderService.getOrder(Integer.parseInt(orderId));
            session.setAttribute("order",order);
            req.getRequestDispatcher(VIEW_ORDER).forward(req,resp);
        }
    }
}
