<%@ include file="../common/top.jsp"%>

<div id="Catalog">

    <form action="confirmOrderForm" method="post">

        <table class="orderTable">
            <tbody id="firstTable" class="tbody">
            <tr>
                <th>Payment Details</th>
                <th class="press"><button type="button" class="open">+/-</button></th>
            </tr>
            <tr>
                <td>Card Type:</td>
                <td>
                    <select name="order.cardType">
                        <option value="Visa" ${sessionScope.order.cardType=='Visa'?'selected':''}>Visa</option>
                        <option value="MasterCard" ${sessionScope.order.cardType=='MasterCard'?'selected':''}>MasterCard</option>
                        <option value="American Express" ${sessionScope.order.cardType=='American Express'?'selected':''}>American Express</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Card Number:</td>
                <td><input type="text" name="order.creditCard" value="${sessionScope.order.creditCard}"> * Use a fake number!</td>
            </tr>
            <tr>
                <td>Expiry Date (MM/YYYY):</td>
                <td><input type="text" name="order.expiryDate" value="${sessionScope.order.expiryDate}"></td>
            </tr>
            </tbody>





            <tbody id="secondTable" class="tbody">
            <tr>
                <th >Billing Address</th>
                <th class="press"><button type="button" class="open">+/-</button></th>
            </tr>
            <tr>
                <td>First name:</td>
                <td><input type="text" name="order.billToFirstName" value="${sessionScope.order.billToFirstName}"></td>
            </tr>
            <tr>
                <td>Last name:</td>
                <td><input type="text" name="order.billToLastName" value="${sessionScope.order.billToLastName}"></td>
            </tr>
            <tr>
                <td>Address 1:</td>
                <td><input type="text" name="order.billAddress1" value="${sessionScope.order.billAddress1}"></td>
            </tr>
            <tr>
                <td>Address 2:</td>
                <td><input type="text" name="order.billAddress2" value="${sessionScope.order.billAddress2}"></td>
            </tr>
            <tr>
                <td>City:</td>
                <td><input type="text" name="order.billCity" value="${sessionScope.order.billCity}"></td>
            </tr>
            <tr>
                <td>State:</td>
                <td><input type="text" name="order.billState" value="${sessionScope.order.billState}"></td>
            </tr>
            <tr>
                <td>Zip:</td>
                <td><input type="text" name="order.billZip" value="${sessionScope.order.billZip}"></td>
            </tr>
            <tr>
                <td>Country:</td>
                <td><input type="text" name="order.billCountry" value="${sessionScope.order.billCountry}"></td>
            </tr>
            </tbody>






            <tbody id="thirdTable" class="tbody">
            <tr>
                <th>Shipping Address</th>
                <th class="press"><button type="button" class="open">+/-</button></th>
            </tr>

            <tr>
                <td>First name:</td>
                <td><input type="text" name="shipToFirstName" value="${sessionScope.order.shipToFirstName}"></td>
            </tr>
            <tr>
                <td>Last name:</td>
                <td><input type="text" name="shipToLastName" value="${sessionScope.order.shipToLastName}"></td>
            </tr>
            <tr>
                <td>Address 1:</td>
                <td><input type="text" name="shipAddress1" value="${sessionScope.order.shipAddress1}"></td>
            </tr>
            <tr>
                <td>Address 2:</td>
                <td><input type="text" name="shipAddress2" value="${sessionScope.order.shipAddress2}"></td>
            </tr>
            <tr>
                <td>City:</td>
                <td><input type="text" name="shipCity" value="${sessionScope.order.shipCity}"></td>
            </tr>
            <tr>
                <td>State:</td>
                <td><input type="text" name="shipState" value="${sessionScope.order.shipState}"></td>
            </tr>
            <tr>
                <td>Zip:</td>
                <td><input type="text" name="shipZip" value="${sessionScope.order.shipZip}"></td>
            </tr>
            <tr>
                <td>Country:</td>
                <td><input type="text" name="shipCountry" value="${sessionScope.order.shipCountry}"></td>
            </tr>
            </tbody>

        </table>

        <input type="submit" value="Continue">

    </form>

</div>
<%@ include file="../common/bottom.jsp"%>

