package org.ningf.ourpetstore.web.servlet.catalog;

import org.ningf.ourpetstore.domain.Product;
import org.ningf.ourpetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.alibaba.fastjson.JSON;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/12/21 15:39
 */
public class ProductAutoCompleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword =req.getParameter("keyword");
        CatalogService service=new CatalogService();
        List<Product> searchedProductList = service.searchProductList(keyword);

        //使用ajax技术
        String result = JSON.toJSONString(searchedProductList);
        System.out.println(result);
        resp.setContentType("text/json");
        PrintWriter out =resp.getWriter();
        out.println(result);
    }
}
