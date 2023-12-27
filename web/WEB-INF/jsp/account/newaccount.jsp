<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form action="newAccount" name="new_account_form" method="post">
        <h3>User Information</h3>
        <c:if test="${requestScope.signOnMsg != null}">
            <p> <font color="red">${requestScope.signOnMsg} </font> </p>
        </c:if>
        <table>
            <tr>
                <td>User ID:</td>
                <td><input type="text" name="username" id="usernameInput"></td>
                <label id="isUserNameExistLabel" style="color: red"></label>
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
        <span class="require"></span>validation code:
        <input type="text" id="validation_code" name="validation_code" /><!--验证码输入框-->
        <img src="validation_code" id="img_validation_code"
             onclick="refresh()" /><!--图形验证码--><br />

        <h3>Account Information</h3>

        <table>
            <tr>
                <td>First name:</td>
                <td><input type="text" name="account.firstName"></td>
            </tr>
            <tr>
                <td>Last name:</td>
                <td><input type="text" name="account.lastName"></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="text" name="account.email"></td>
            </tr>
            <tr>
                <td>Phone:</td>
                <td><input type="text" name="account.phone"></td>
            </tr>
            <tr>
                <td>Address 1:</td>
                <td><input type="text" name="account.address1"></td>
            </tr>
            <tr>
                <td>Address 2:</td>
                <td><input type="text" name="account.address2"></td>
            </tr>
            <tr>
                <td>City:</td>
                <td><input type="text" name="account.city"></td>
            </tr>
            <tr>
                <td>State:</td>
                <td><input type="text" name="account.state"></td>
            </tr>
            <tr>
                <td>Zip:</td>
                <td><input type="text" name="account.zip"></td>
            </tr>
            <tr>
                <td>Country:</td>
                <td><input type="text" name="account.country"></td>
            </tr>
        </table>

        <h3>Profile Information</h3>

        <table>
            <tr>
                <td>Language Preference:</td>
                <td>
                    <select name="account.languagePreference">
                        <c:forEach var="language" items="${sessionScope.languages}">
                            <option value="${language}">${language}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Favourite Category:</td>
                <td>
                    <select name="account.favouriteCategoryId">
                        <c:forEach var="category" items="${sessionScope.categories}">
                            <option value="${category}">${category}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Enable MyList</td>
                <td><input type="checkbox" name="account.listOption"></td>
            </tr>
            <tr>
                <td>Enable MyBanner</td>
                <td><input type="checkbox" name="account.bannerOption"></td>
            </tr>

        </table>

        <input type="button" value="sign up" onclick="checkRegister()" />
    </form>
</div>
<script type="text/javascript">
    function refresh() {
        var img = document.getElementById("img_validation_code");
        img.src = "validation_code?" + Math.random();
    }
    //实现验证码的刷新
    function checkRegister() {
        var validation_code = document.getElementById("validation_code");
        if (validation_code.value == "") {
            alert("Validation code is required.");
            return;
        }
        document.new_account_form.submit();
    }
</script>
<%@ include file="../common/bottom.jsp"%>