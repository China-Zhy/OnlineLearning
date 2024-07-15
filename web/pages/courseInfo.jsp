<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/views/others/layuiError.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="courseInfoBo" scope="request" type="nxu.bo.CourseInfoBo"/>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>课程的详细信息</title>
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

<%--页面头部--%>
<div class="breadcrumbs overlay">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg-6 col-md-6 col-12">
                <div class="breadcrumbs-content">
                    <h1 class="page-title">【${courseInfoBo.course.name}】的详情信息</h1>
                    <p>
                        <%-- 此处演示两种自定义标签 --%>
                        <%@ taglib prefix="sb" tagdir="/WEB-INF/tags" %>
                        <c:choose>
                            <c:when test="${empty Admin}">
                                <%= request.getHeader("host") %>，您还没有进行登录哦~ 登录后才可以购买课程！
                            </c:when>
                            <c:otherwise>
                                <sb:mySimpleTag name="${Admin.name}" phone="${Admin.phone}" email="${Admin.email}" score="${Admin.score}"/>
                            </c:otherwise>
                        </c:choose>
                    <hr>
                        <%@ taglib prefix="time" uri="/WEB-INF/myDemoTag.tld" %>
                        <time:showTime country="中国" city="银川"/>
                    </p>
                </div>
            </div>
            <div class="col-lg-6 col-md-6 col-12">
                <ul class="breadcrumb-nav">
                    <li><a href="#">任课教师</a></li>
                    <li>${courseInfoBo.teacher.name}</li>
                </ul>
            </div>
        </div>
    </div>
</div>

