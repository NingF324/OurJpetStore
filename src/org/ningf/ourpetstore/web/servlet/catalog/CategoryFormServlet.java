package org.ningf.ourpetstore.web.servlet.catalog;

import org.ningf.ourpetstore.domain.Account;
import org.ningf.ourpetstore.domain.Category;
import org.ningf.ourpetstore.domain.Product;
import org.ningf.ourpetstore.service.CatalogService;
import org.ningf.ourpetstore.service.LogService;

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
 * @time: 2023/11/5 20:33
 */
public class CategoryFormServlet extends HttpServlet {
    private static final String CATEGORY_FORM = "/WEB-INF/jsp/catalog/category.jsp";
    private CatalogService catalogService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId=req.getParameter("categoryId");
        catalogService=new CatalogService();
        Category category = catalogService.getCategory(categoryId);
        List<Product> productListByCategory = catalogService.getProductListByCategory(categoryId);
        HttpSession httpSession=req.getSession();
        httpSession.setAttribute("category",category);
        httpSession.setAttribute("productList",productListByCategory);
        Account account = (Account)httpSession.getAttribute("loginAccount");
        if(account != null){
            String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath() + req.getServletPath() + "?" + (req.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " Jump to product categories " + category;
            logService.insertLogInfo(account.getUsername(), logInfo);
        }
        req.getRequestDispatcher(CATEGORY_FORM).forward(req,resp);

    }
}
