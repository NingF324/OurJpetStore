package org.ningf.ourpetstore.web.servlet.account;

import org.ningf.ourpetstore.domain.Account;
import org.ningf.ourpetstore.domain.Product;
import org.ningf.ourpetstore.service.AccountService;
import org.ningf.ourpetstore.service.CatalogService;
import org.ningf.ourpetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/7 9:02
 */
public class NewAccountServlet extends HttpServlet {
    private static final String NEW_ACCOUNT_FORM = "/WEB-INF/jsp/account/newaccount.jsp";
    private AccountService accountService=new AccountService();
    private CatalogService catalogService=new CatalogService();
    private Account account=new Account();
    private List<Product> myList;
    private String username;
    private String password;
    private String repeatedPassword;
    private String email;
    private String firstName;
    private String lastName;
    private String status="OK";
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
    private String favouriteCategoryId;
    private String languagePreference;
    private boolean listOption;
    private boolean bannerOption;

    private String signOnMsg;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String validationCode = req.getParameter("validation_code");
        if(!checkValidationCode(req, validationCode)){
            this.signOnMsg="The verification code is incorrect";
            req.setAttribute("signOnMsg",this.signOnMsg);
            req.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(req,resp);
        } else if(!validate(req)){
            req.setAttribute("signOnMsg",this.signOnMsg);
            req.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(req,resp);
        }else {
            this.username = req.getParameter("username");
            this.password = req.getParameter("password");
            this.repeatedPassword=req.getParameter("repeatedPassword");
            this.email = req.getParameter("account.email");
            this.firstName = req.getParameter("account.firstName");
            this.lastName = req.getParameter("account.lastName");
            this.address1 = req.getParameter("account.address1");
            this.address2 = req.getParameter("account.address2");
            this.city = req.getParameter("account.city");
            this.state = req.getParameter("account.state");
            this.zip = req.getParameter("account.zip");
            this.country = req.getParameter("account.country");
            this.phone = req.getParameter("account.phone");
            this.favouriteCategoryId = req.getParameter("account.favouriteCategoryId");
            this.languagePreference = req.getParameter("account.languagePreference");
            this.listOption = Boolean.parseBoolean(req.getParameter("account.listOption"));
            this.bannerOption = Boolean.parseBoolean(req.getParameter("account.bannerOption"));

            account.setUsername(username);
            account.setPassword(password);
            account.setEmail(email);
            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setStatus(status);
            account.setAddress1(address1);
            account.setAddress2(address2);
            account.setCity(city);
            account.setState(state);
            account.setZip(zip);
            account.setPhone(phone);
            account.setCountry(country);
            account.setFavouriteCategoryId(favouriteCategoryId);
            account.setLanguagePreference(languagePreference);
            account.setListOption(listOption);
            account.setBannerOption(bannerOption);

            accountService.insertAccount(account);
            account = accountService.getAccount(account.getUsername());
            if(account != null){   //日志
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + req.getContextPath() + req.getServletPath() + "?" + (req.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " Register a new account";
                logService.insertLogInfo(account.getUsername(), logInfo);
            }
            resp.sendRedirect("mainForm");
        }
    }
    //判断账号密码，可用正则表达式进行修改
    private boolean validate(HttpServletRequest req){
        if(req.getParameter("username")==null|| req.getParameter("username").isEmpty()){
            this.signOnMsg="The username cannot be empty";
            return false;
        }
        if(req.getParameter("password")==null|| req.getParameter("password").isEmpty()){
            this.signOnMsg="The password cannot be empty";
            return false;
        }
        if(req.getParameter("repeatedPassword")==null|| req.getParameter("repeatedPassword").isEmpty()){
            this.signOnMsg="The repeated password cannot be empty";
            return false;
        }
        if(!req.getParameter("password").equals(req.getParameter("repeatedPassword"))){
            this.signOnMsg="The password is not equal to repeatedPassword";
            return false;
        }
        if (req.getParameter("account.email") == null || req.getParameter("account.email").isEmpty()) {
            this.signOnMsg = "The email address cannot be empty";
            return false;
        }
        if (req.getParameter("account.phone") == null || req.getParameter("account.phone").isEmpty()) {
            this.signOnMsg = "The phone number cannot be empty";
            return false;
        }

        // 正则表达式验证邮箱格式
        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(req.getParameter("account.email"));

        if (!matcher.matches()) {
            this.signOnMsg = "Invalid email address format";
            return false;
        }
        //验证手机号码位数
        if (req.getParameter("account.phone").length() != 11) {
            this.signOnMsg = "Invalid phone number format. Please enter 11 digits.";
            return false;
        }
        return true;
    }
    protected boolean checkValidationCode(HttpServletRequest request,String validationCode){
//从httpSession对象中获取验证码
        String validationCodeSession = (String)request.getSession().getAttribute("validation_code");
//如果validationCodeSession==null说明验证码过期，要刷新后重新获得验证码
        if(validationCodeSession==null){
//result.jsp需要的结果信息
            request.setAttribute("info", "验证码过期");
            return false;
        }
//验证验证码是否正确
        if(!validationCode.equalsIgnoreCase(validationCodeSession)){
//result.jsp需要的结果信息
            request.setAttribute("info", "验证码不正确");
            return false;
        }
        return true;
    }
}
