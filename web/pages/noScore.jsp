<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/views/others/layuiError.jsp" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <title>积分不足</title>
    <jsp:include page="/pages/assets/app/myCss.jsp"/>
</head>

<body>
<%--页面加载动画--%>
<jsp:include page="loading.jsp"/>

<div class="mail-success">
    <div class="verticle-lines">
        <div class="vlines one"></div>
        <div class="vlines two"></div>
        <div class="vlines three"></div>
        <div class="vlines four"></div>
    </div>
    <div class="d-table">
        <div class="d-table-cell">
            <div class="container">
                <div class="error-content">
                    <h1>课程购买失败了哦</h1>
                    <h2>原因是您的积分不足，快快去充值吧！</h2>
                    <p>The course purchase was failed!</p>
                    <div class="button">
                        <a href="/app/course?method=toRechargeScore" class="btn">前往积分充值</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--回到顶部按钮--%>
<jsp:include page="goHead.jsp"/>

<jsp:include page="/pages/assets/app/myJs.jsp"/>
<script>
    window.onload = function () {
        window.setTimeout(fadeout, 500);
    }

    function fadeout() {
        document.querySelector('.preloader').style.opacity = '0';
        document.querySelector('.preloader').style.display = 'none';
    }
</script>
</body>

</html>