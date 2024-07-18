<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/views/others/layuiError.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="courseDataBo" scope="request" type="java.util.List"/>
<html class="no-js" lang="en">
<head>
    <title>欢迎访问 Online Learning</title>
    <jsp:include page="/pages/assets/app/myCss.jsp"/>
</head>

<body>
<%--页面加载动画--%>
<jsp:include page="/pages/loading.jsp"/>

<%--头部导航菜单(开始)--%>
<header class="header">
    <div class="header-inner">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <nav class="navbar navbar-expand-lg">
                        <a class="navbar-brand" href="#"><img src="/pages/assets/images/logo/logo.png" alt="Logo加载失败"></a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <i class="lni lni-menu open"></i>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <%--课程类型の功能菜单(开始)--%>
                                <c:forEach items="${courseDataBo}" var="courseInfo">
                                <li class="nav-item">
                                    <a class="nav-link" href="#courseType${courseInfo.courseType.id}" style="font-size: 30px; font-weight: bold;">${courseInfo.courseType.name}</a>
                                </li>
                                </c:forEach>
                            <%--课程类型の功能菜单(结束)--%>
                            </ul>
                            <div class="button">
                                <c:choose>
                                    <c:when test="${empty Admin}">
                                        <a href="#toFooter" class="btn" disabled>Hi~ 同学，别来无恙</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="/user?method=logout" class="btn">退 出 登 录</a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</header>
<%--头部导航菜单(结束)--%>

<%--页面头部登录&注册功能模块(开始)--%>
<section class="demo-area">
    <div class="verticle-lines">
        <div class="vlines one"></div>
        <div class="vlines two"></div>
        <div class="vlines three"></div>
        <div class="vlines four"></div>
    </div>
    <div class="demo-inner">
        <div class="container">
            <div class="row ">
                <div class="col-lg-6 co-12">
                    <div class="demo-text">
                        <h1 class="wow fadeInUp" data-wow-delay=".3">Online Learning<br>一个简约、开放、共享的在线学习平台</h1>
                        <p class="wow fadeInUp" data-wow-delay=".5s">
                        <hr>
                        该网站是一个基于Servlet&JSP的在线学习平台，该项目由 宁夏大学 2022级 软件工程二班 张宏业 携其组员 樊雪儿、胡昊、唐馨源 倾情打造...
                        </p>
                        <div class="button wow fadeInUp" data-wow-delay=".7s">
                            <c:if test="${not empty Admin}">
                                <a href="/app/course?method=getAllCourseByUserId&pageIndex=1&pageSize=6" class="btn">我 的 课 程</a>
                                <a href="/app/course?method=toRechargeScore" class="btn btn-alt">充 值 中 心</a>
                            </c:if>
                            <c:if test="${empty Admin}">
                                <a href="/views/user/userLogin.jsp" class="btn">登 录</a>
                                <a href="/views/user/userRegister.jsp" class="btn btn-alt">注 册</a>
                            </c:if>
                            <c:if test="${!empty Admin && Admin.type!=4}">
                                <a href="/app/course?method=goToLayuiAdmin" class="btn">后 台 管 理</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%--页面头部登录&注册功能模块(结束)--%>

<%--根据课程的不同类型显示课程(开始)--%>
<c:forEach items="${courseDataBo}" var="courseInfo" varStatus="blockInfo">
<section id="courseType${courseInfo.courseType.id}" class="home-pages universal-demo section" style="background-color: ${blockInfo.count % 2 == 1 ? '#fff':'#F4F7FA'}">
    <img class="shape" src="/pages/assets/images/shape.png" alt="课程图片加载失败">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="section-title">
                    <h2 class="wow fadeInUp" data-wow-delay=".4s">【${courseInfo.courseType.name}】</h2>
                    <p class="wow fadeInUp" data-wow-delay=".6s">【课程类型编号-${courseInfo.courseType.id}】</p>
                </div>
            </div>
        </div>
        <div class="home-page-inner">
<%--课程信息的循环(开始)--%>
            <c:forEach items="${courseInfo.courses}" var="course" varStatus="numberInfo">
                <c:if test="${numberInfo.index % 3 == 0}">
                    <c:if test="${numberInfo.last}">
                        <div class="row">
                            <div class="col-lg-4 col-md-6 col-12">
                                <div class="single-demo wow fadeInUp" data-wow-delay=".2s">
                                    <a href="/app/course?method=showCourseDetailsById&id=${course.id}&courseType=${courseInfo.courseType.name}" target="_blank"><img src="${course.image}" alt="图片加载失败">
                                        <h3 style="font-weight: bold;">编号:${course.id}&ensp;名称:${course.name}&ensp;<span style="color: darkred;">价格:￥${course.score}</span></h3>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${!numberInfo.last}">
                        <div class="row">
                            <div class="col-lg-4 col-md-6 col-12">
                                <div class="single-demo wow fadeInUp" data-wow-delay=".2s">
                                    <a href="/app/course?method=showCourseDetailsById&id=${course.id}&courseType=${courseInfo.courseType.name}" target="_blank"><img src="${course.image}" alt="图片加载失败">
                                        <h3 style="font-weight: bold;">编号:${course.id}&ensp;名称:${course.name}&ensp;<span style="color: darkred;">价格:￥${course.score}</span></h3>
                                    </a>
                                </div>
                            </div>
                    </c:if>
                </c:if>

                <c:if test="${numberInfo.index % 3 != 0}">
                    <c:if test="${numberInfo.last}">
                            <div class="col-lg-4 col-md-6 col-12">
                                <div class="single-demo wow fadeInUp" data-wow-delay=".${(numberInfo.index % 3)*2}s">
                                    <a href="/app/course?method=showCourseDetailsById&id=${course.id}&courseType=${courseInfo.courseType.name}" target="_blank"><img src="${course.image}" alt="图片加载失败">
                                        <h3 style="font-weight: bold;">编号:${course.id}&ensp;名称:${course.name}&ensp;<span style="color: darkred;">价格:￥${course.score}</span></h3>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${!numberInfo.last}">
                        <div class="col-lg-4 col-md-6 col-12">
                            <div class="single-demo wow fadeInUp" data-wow-delay=".${(numberInfo.index % 3)*2}s">
                                <a href="/app/course?method=showCourseDetailsById&id=${course.id}&courseType=${courseInfo.courseType.name}" target="_blank"><img src="${course.image}" alt="图片加载失败">
                                    <h3 style="font-weight: bold;">编号:${course.id}&ensp;名称:${course.name}&ensp;<span style="color: darkred;">价格:￥${course.score}</span></h3>
                                </a>
                            </div>
                        </div>
                    </c:if>
                </c:if>
            </c:forEach>
