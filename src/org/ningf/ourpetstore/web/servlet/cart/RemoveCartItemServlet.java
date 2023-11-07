package org.ningf.ourpetstore.web.servlet.cart;

import org.ningf.ourpetstore.domain.Cart;
import org.ningf.ourpetstore.domain.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/6 17:14
 */
public class RemoveCartItemServlet extends HttpServlet {
    private static final String CART_FORM = "/OurJpetStore/cartForm";
    private static final String ERROR_FORM = "/WEB-INF/jsp/common/error.jsp";
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
            resp.sendRedirect(CART_FORM);
        }
    }
}
