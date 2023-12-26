<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>

<html>

<head>
    <title>OurPetStore</title>
    <link rel="StyleSheet" href="css/ourpetstore.css" type="text/css"
          media="screen" />
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>

<body>
    <div id="Header">

        <div id="Logo">
            <div id="LogoContent">
                <a href="mainForm"> <img src="images/logo-topbar.gif" /></a>
            </div>
        </div>

        <div id="Menu">
            <div id="MenuContent">
                <c:if test="${sessionScope.loginAccount ==null}">
                    <a href="signOnForm">Sign In</a>
                </c:if>
                <c:if test="${sessionScope.loginAccount !=null}">
                    <a href="cartForm"><img align="middle" name="img_cart" src="images/cart.gif" /></a>
                    <img align="middle" src="images/separator.gif" />
                    <a href="signOut">Sign Out</a>
                    <img align="middle" src="images/separator.gif" />
                    <a href="updateAccountForm">My Account</a>
                </c:if>
                <img align="middle" src="images/separator.gif" />
                <a href="help.html">?</a>
            </div>
        </div>

        <div id="Search">
            <div id="SearchContent">
                <form action="searchProductsForm" method="post">
                    <input type="text" name="keyword" id="keyword" size="14">
                    <input type="submit" name="searchProducts" value="Search">
                </form>
                <div id="productAutoComplete">
                    <ul id="productAutoList">

                    </ul>
                </div>
            </div>
        </div>

        <div id="QuickLinks">
            <a href="categoryForm?categoryId=FISH"><img src="images/sm_fish.gif" /></a>
            <img src="images/separator.gif" />
            <a href="categoryForm?categoryId=DOGS"><img src="images/sm_dogs.gif" /></a>
            <img src="images/separator.gif" />
            <a href="categoryForm?categoryId=REPTILES"><img src="images/sm_reptiles.gif" /></a>
            <img src="images/separator.gif" />
            <a href="categoryForm?categoryId=CATS"> <img src="images/sm_cats.gif" /></a>
            <img src="images/separator.gif" />
            <a href="categoryForm?categoryId=BIRDS"><img src="images/sm_birds.gif" /></a>
        </div>
    </div>

<div id="Content">