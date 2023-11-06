package org.ningf.ourpetstore.web.servlet;

import org.ningf.ourpetstore.domain.Item;
import org.ningf.ourpetstore.domain.Product;
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
 * @time: 2023/11/6 15:19
 */
public class ItemFormServlet extends HttpServlet {
    private static final String ITEM_FORM = "/WEB-INF/jsp/catalog/item.jsp";
    private CatalogService catalogService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemId=req.getParameter("itemId");
        catalogService=new CatalogService();
        Item item=catalogService.getItem(itemId);
        Product product=item.getProduct();
        HttpSession session= req.getSession();
        session.setAttribute("product",product);
        session.setAttribute("item",item);
        req.getRequestDispatcher(ITEM_FORM).forward(req,resp);
    }
}
