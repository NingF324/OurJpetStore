package org.ningf.ourpetstore.web.servlet;

import org.ningf.ourpetstore.domain.Cart;
import org.ningf.ourpetstore.domain.Item;
import org.ningf.ourpetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/6 16:27
 */
public class AddItemToCartServlet extends HttpServlet {
    private static final String CART_FORM = "/OurJpetStore/cartForm";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String workingItemId=req.getParameter("workingItemId");

        HttpSession session=req.getSession();
        Cart cart=(Cart) session.getAttribute("cart");

        if(cart==null){
            cart=new Cart();
        }

        if (cart.containsItemId(workingItemId)) {
            cart.incrementQuantityByItemId(workingItemId);
        } else {
            // isInStock is a "real-time" property that must be updated
            // every time an item is added to the cart, even if other
            // item details are cached.
            CatalogService catalogService=new CatalogService();
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cart.addItem(item, isInStock);
        }

        session.setAttribute("cart",cart);
        /*req.getRequestDispatcher(CART_FORM).forward(req,resp);*/
        resp.sendRedirect(CART_FORM);
    }
}