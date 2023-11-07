package org.ningf.ourpetstore.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import org.ningf.ourpetstore.domain.Product;
import org.ningf.ourpetstore.service.CatalogService;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/6 23:28
 */
public class SearchProductsFormServlet extends HttpServlet {
    private static final String SEARCH_PRODUCTS_FORM = "/WEB-INF/jsp/catalog/searchProducts.jsp";
    private static final String ERROR_FORM = "/WEB-INF/jsp/common/error.jsp";
    private String keyword;
    private CatalogService catalogService=new CatalogService();
    private List<Product> productList;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.keyword=req.getParameter("keyword");
        HttpSession session= req.getSession();
        if (keyword == null||keyword.isEmpty()) {
            session.setAttribute("errorMsg","Please enter a keyword to search for, then press the search button.");
            req.getRequestDispatcher(ERROR_FORM).forward(req,resp);
        } else {
            productList = catalogService.searchProductList(keyword.toLowerCase());
            session.setAttribute("productList",productList);
            req.getRequestDispatcher(SEARCH_PRODUCTS_FORM).forward(req,resp);
        }
    }
}