<%--课程信息的循环(结束)--%>
        </div>
    </div>
</section>
</c:forEach>
<%--根据课程的不同类型显示课程(结束)--%>

<%--页尾链接导航(开始)--%>
<footer class="footer" id="toFooter">
    <div class="call-action">
        <div class="container">
            <div class="inner-content">
                <div class="row align-items-center">
                    <div class="col-lg-8 col-md-8 col-12">
                        <h2>准备好加入 Online Learning 团队了吗?</h2>
                        <p>
                            【努力只能及格，拼命才能优秀】<br>
                            【书山有路勤为径，学海无涯苦作舟，苦作舟】<br>
                            【项目组成员：张宏业、樊雪儿、胡昊、唐馨源。指导教师：波波Teacher】
                        </p>
                    </div>
                    <div class="col-lg-4 col-md-4 col-12">
                        <div class="button">
                            <a href="https://webvpn.nxu.edu.cn/" class="btn" target="_blank">探索更多关于 Online Learning</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer-middle">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-4 col-12">
                    <div class="f-about single-footer">
                        <div class="logo">
                            <a href="#"><img src="/pages/assets/images/logo/nxu.png" alt="图片加载失败"></a>
                        </div>
                        <p>开发顺序：entity → mapper接口 → mapper.xml → service <br> → serviceImpl → Test → controller → jsp → 浏览器运行调试</p>
                        <div class="footer-social">
                            <ul>
                                <li><a href="#"><i class="lni lni-facebook-original"></i></a></li>
                                <li><a href="#"><i class="lni lni-twitter-original"></i></a></li>
                                <li><a href="#"><i class="lni lni-linkedin-original"></i></a></li>
                                <li><a href="#"><i class="lni lni-google"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8 col-md-8 col-12">
                    <div class="row">
                        <div class="col-lg-3 col-md-6 col-12">
                            <div class="single-footer sm-custom-border f-link">
                                <h3>【张宏业】</h3>
                                <ul>
                                    <li><a href="#">【用户管理-User表】</a></li>
                                    <li><a href="#">【文件管理-File表】</a></li>
                                    <li><a href="#">【作业管理-Homework】</a></li>
                                    <li><a href="#">【An attractive man】</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6 col-12">
                            <div class="single-footer sm-custom-border f-link">
                                <h3>【樊雪儿】</h3>
                                <ul>
                                    <li><a href="#">【课程管理-Course表】</a></li>
                                    <li><a href="#">【课程类型-CourseType】</a></li>
                                    <li><a href="#">【课程邀请码-CourseCode】</a></li>
                                    <li><a href="#">【提前一天跑路的美少女】</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6 col-12">
                            <div class="single-footer md-custom-border sm-custom-border f-link">
                                <h3>【胡    昊】</h3>
                                <ul>
                                    <li><a href="#">【订单管理-Order表】</a></li>
                                    <li><a href="#">【积分管理-Points表】</a></li>
                                    <li><a href="#">【活动管理-Active表】</a></li>
                                    <li><a href="#">【蝴蝶羊排吃不倒的Man】</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6 col-12">
                            <div class="single-footer md-custom-border sm-custom-border f-link">
                                <h3>【唐馨源】</h3>
                                <ul>
                                    <li><a href="#">【公告管理-Notice表】</a></li>
                                    <li><a href="#">【评论管理-Comment表】</a></li>
                                    <li><a href="#">【私信管理-Message表】</a></li>
                                    <li><a href="#">【牙口不好的搞怪小妹儿】</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer-bottom">
        <div class="container">
            <div class="inner">
                <div class="row">
                    <div class="col-12">
                        <div class="left">
                            <p><a target="_blank" href="#" title="Online Learning 在线学习平台" style="text-decoration: none;">Online Learning 在线学习平台</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
<%--页尾链接导航(结束)--%>

<%--回到顶部按钮--%>
<jsp:include page="goHead.jsp"/>

<jsp:include page="/pages/assets/app/myJs.jsp"/>
</body>
</html>