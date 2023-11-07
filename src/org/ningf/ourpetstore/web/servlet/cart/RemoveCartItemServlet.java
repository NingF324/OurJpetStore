package org.ningf.ourpetstore.web.servlet.cart;

import org.ningf.ourpetstore.domain.Account;
import org.ningf.ourpetstore.domain.Cart;
import org.ningf.ourpetstore.domain.CartLineItem;
import org.ningf.ourpetstore.domain.Item;
import org.ningf.ourpetstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/6 17:14
 */
public class RemoveCartItemServlet extends HttpServlet {
    private static final String CART_FORM = "/OurJpetStore/cartForm";
    private static final String ERROR_FORM = "/WEB-INF/jsp/common/error.jsp";
    private CartService cartService=new CartService();
    private CartLineItem cartLineItem=new CartLineItem();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session= req.getSession();
        Cart cart =(Cart) session.getAttribute("cart");
        String workingItemId =req.getParameter("workingItemId");
        Item item = cart.removeItemById(workingItemId);

        if (item == null) {
            session.setAttribute("errorMsg","Attempted to remove null CartItem from Cart.");
            req.getRequestDispatcher(ERROR_FORM).forward(req,resp);
        } else {
            /*req.getRequestDispatcher(CART_FORM).forward(req,resp);*/
            Account account = (Account) req.getSession().getAttribute("loginAccount");
            cartService.removeCartLineItem(account.getUsername(),workingItemId);
            resp.sendRedirect(CART_FORM);
        }
    }
}
