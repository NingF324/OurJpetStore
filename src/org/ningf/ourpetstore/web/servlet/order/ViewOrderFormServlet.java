package org.ningf.ourpetstore.web.servlet.order;

import org.ningf.ourpetstore.domain.Account;
import org.ningf.ourpetstore.domain.Cart;
import org.ningf.ourpetstore.domain.Order;
import org.ningf.ourpetstore.service.LogService;
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
        Order order=new Order();
        String orderId = req.getParameter("orderId");
        Account account= (Account) req.getSession().getAttribute("loginAccount");
        if(account != null){
            String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath() + req.getServletPath() + "?" + (req.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " View the order " + order;
            logService.insertLogInfo(account.getUsername(), logInfo);
        }
        if(orderId==null|| orderId.isEmpty()){//从购买来
            order = (Order) session.getAttribute("order");
            orderService.insertOrder(order);
            Cart cart = new Cart();
            session.setAttribute("cart",cart);
            req.getRequestDispatcher(VIEW_ORDER).forward(req,resp);
        }else {
            order = orderService.getOrder(Integer.parseInt(orderId));
            session.setAttribute("order",order);
            req.getRequestDispatcher(VIEW_ORDER).forward(req,resp);
        }
    }
}
