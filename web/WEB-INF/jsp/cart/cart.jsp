<%@ page import="java.math.BigDecimal" %>

<%
  BigDecimal totalCost = new BigDecimal("0"); // 初始化总价格为0
  pageContext.setAttribute("totalCost", totalCost); // 存储总价格在pageContext中
%>

<%@ include file="../common/top.jsp"%>

<div id="BackLink">
  <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Catalog">

  <div id="Cart">

    <h2>Shopping Cart</h2>

    <form action="updateCart" method="post">
      <table>
        <tr>
          <th><b>Item ID</b></th>
          <th><b>Product ID</b></th>
          <th><b>Description</b></th>
          <th><b>In Stock?</b></th>
          <th><b>Quantity</b></th>
          <th><b>List Price</b></th>
          <th><b>Total Cost</b></th>
          <th>&nbsp;</th>
        </tr>

        <c:if test="${sessionScope.cart.numberOfItems == 0}">
          <tr>
            <td colspan="8"><b>Your cart is empty.</b></td>
          </tr>
        </c:if>

        <c:forEach var="cartLineItem" items="${sessionScope.cartLineItems}">
          <tr>
            <td>
              <a href="itemForm?itemId=${cartLineItem.itemId}">${cartLineItem.itemId}</a>
            </td>
            <td>${cartLineItem.productId}</td>
            <td>${cartLineItem.description}</td>
            <td>true</td>
            <td>
              <input type="text" name="${cartLineItem.itemId}" value="${cartLineItem.quantity}">
            </td>
            <td><fmt:formatNumber value="${cartLineItem.listPrice}" pattern="$#,##0.00" /></td>
            <td><fmt:formatNumber value="${cartLineItem.unitPrice}" pattern="$#,##0.00" /></td>
            <td>
              <a href="removeCartItem?workingItemId=${cartLineItem.itemId}" class="Button">Remove</a>
            </td>
          </tr>
          <c:set var="totalCost" value="${(totalCost.add(cartLineItem.unitPrice))}" />
        </c:forEach>

        <tr>
          <td colspan="7">
            Sub Total: <fmt:formatNumber value="${totalCost}" pattern="$#,##0.00" />
            <input type="submit" value="Update Cart">
          </td>
          <td>&nbsp;</td>
        </tr>
      </table>
    </form>

    <c:if test="${sessionScope.cart.numberOfItems > 0}">
      <a href="newOrderForm" class="Button">Proceed to Checkout</a>
    </c:if>
  </div>

  <div id="MyList">
    <c:if test="${sessionScope.loginAccount != null}">
      <c:if test="${!empty sessionScope.loginAccount.listOption}">
        <%@ include file="includeMyList.jsp"%>
      </c:if>
    </c:if>
  </div>

  <div id="Separator">&nbsp;</div>
</div>

<%@ include file="../common/bottom.jsp"%>
