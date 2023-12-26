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
import java.util.ArrayList;
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
    private ArrayList<String> itemsToRemove=new ArrayList<>();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session= req.getSession();
        Cart cart =(Cart) session.getAttribute("cart");
        Iterator<CartItem> cartItemIterator= cart.getAllCartItems();
        Account account = (Account) req.getSession().getAttribute("loginAccount");
        while (cartItemIterator.hasNext()) {
            CartItem cartItem = cartItemIterator.next();
            String itemId = cartItem.getItem().getItemId();
            try {
                CatalogService catalogService = new CatalogService();
                String quantityString = req.getParameter(itemId);
                Item item = catalogService.getItem(itemId);
                int quantity = Integer.parseInt(quantityString);


                if (quantity == 0) {
                    // 从购物车中移除商品，并更新相应的 CartLineItem
                    itemsToRemove.add(itemId);
                    String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                            + req.getContextPath() + req.getServletPath() + "?" + (req.getQueryString());

                    LogService logService = new LogService();
                    String logInfo = logService.logInfo(" ") + strBackUrl + " 从购物车移除商品：" + itemId;
                    logService.insertLogInfo(account.getUsername(), logInfo);

                    cartItemIterator.remove(); // 从迭代器中移除购物车商品
                } else {
                    cart.setQuantityByItemId(itemId, quantity);

                    cartLineItem.setUserId(account.getUsername());
                    cartLineItem.setItemId(itemId);
                    cartLineItem.setQuantity(quantity);
                    cartLineItem.setUnitPrice(item.getListPrice().multiply(BigDecimal.valueOf(quantity)));
                    cartLineItem.setProductId(item.getProductId());
                    cartLineItem.setDescription(item.getAttribute1() + " " + item.getProduct().getName());
                    cartLineItem.setListPrice(item.getListPrice());

                    cartService.updateCartLineItem(cartLineItem);
                    String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                            + req.getContextPath() + req.getServletPath() + "?" + (req.getQueryString());

                    LogService logService = new LogService();
                    String logInfo = logService.logInfo(" ") + strBackUrl + " Update the number of items in the cart";
                    logService.insertLogInfo(account.getUsername(), logInfo);
                }
            } catch (Exception e) {
                // 忽略解析异常
            }
        }
        for (String itemIdToRemove : itemsToRemove) {
            cart.removeItemById(itemIdToRemove);
            cartService.removeCartLineItem(account.getUsername(), itemIdToRemove);
            // 记录日志等其他操作
        }
        /*req.getRequestDispatcher(CART_FORM).forward(req,resp);*/
        resp.sendRedirect(CART_FORM);
    }
}
