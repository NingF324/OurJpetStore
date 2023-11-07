<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <%--回车到下一个输入框可优化--%>
    <form action="signOn" name="login_form" method="post">
        <p>Please enter your username and password.</p>
        <c:if test="${requestScope.signOnMsg != null}">
            <p> <font color="red">${requestScope.signOnMsg} </font> </p>
        </c:if>
        <p>
            Username:<input type="text" name="username"> <br />
            Password:<input type="password" name="password">
        </p>
        <span class="require"></span>validation code:
        <input type="text" id="validation_code" name="validation_code" /><!--验证码输入框-->
        <img src="validation_code" id="img_validation_code"
             onclick="refresh()" /><!--图形验证码--><br />
        <input type="button" value="login" onclick="checkRegister()" />
    </form>
    Need a username and password?
    <a href="registerForm">Register Now!</a>

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
        document.login_form.submit();
    }
</script>

<%@ include file="../common/bottom.jsp"%>
