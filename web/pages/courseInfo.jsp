<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/views/others/layuiError.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="courseInfoBo" scope="request" type="nxu.bo.CourseInfoBo"/>
<html class="no-js" lang="en">
<head>
    <title>课程的详细信息</title>
    <jsp:include page="/pages/assets/app/myCss.jsp"/>
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
                    <h1 class="page-title" style="letter-spacing: 2px;">【${courseInfoBo.course.name}】的详情信息</h1>
                    <p>
                        <%-- 此处演示两种自定义标签 --%>
                        <%@ taglib prefix="sb" tagdir="/WEB-INF/tags" %>
                        <c:choose>
                            <c:when test="${empty Admin}">
                                请求地址为: <%= request.getHeader("host") %>，您还没有进行登录哦~ 登录后才可以购买课程！
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
                    <li><a href="#" style="font-weight: bold;font-size: 20px;letter-spacing: 2px;">⚪任课教师⚪</a></li>
                    <li style="font-weight: bold;font-size: 20px;letter-spacing: 2px;">⚪${courseInfoBo.teacher.name}⚪</li>
                </ul>
            </div>
        </div>
    </div>
</div>

<%--课程主题信息部分--%>
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
                            <hr>
                            <ul class="custom-flex post-meta">
                                <li>
                                    <a href="#" style="font-weight: bold; font-size: 20px;">
                                        <i class="lni lni-calendar"></i>
                                        课程类型：${courseTypeName}
                                    </a>
                                </li>
                                <li>
                                    <a href="#" style="font-weight: bold; font-size: 20px;">
                                        <i class="lni lni-calendar"></i>
                                        <span style="color: red;">课程价格：￥${courseInfoBo.course.score}</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#" style="font-weight: bold; font-size: 20px;">
                                        <i class="lni lni-eye"></i>
                                        <c:choose>
                                            <c:when test="${courseInfoBo.course.state == 1}">
                                                正常价格
                                            </c:when>
                                            <c:when test="${courseInfoBo.course.state == 2}">
                                                折扣活动
                                            </c:when>
                                            <c:when test="${courseInfoBo.course.state == 3}">
                                                限时免费
                                            </c:when>
                                            <c:otherwise>
                                                非法的课程状态
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                </li>
                                <li>
                                    <a href="#" style="font-weight: bold; font-size: 20px;">
                                        <i class="lni lni-comments"></i>
                                        评论数：${courseInfoBo.commentBo.size()}条
                                    </a>
                                </li>
                            </ul>
                            <hr>
                            <h2 class="post-title">
                                <a href="#">🟤《${courseInfoBo.course.name}》的课程描述信息</a>
                            </h2>
                            <hr>
                            <p>
                                ${courseInfoBo.course.info}
                            </p>
                            <h2 class="post-title">
                                <a href="#">🟡《${courseInfoBo.course.name}》任课教师的相关信息</a>
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
                                        <ul class="list" style="font-weight: bold;font-size: 15px;">
                                            <li><i class="lni lni-checkmark"></i>教师编号：Teacher-${courseInfoBo.teacher.id}</li>
                                            <li><i class="lni lni-checkmark"></i>教师姓名：${courseInfoBo.teacher.name}</li>
                                            <li><i class="lni lni-checkmark"></i>教师电话：${courseInfoBo.teacher.phone}</li>
                                            <li><i class="lni lni-checkmark"></i>教师邮箱：${courseInfoBo.teacher.email}</li>
                                            <li><i class="lni lni-checkmark"></i>注册时间：${courseInfoBo.teacher.register}</li>
                                            <li><i class="lni lni-checkmark"></i>教师性别：
                                                <c:choose>
                                                    <c:when test="${courseInfoBo.teacher.gender == 1}">
                                                        👨‍🏫男
                                                    </c:when>
                                                    <c:when test="${courseInfoBo.teacher.gender == 2}">
                                                        👩‍🏫女
                                                    </c:when>
                                                    <c:otherwise>
                                                        🦹未知性别
                                                    </c:otherwise>
                                                </c:choose>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <h3>🟢课程其他信息:</h3>
                            <p>
                                <sb:mySimpleTag name="${Admin.name}" phone="${Admin.phone}" email="${Admin.email}" score="${Admin.score}"/>
                                <hr>
                                <tools:showTime country="中国" city="银川"/>
                                <br>
                                客户端地址：<%= request.getRequestURL() %>
                            </p>
                            <div class="post-tags-media">
                                <div class="post-tags popular-tag-widget mb-xl-40">
                                    <h4 class="tag-title">🟠课程信息标签</h4>
                                    <div class="tags" style="font-weight: bold;">
                                        <a href="#">${courseInfoBo.course.name}</a>
                                        <a href="#">${courseTypeName}</a>
                                        <a href="#">${courseInfoBo.teacher.name}</a>
                                        <c:choose>
                                            <c:when test="${courseInfoBo.course.state == 1}"><a href="#">原价课程</a></c:when>
                                            <c:when test="${courseInfoBo.course.state == 2}"><a href="#">折扣课程</a></c:when>
                                            <c:when test="${courseInfoBo.course.state == 3}"><a href="#">限免课程</a></c:when>
                                            <c:otherwise>
                                                <a href="#">未知的课程状态</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                                <div class="post-social-media">
                                    <h5 class="share-title">分享此课程给身边的同学叭~</h5>
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
                            <h3 class="comment-title">🟣其他用户对【${courseInfoBo.course.name}】评价：</h3>
                            <ul class="comments-list">
                                <c:forEach items="${courseInfoBo.commentBo}" var="commentBo" varStatus="numInfo">
                                    <li>
                                        <div class="comment-img">
                                            <img src="${commentBo.user.image}" alt="图片加载失败">
                                        </div>
                                        <div class="comment-desc">
                                            <div class="desc-top">
                                                <h6>
                                                    用户姓名：${commentBo.user.name} &ensp;用户编号：${commentBo.user.id}
                                                    <c:forEach begin="1" end="${commentBo.comment.score}">
                                                        <span class="saved"><i class="lni lni-bookmark"></i></span>
                                                    </c:forEach>
                                                </h6>
                                                <span class="date">评论时间：${commentBo.comment.time}</span>
                                                <span style="color: purple;">&ensp;此评论获赞数：${commentBo.comment.good}个</span>
                                                <a href="#" class="reply-link"><i class="lni lni-reply"></i>回复(评论编号${commentBo.comment.id})的评论</a>
                                            </div>
                                            <p>${commentBo.comment.info}</p>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>

                        <div class="comment-form">
                            <h3 class="comment-reply-title">🔴输入您对【${courseInfoBo.course.name}】的评价：</h3>
                            <form>
                                <div class="row">
                                    <div class="col-12">
                                        <div class="form-box form-group">
                                            <textarea name="#" rows="6" class="form-control form-control-custom" placeholder="请在登录后，再进行评论！"></textarea>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-12 col-12">
                                        <div class="form-box form-group">
                                            <input type="number" name="name" min="1" max="5" class="form-control form-control-custom" placeholder="请输入您对此课程的星级评价"/>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-12 col-12">
                                        <div class="button">
                                            <c:choose>
                                                <c:when test="${empty Admin}">
                                                    <button type="submit" class="btn mouse-dir white-bg btn-alt" disabled>登 录 后 才 可 评 论<span class="dir-part"></span></button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button id="doComment" type="submit" class="btn mouse-dir white-bg">点 击 发 送 评 论<span class="dir-part"></span></button>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
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
                                        <a href="#"><img src="/pages/assets/images/course/course-00${item}.jpg" alt="图片加载失败"></a>
                                    </div>
                                    <div class="feed-desc">
                                        <h6 class="post-title"><a href="#">${item}-课程名称：等待后端查询填充，更多详情请联管理员</a></h6>
                                        <span class="time">
                                            <i class="lni lni-calendar"></i>课程价格：￥999 &ensp;限时免费
                                            <br><time:showTime country="中国" city="银川"/>
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
                                <a href="#">标签—${item}</a>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="widget call-us">
                        <div class="content">
                            <c:choose>
                                <c:when test="${isHaveThisCourse}">
                                    <h3 style="color: white;">请及时查看该课程公告！</h3>
                                    <div class="button">
                                        <a href="/app/course?method=getSomeCourseNotice&target=${courseInfoBo.course.id}&courseName=${courseInfoBo.course.name}" class="btn" target="_blank">查看课程公告</a>
                                    </div>

                                </c:when>
                                <c:otherwise>
                                    <h3 style="color: white;">准备好学习此课程了吗？</h3>
                                    <div class="button">
                                        <a href="/app/course?method=userBuyCourseByCourseId&courseId=${courseInfoBo.course.id}" class="btn">购买此课程</a>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </aside>
        </div>
    </div>
</section>

<%--回到顶部按钮--%>
<jsp:include page="goHead.jsp"/>

<jsp:include page="/pages/assets/app/myJs.jsp"/>
</body>
</html>