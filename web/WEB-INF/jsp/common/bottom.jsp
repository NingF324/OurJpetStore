</div>

<div id="Footer">

    <div id="PoweredBy">&nbsp<a href="http://www.csu.deu.cn">www.csu.deu.cn</a>
    </div>

    <div id="Banner">
        <c:if test="${sessionScope.loginAccount != null }">
            <c:if test="${sessionScope.loginAccount.bannerOption}">
                ${sessionScope.loginAccount.bannerName}
            </c:if>
        </c:if>
    </div>

</div>
<script src="js/productAuto.js"></script>
<script src="js/isUserNameExist.js"></script>
<script src="js/cartAutoUpdate.js"></script>
<script src="js/removeConfirm.js"></script>
</body>
</html>
