package org.ningf.ourpetstore.web.servlet;

import org.ningf.ourpetstore.domain.Cart;
import org.ningf.ourpetstore.domain.CartItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/6 16:52
 */
public class UpdateCartServlet extends HttpServlet {
    private static final String CART_FORM = "/OurJpetStore/cartForm";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session= req.getSession();
        Cart cart =(Cart) session.getAttribute("cart");
        Iterator<CartItem> cartItemIterator= cart.getAllCartItems();
        while (cartItemIterator.hasNext()) {
            CartItem cartItem = (CartItem) cartItemIterator.next();
            String itemId = cartItem.getItem().getItemId();
            try {
                String quantityString=req.getParameter(itemId);
                int quantity=Integer.parseInt(quantityString);
                cart.setQuantityByItemId(itemId, quantity);
                if (quantity < 1) {
                    cartItemIterator.remove();
                }
            } catch (Exception e) {
                //ignore parse exceptions on purpose
            }
        }
        /*req.getRequestDispatcher(CART_FORM).forward(req,resp);*/
        resp.sendRedirect(CART_FORM);
    }
}
