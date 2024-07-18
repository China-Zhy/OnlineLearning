<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/views/others/layuiError.jsp" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <title>课程购买失败啦</title>
    <jsp:include page="/pages/assets/app/myCss.jsp"/>
</head>

<body>
<%--页面加载动画--%>
<jsp:include page="loading.jsp"/>

<div class="error-area">
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
                    <h1>404</h1>
                    <h2>课程信息不见啦</h2>
                    <p>更多详细信息请联系工作人员，电话：18201521341</p>
                    <div class="button">
                        <a href="/app/course?method=showCourseGroupByType" class="btn">返回课程首页</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

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