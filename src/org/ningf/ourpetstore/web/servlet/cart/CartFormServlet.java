package org.ningf.ourpetstore.web.servlet.cart;

import org.ningf.ourpetstore.domain.Account;
import org.ningf.ourpetstore.domain.Cart;
import org.ningf.ourpetstore.domain.CartLineItem;
import org.ningf.ourpetstore.domain.Order;
import org.ningf.ourpetstore.service.CartService;
import org.ningf.ourpetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/6 17:26
 */
public class CartFormServlet extends HttpServlet {
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";
    private CartService cartService=new CartService();
    private List<CartLineItem> cartLineItems;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) req.getSession().getAttribute("loginAccount");
        cartLineItems=cartService.getCartLineItem(account.getUsername());
        //将cartLienItems存入sessionScope.cart.cartItems
        session.setAttribute("cartLineItems",cartLineItems);
        String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath() + req.getServletPath() + "?" + (req.getQueryString());

        LogService logService = new LogService();
        String logInfo = logService.logInfo(" ") + strBackUrl + "  Views the shopping cart";
        logService.insertLogInfo(account.getUsername(), logInfo);
        req.getRequestDispatcher(CART_FORM).forward(req,resp);
    }
}
