<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/views/others/layuiError.jsp" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <title>课程购买成功啦</title>
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
                    <h1>课程购买成功啦</h1>
                    <h2>The course purchase was successful!</h2>
                    <p>努力只能及格，拼命才能优秀！加油吧，同学，开启你的该课程学习之旅吧！</p>
                    <div class="button">
                        <a href="/app/course?method=getAllCourseByUserId&pageIndex=1&pageSize=6" class="btn">查看我拥有的课程</a>
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