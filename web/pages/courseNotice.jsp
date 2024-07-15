<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/views/others/layuiError.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>课程公告</title>
    <meta name="description" content="在线学习平台"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="shortcut icon" type="image/x-icon" href="/pages/assets/images/favicon.svg"/>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
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

<section class="services style3 section">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="section-title">
                    <span class="wow fadeInDown" data-wow-delay=".2s" style="font-size: 20px;">【课程公告，共 ${fn:length(notices)} 条】</span>
                    <h2 class="wow fadeInUp" data-wow-delay=".4s">${courseName}的公告</h2>
                    <h3 class="overlay-text">Course Notice</h3>
                </div>
            </div>
        </div>
        <div class="single-head">
            <div class="row">
                <c:forEach items="${notices}" var="notice" varStatus="item">
                    <div class="col-lg-4 col-md-6 col-12">
                        <div class="single-service wow fadeInUp" data-wow-delay=".2*${item.count}s">
                            <span class="serial">
                                <c:choose>
                                    <c:when test="${item.count < 10}">
                                        0${item.count}
                                    </c:when>
                                    <c:otherwise>
                                        ${item.count}
                                    </c:otherwise>
                                </c:choose>
                            </span>
                            <h3><a href="#">${notice.title}</a></h3>
                            <p>
                                发布人：${notice.userId}&ensp;发布时间：${notice.create} <br>
                                <c:choose>
                                    <c:when test="${fn:length(notice.info) >= 70}">
                                        ${fn:substring(notice.info, 0, 70)}...
                                    </c:when>
                                    <c:otherwise>
                                        ${notice.info}
                                    </c:otherwise>
                                </c:choose>
                            </p>
                            <div class="button">
                                <a href="#" class="btn">查看详细信息</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</section>

<a href="#" class="scroll-top btn-hover">
    <i class="lni lni-chevron-up"></i>
</a>

<script src="/pages/assets/js/bootstrap.min.js"></script>
<script src="/pages/assets/js/count-up.min.js"></script>
<script src="/pages/assets/js/wow.min.js"></script>
<script src="/pages/assets/js/tiny-slider.js"></script>
<script src="/pages/assets/js/glightbox.min.js"></script>
<script src="/pages/assets/js/imagesloaded.min.js"></script>
<script src="/pages/assets/js/isotope.min.js"></script>
<script src="/pages/assets/js/main.js"></script>
</body>
</html>