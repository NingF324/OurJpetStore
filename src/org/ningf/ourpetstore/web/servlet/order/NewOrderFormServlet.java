package org.ningf.ourpetstore.web.servlet.order;

import org.ningf.ourpetstore.domain.Account;
import org.ningf.ourpetstore.domain.Cart;
import org.ningf.ourpetstore.domain.Order;

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
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Account account = (Account) session.getAttribute("loginAccount");

        Order order = new Order();
        order.initOrder(account, cart);
        session.setAttribute("order", order);

        req.getRequestDispatcher(NEW_ORDER_FORM).forward(req, resp);
    }
}