<section class="section blog-single">
    <div class="container">
        <div class="row">
            <%--课程信息展示(开始)--%>
            <div class="col-lg-8 col-12">
                <div class="single-inner">
                    <div class="post-thumbnils">
                        <img src="${courseInfoBo.course.image}" alt="#">
                    </div>
                    <div class="post-details">
                        <div class="detail-inner">
                            <ul class="custom-flex post-meta">
                                <li>
                                    <a href="#">
                                        <i class="lni lni-calendar"></i>
                                        课程类型：${courseInfoBo.course.courseType} - ${courseTypeName}
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="lni lni-calendar"></i>
                                        课程价格：${courseInfoBo.course.score}
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="lni lni-eye"></i>
                                        <c:choose>
                                            <c:when test="${courseInfoBo.course.score == 1}">
                                                正常价格
                                            </c:when>
                                            <c:when test="${courseInfoBo.course.score == 2}">
                                                折扣活动
                                            </c:when>
                                            <c:when test="${courseInfoBo.course.score == 3}">
                                                限时免费
                                            </c:when>
                                            <c:otherwise>
                                                非法的课程状态
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="lni lni-comments"></i>
                                        评论个数：${courseInfoBo.commentBo.size()}
                                    </a>
                                </li>
                            </ul>
                            <h2 class="post-title">

                                <a href="#">《${courseInfoBo.course.name}》的课程描述信息</a>
                            </h2>
                            <hr>
                            <p>
                                ${courseInfoBo.course.info}
                            </p>
                            <h2 class="post-title">
                                <a href="#">《${courseInfoBo.course.name}》任课教师的相关信息</a>
                            </h2>
                            <hr>
                            <div class="post-image">
                                <div class="row">
                                    <div class="col-lg-6 col-md-6 col-12">
                                        <a href="#">
                                            <img class="blog-inner-big-img" src="${courseInfoBo.teacher.image}" alt="图片加载失败">
                                        </a>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-12">
                                        <ul class="list">
                                            <li><i class="lni lni-checkmark"></i>教师编号：Teacher-${courseInfoBo.teacher.id}</li>
                                            <li><i class="lni lni-checkmark"></i>教师姓名：${courseInfoBo.teacher.name}</li>
                                            <li><i class="lni lni-checkmark"></i>教师电话：${courseInfoBo.teacher.phone}</li>
                                            <li><i class="lni lni-checkmark"></i>教师邮箱：${courseInfoBo.teacher.email}</li>
                                            <li><i class="lni lni-checkmark"></i>注册时间：${courseInfoBo.teacher.register}</li>
                                            <li><i class="lni lni-checkmark"></i>教师性别：
                                                <c:choose>
                                                    <c:when test="${courseInfoBo.teacher.gender == 1}">
                                                        男
                                                    </c:when>
                                                    <c:when test="${courseInfoBo.teacher.gender == 2}">
                                                        女
                                                    </c:when>
                                                    <c:otherwise>
                                                        未知性别
                                                    </c:otherwise>
                                                </c:choose>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <h3>课程其他信息:</h3>
                            <p>
                                <%-- 此处演示两种自定义标签 --%>
                                <sb:mySimpleTag name="${Admin.name}" phone="${Admin.phone}" email="${Admin.email}" score="${Admin.score}"/>
                                <hr>
                                <tools:showTime country="中国" city="银川"/>
                                <br>
                                客户端地址：<%= request.getRequestURL() %>
                            </p>
                            <div class="post-tags-media">
                                <div class="post-tags popular-tag-widget mb-xl-40">
                                    <h5 class="tag-title">课程信息标签</h5>
                                    <div class="tags">
                                        <a href="#">${courseInfoBo.course.name}</a>
                                        <a href="#">${courseTypeName}</a>
                                        <a href="#">${courseInfoBo.teacher.name}</a>
                                    </div>
                                </div>
                                <div class="post-social-media">
                                    <h5 class="share-title">分享此课程给身边的同学</h5>
                                    <ul class="custom-flex">
                                        <li>
                                            <a href="#" class="facebook">
                                                <i class="lni lni-facebook-original"></i>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#" class="twitter">
                                                <i class="lni lni-twitter-original"></i>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#" class="google">
                                                <i class="lni lni-google"></i>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#" class="pinterest">
                                                <i class="lni lni-pinterest"></i>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#" class="vimeo">
                                                <i class="lni lni-vimeo"></i>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="post-comments">
                            <h3 class="comment-title">其他用户对【${courseInfoBo.course.name}】评价：</h3>
                            <ul class="comments-list">
                                <c:forEach items="${courseInfoBo.commentBo}" var="commentBo" varStatus="numInfo">
                                    <c:if test="${numInfo.count % 2 == 1}">
                                        <li>
                                            <div class="comment-img">
                                                <img src="${commentBo.user.image}" alt="图片加载失败">
                                            </div>
                                            <div class="comment-desc">
                                                <div class="desc-top">
                                                    <h6>
                                                        用户姓名：${commentBo.user.name} &ensp;用户编号：${commentBo.user.id}
                                                        <span class="saved"><i class="lni lni-bookmark"></i></span>
                                                    </h6>
                                                    <span class="date">评论时间：${commentBo.comment.time}</span>
                                                    <span style="color: purple;">&ensp;获赞数：${commentBo.comment.good}</span>
                                                    <a href="#" class="reply-link"><i class="lni lni-reply"></i>回复(评论编号${commentBo.comment.id})的评论</a>
                                                </div>
                                                <p>${commentBo.comment.info}</p>
                                            </div>
                                        </li>
                                    </c:if>
                                    <c:if test="${numInfo.index % 2 == 0}">
                                        <li class="children">
                                            <div class="comment-img">
                                                <img src="${commentBo.user.image}" alt="图片加载失败">
                                            </div>
                                            <div class="comment-desc">
                                                <div class="desc-top">
                                                    <h6>
                                                        用户姓名：${commentBo.user.name} &ensp;用户编号：${commentBo.user.id}
                                                        <span class="saved"><i class="lni lni-bookmark"></i></span>
                                                    </h6>
                                                    <span class="date">评论时间：${commentBo.comment.time}</span>
                                                    <span style="color: purple;">&ensp;获赞数：${commentBo.comment.good}</span>
                                                    <a href="#" class="reply-link"><i class="lni lni-reply"></i>回复(评论编号${commentBo.comment.id})的评论</a>
                                                </div>
                                                <p>${commentBo.comment.info}</p>
                                            </div>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </div>

                        <div class="comment-form">
                            <h3 class="comment-reply-title">输入您对【${courseInfoBo.course.name}】的评价：</h3>
                            <form>
                                <div class="row">
                                    <div class="col-12">
                                        <div class="form-box form-group">
                                            <textarea name="#" rows="6" class="form-control form-control-custom" placeholder="请在登录后，再进行评论！"></textarea>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-md-12 col-12">
                                        <div class="form-box form-group">
                                            <input type="text" name="name" class="form-control form-control-custom" placeholder="请输入您的昵称"/>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-md-6 col-12">
                                        <div class="form-box form-group">
                                            <input type="tel" name="phone" class="form-control form-control-custom" placeholder="请输入您的电话号码"/>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-md-6 col-12">
                                        <div class="form-box form-group">
                                            <input type="email" name="email" class="form-control form-control-custom" placeholder="请输入您的电子邮箱"/>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="button">
                                            <button id="doComment" type="submit" class="btn mouse-dir white-bg">点击发送评论<span class="dir-part"></span></button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <%--课程信息展示(结束)--%>
            <aside class="col-lg-4 col-12">
                <div class="sidebar">
                    <div class="widget search-widget">
                        <h5 class="widget-title">【搜索更多相关课程】</h5>
                        <form action="#">
                            <input type="text" placeholder="请输入课程名称信息...">
                            <button type="submit"><i class="lni lni-search-alt"></i></button>
                        </form>
                    </div>
                    <div class="widget popular-feeds">
                        <h5 class="widget-title">【更多同类课程】</h5>
                        <c:forEach begin="1" end="5" var="item">
                            <div class="popular-feed-loop">
                                <div class="single-popular-feed">
                                    <div class="feed-img">
                                        <a href="#"><img src="/pages/assets/images/logo/logo.png" alt="图片加载失败"></a>
                                    </div>
                                    <div class="feed-desc">
                                        <h6 class="post-title"><a href="#">${item}-课程名称：等待后端查询填充，更多详情请联管理员</a>
                                        </h6>
                                        <span class="time">
                                            <i class="lni lni-calendar"></i>课程价格：￥999 &ensp;限时免费
                                            <br><time:showTime country="${requestScope.get('')}" city=""/>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="widget categories-widget">
                        <h5 class="widget-title">【其他功能链接】</h5>
                        <ul class="custom">
                            <c:forEach begin="1" end="5" var="item">
                                <li>
                                    <a href="#">相关功能链接——${item}<i class="lni lni-arrow-right"></i></a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="widget popular-tag-widget">
                        <h5 class="widget-title">【其他受欢迎的课程标签...】</h5>
                        <div class="tags">
                            <c:forEach begin="1" end="10" var="item">
                                <a href="#">标签——${item}</a>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="widget call-us">
                        <div class="content">
                            <h4>准备学习此课程了吗？<span><c:out value="<%= new java.util.Date() %>"/></span></h4>
                            <div class="button">
                                <c:choose>
                                    <c:when test="${isHaveThisCourse}">
                                        <a href="/app/course?method=getSomeCourseNotice&target=${courseInfoBo.course.id}&courseName=${courseInfoBo.course.name}" class="btn" target="_blank">查看课程公告</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="/app/course?method=userBuyCourseByCourseId&courseId=${courseInfoBo.course.id}" class="btn">购买此课程</a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </aside>
        </div>
    </div>
</section>

<!-- 回到顶部 -->
<a href="#" class="scroll-top btn-hover">
    <i class="lni lni-chevron-up"></i>
</a>

<!-- js文件 -->
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
<script src="/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index'], function () {
        let $ = layui.$;
        $('#doComment').click(function () {

            alert(1);
            return false;

            if ($('#score').val() == null || $('#score').val() === '') {
                layer.msg('请填写金额', {offset: '15px', icon: 5, time: 1000});
                return;
            }
            $.ajax({
                type: 'POST',
                url: '/app/course?method=doRechargeScore',
                data: {'score': $('#score').val()},
                dataType: 'json',
                success: function (data) {
                    if (data.result) {
                        layer.msg(data.info, {offset: '15px', icon: 1, time: 1000});
                    } else {
                        layer.msg(data.info, {offset: '15px', icon: 2, time: 1000});
                    }
                },
                error: function (xhr, status, error) {
                    layer.msg(data.info, {offset: '15px', icon: 5, time: 1000});
                    console.log(error); // 控制台打印错误
                }
            });
        });
        $('#goCourseHome').click(function () {
            window.location.href = '/app/course?method=getAllCourseByUserId';
        });
    });
</script>