package org.ningf.ourpetstore.web.servlet.catalog;

import org.ningf.ourpetstore.domain.Item;
import org.ningf.ourpetstore.domain.Product;
import org.ningf.ourpetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/5 21:20
 */
public class ProductFormServlet extends HttpServlet {
    private static final String PRODUCT_FORM = "/WEB-INF/jsp/catalog/product.jsp";
    private CatalogService catalogService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId=req.getParameter("productId");
        catalogService=new CatalogService();
        Product product = catalogService.getProduct(productId);
        List<Item> itemList = catalogService.getItemListByProduct(productId);
        HttpSession httpSession=req.getSession();
        httpSession.setAttribute("product",product);
        httpSession.setAttribute("itemList",itemList);
        req.getRequestDispatcher(PRODUCT_FORM).forward(req,resp);
    }
}
