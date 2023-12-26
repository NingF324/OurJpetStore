package org.ningf.ourpetstore.web.servlet.cart;

import com.alibaba.fastjson.JSON;
import org.ningf.ourpetstore.domain.Account;
import org.ningf.ourpetstore.domain.CartLineItem;
import org.ningf.ourpetstore.domain.Item;
import org.ningf.ourpetstore.service.CartService;
import org.ningf.ourpetstore.service.CatalogService;
import org.ningf.ourpetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/12/26 8:46
 */
public class CartAutoUpdateServlet extends HttpServlet {
    private ArrayList<String> itemsToRemove=new ArrayList<>();
    private CartService cartService=new CartService();

    private CartLineItem cartLineItem=new CartLineItem();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String quantityString=req.getParameter("quantity");
        int quantity = Integer.parseInt(quantityString);
        String itemId=req.getParameter("itemId");
        HttpSession session= req.getSession();
        Account account = (Account) req.getSession().getAttribute("loginAccount");
        CatalogService catalogService = new CatalogService();
        if(quantity==0){
            cartService.removeCartLineItem(account.getUsername(),itemId);
            String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath() + req.getServletPath() + "?" + (req.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " 从购物车移除商品：" + itemId;
            logService.insertLogInfo(account.getUsername(), logInfo);
            //将cartLineItem中的UnitPrice通过Json输出
            String result = JSON.toJSONString("0");
            resp.setContentType("text/json");
            PrintWriter out =resp.getWriter();
            out.println(result);
        }else{
            Item item = catalogService.getItem(itemId);
            cartLineItem.setUserId(account.getUsername());
            cartLineItem.setItemId(itemId);
            cartLineItem.setQuantity(quantity);
            cartLineItem.setUnitPrice(item.getListPrice().multiply(BigDecimal.valueOf(quantity)));
            cartLineItem.setProductId(item.getProductId());
            cartLineItem.setDescription(item.getAttribute1() + " " + item.getProduct().getName());
            cartLineItem.setListPrice(item.getListPrice());

            cartService.updateCartLineItem(cartLineItem);

            //将cartLineItem中的UnitPrice通过Json输出
            String result = JSON.toJSONString(cartLineItem.getUnitPrice());
            resp.setContentType("text/json");
            PrintWriter out =resp.getWriter();
            out.println(result);


            String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath() + req.getServletPath() + "?" + (req.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " Update the number of items in the cart";
            logService.insertLogInfo(account.getUsername(), logInfo);
        }
    }
}
