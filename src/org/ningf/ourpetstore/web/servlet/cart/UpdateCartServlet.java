package org.ningf.ourpetstore.web.servlet.cart;

import org.ningf.ourpetstore.domain.*;
import org.ningf.ourpetstore.service.CartService;
import org.ningf.ourpetstore.service.CatalogService;
import org.ningf.ourpetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/6 16:52
 */
public class UpdateCartServlet extends HttpServlet {
    private static final String CART_FORM = "/OurJpetStore/cartForm";
    private CartService cartService=new CartService();
    private CartLineItem cartLineItem=new CartLineItem();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session= req.getSession();
        Cart cart =(Cart) session.getAttribute("cart");
        Iterator<CartItem> cartItemIterator= cart.getAllCartItems();
        while (cartItemIterator.hasNext()) {
            CartItem cartItem = cartItemIterator.next();
            String itemId = cartItem.getItem().getItemId();
            try {
                CatalogService catalogService=new CatalogService();
                String quantityString=req.getParameter(itemId);
                Item item = catalogService.getItem(itemId);
                int quantity=Integer.parseInt(quantityString);
                cart.setQuantityByItemId(itemId, quantity);
                Account account = (Account) req.getSession().getAttribute("loginAccount");
                cartLineItem.setUserId(account.getUsername());
                cartLineItem.setItemId(itemId);
                cartLineItem.setQuantity(quantity);
                cartLineItem.setUnitPrice(item.getListPrice().multiply(BigDecimal.valueOf(quantity)));
                cartLineItem.setProductId(item.getProductId());
                cartLineItem.setDescription(item.getAttribute1()+" "+item.getProduct().getName());
                cartLineItem.setListPrice(item.getListPrice());
                cartService.updateCartLineItem(cartLineItem);
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + req.getContextPath() + req.getServletPath() + "?" + (req.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " Update the number of items in the cart";
                logService.insertLogInfo(account.getUsername(), logInfo);
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
