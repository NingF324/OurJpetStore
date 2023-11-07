<%@ include file="../common/top.jsp"%>

<div id="Catalog">
<form action="updateAccount" name="update_form" method="post">
    <c:if test="${requestScope.errorMsg != null}">
        <p> <font color="red">${requestScope.errorMsg} </font> </p>
    </c:if>
    <h3>User Information</h3>

    <table>
        <tr>
            <td>User ID:</td>
            <td>${sessionScope.loginAccount.username}</td>
        </tr>
        <tr>
            <td>New password:</td>
            <td><input type="text" name="password"></td>
        </tr>
        <tr>
            <td>Repeat password:</td>
            <td><input type="text" name="repeatedPassword"></td>
        </tr>
    </table>

    <h3>Account Information</h3>
    <table>
        <tr>
            <td>First name:</td>
            <td><input type="text" name="account.firstName" value="${sessionScope.loginAccount.firstName}"></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><input type="text" name="account.lastName" value="${sessionScope.loginAccount.lastName}"></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="account.email" value="${sessionScope.loginAccount.email}"></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><input type="text" name="account.phone" value="${sessionScope.loginAccount.phone}"></td>
        </tr>
        <tr>
            <td>Address 1:</td>
            <td><input type="text" name="account.address1" value="${sessionScope.loginAccount.address1}"></td>
        </tr>
        <tr>
            <td>Address 2:</td>
            <td><input type="text" name="account.address2" value="${sessionScope.loginAccount.address2}"></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><input type="text" name="account.city" value="${sessionScope.loginAccount.city}"></td>
        </tr>
        <tr>
            <td>State:</td>
            <td><input type="text" name="account.state" value="${sessionScope.loginAccount.state}"></td>
        </tr>
        <tr>
            <td>Zip:</td>
            <td><input type="text" name="account.zip" value="${sessionScope.loginAccount.zip}"></td>
        </tr>
        <tr>
            <td>Country:</td>
            <td><input type="text" name="account.country" value="${sessionScope.loginAccount.country}"></td>
        </tr>
    </table>

    <h3>Profile Information</h3>

    <table>
        <tr>
            <td>Language Preference:</td>
            <td>
                <select id="account.languagePreference" name="account.languagePreference">
                    <option value="english" ${sessionScope.loginAccount.languagePreference=='english'?'selected':''}>
                        english
                    </option>
                    <option value="japanese" ${sessionScope.loginAccount.languagePreference=='japanese'?'selected':''}>
                        japanese
                    </option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Favourite Category:</td>
            <td>
                <select id="account.favouriteCategoryId" name="account.favouriteCategoryId">
                    <option value="FISH" ${sessionScope.loginAccount.favouriteCategoryId=='FISH'?'selected':''}>
                        FISH
                    </option>
                    <option value="DOGS" ${sessionScope.loginAccount.favouriteCategoryId=='DOGS'?'selected':''}>
                        DOGS
                    </option>
                    <option value="REPTILES" ${sessionScope.loginAccount.favouriteCategoryId=='REPTILES'?'selected':''}>
                        REPTILES
                    </option>
                    <option value="CATS" ${sessionScope.loginAccount.favouriteCategoryId=='CATS'?'selected':''}>
                        CATS
                    </option>
                    <option value="BIRDS" ${sessionScope.loginAccount.favouriteCategoryId=='BIRDS'?'selected':''}>
                        BIRDS
                    </option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Enable MyList</td>
            <td><input type="checkbox" name="account.listOption" value="true" ${sessionScope.loginAccount.listOption?'checked':''}></td>
        </tr>
        <tr>
            <td>Enable MyBanner</td>
            <td><input type="checkbox" name="account.bannerOption" value="true"${sessionScope.loginAccount.bannerOption?'checked':''}></td>
        </tr>

    </table>

    <input type="submit" name="editAccount" value="Save Account Information">
</form>
<stripes:link
        beanclass="org.mybatis.jpetstore.web.actions.OrderActionBean"
        event="listOrders">My Orders</stripes:link>
</div>

<%@ include file="../common/bottom.jsp"%>
