<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>购买失败啦</title>
    <meta name="description" content="在线学习平台"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="shortcut icon" type="image/x-icon" href="assets/images/favicon.svg"/>
    <link href="https://fonts.googleapis.com/css2?family=Spartan:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/pages/assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/pages/assets/css/LineIcons.2.0.css"/>
    <link rel="stylesheet" href="/pages/assets/css/animate.css"/>
    <link rel="stylesheet" href="/pages/assets/css/tiny-slider.css"/>
    <link rel="stylesheet" href="/pages/assets/css/glightbox.min.css"/>
    <link rel="stylesheet" href="/pages/assets/css/main.css"/>
</head>

<body>
<%--页面加载动画--%>
<jsp:include page="loading.jsp"/>

<!-- Start Error Area -->
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
<!-- End Error Area -->
<script src="/pages/assets/js/bootstrap.min.js"></script>
<script src="/pages/assets/js/count-up.min.js"></script>
<script src="/pages/assets/js/wow.min.js"></script>
<script src="/pages/assets/js/tiny-slider.js"></script>
<script src="/pages/assets/js/glightbox.min.js"></script>
<script src="/pages/assets/js/imagesloaded.min.js"></script>
<script src="/pages/assets/js/isotope.min.js"></script>
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