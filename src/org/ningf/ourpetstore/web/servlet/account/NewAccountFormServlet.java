package org.ningf.ourpetstore.web.servlet.account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/7 9:01
 */
public class NewAccountFormServlet extends HttpServlet {
    private static final String NEW_ACCOUNT_FORM = "/WEB-INF/jsp/account/newaccount.jsp";
    private static final List<String> LANGUAGE_LIST;
    private static final List<String> CATEGORY_LIST;
    static {
        LANGUAGE_LIST = List.of("english", "japanese");

        CATEGORY_LIST = List.of("FISH", "DOGS", "REPTILES", "CATS", "BIRDS");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        session.setAttribute("languages",LANGUAGE_LIST);
        session.setAttribute("categories",CATEGORY_LIST);
        req.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(req,resp);
    }
}
