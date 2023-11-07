package org.ningf.ourpetstore.web.servlet.cart;

import org.ningf.ourpetstore.domain.Account;
import org.ningf.ourpetstore.domain.Cart;
import org.ningf.ourpetstore.domain.CartLineItem;
import org.ningf.ourpetstore.domain.Item;
import org.ningf.ourpetstore.service.CartService;
import org.ningf.ourpetstore.service.CatalogService;

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
 * @time: 2023/11/6 16:27
 */
public class AddItemToCartServlet extends HttpServlet {
    private static final String CART_FORM = "/OurJpetStore/cartForm";
    private CartService cartService=new CartService();
    private CartLineItem cartLineItem=new CartLineItem();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String workingItemId=req.getParameter("workingItemId");

        HttpSession session=req.getSession();
        Cart cart=(Cart) session.getAttribute("cart");

        if(cart==null){
            cart=new Cart();
        }

        if (cart.containsItemId(workingItemId)) {
            CatalogService catalogService=new CatalogService();
            cart.incrementQuantityByItemId(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            Account account = (Account) req.getSession().getAttribute("loginAccount");
            int quantity=cartService.getQuantityByItemIdAndUserId(account.getUsername(),workingItemId);
            cartLineItem.setUserId(account.getUsername());
            cartLineItem.setItemId(workingItemId);
            cartLineItem.setQuantity(quantity+1);
            cartLineItem.setUnitPrice(item.getListPrice().multiply(BigDecimal.valueOf(quantity+1)));
            cartLineItem.setProductId(item.getProductId());
            cartLineItem.setDescription(item.getAttribute1()+" "+item.getProduct().getName());
            cartLineItem.setListPrice(item.getListPrice());
            cartService.updateCartLineItem(cartLineItem);
        } else {
            // isInStock is a "real-time" property that must be updated
            // every time an item is added to the cart, even if other
            // item details are cached.
            CatalogService catalogService=new CatalogService();
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cart.addItem(item, isInStock);
            Account account = (Account) req.getSession().getAttribute("loginAccount");
            cartLineItem.setUserId(account.getUsername());
            cartLineItem.setItemId(workingItemId);
            cartLineItem.setQuantity(1);
            cartLineItem.setUnitPrice(item.getListPrice());
            cartLineItem.setProductId(item.getProductId());
            cartLineItem.setDescription(item.getAttribute1()+" "+item.getProduct().getName());
            cartLineItem.setListPrice(item.getListPrice());
            cartService.insertCartLineItem(cartLineItem);
        }

        session.setAttribute("cart",cart);
        /*req.getRequestDispatcher(CART_FORM).forward(req,resp);*/
        resp.sendRedirect(CART_FORM);
    }
}