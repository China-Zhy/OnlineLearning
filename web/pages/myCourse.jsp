<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/views/others/layuiError.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <title>我的课程</title>
    <jsp:include page="/pages/assets/app/myCss.jsp"/>
</head>

<body>
<%--页面加载动画--%>
<jsp:include page="loading.jsp"/>

<header class="footer">
    <div class="call-action">
        <div class="container">
            <div class="inner-content">
                <div class="row align-items-center">
                    <div class="col-lg-8 col-md-8 col-12">
                        <h2>尊敬的 ${Admin.name}，您总共拥有 ${total} 门 课程</h2>
                        <p>以下是您所拥有的全部课程，学海无涯苦作舟，祝愿您前程似锦，身体健康，早日功成名就！</p>
                    </div>
                    <div class="col-lg-2 col-md-2 col-12">
                        <div class="button">
                            <a href="/app/course?method=showCourseGroupByType" class="btn">返回课程首页</a>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-2 col-12">
                        <div class="button">
                            <a href="/app/course?method=toRechargeScore" class="btn">我的积分记录</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>

<section class="section blog-single">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-12 latest-news-area">
                <div class="row">
                    <jsp:useBean id="courses" scope="request" type="java.util.List"/>
                    <c:forEach items="${courses}" var="course" varStatus="item">
                        <div class="col-lg-6 col-md-6 col-12">
                            <div class="single-news custom-shadow-hover wow fadeInUp" data-wow-delay=".2*${item.count}s">
                                <div class="image">
                                    <img class="thumb" src="${course.image}" alt="课程图片加载失败">
                                </div>
                                <div class="content-body">
                                    <a class="cat" href="#">序号：${item.count}&ensp;编号：${course.id}&ensp;价格：${course.score}</a>
                                    <h4 class="title">
                                        <a href="/app/course?method=showCourseDetailsById&id=${course.id}&courseType=${course.courseType}">${course.name}</a>
                                    </h4>
                                    <p>
                                        <c:choose>
                                            <c:when test="${fn:length(course.info) >= 70}">
                                                ${fn:substring(course.info, 0, 70)}...
                                            </c:when>
                                            <c:otherwise>
                                                ${course.info}
                                            </c:otherwise>
                                        </c:choose>
                                    </p>
                                    <div class="button">
                                        <a href="/app/course?method=showCourseDetailsById&id=${course.id}&courseType=${course.courseType}" class="btn">继续学习此[${course.id}]课程</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="pagination left">
                    <ul class="pagination-list">
                        <li><a href="/app/course?method=getAllCourseByUserId&pageIndex=1&pageSize=6">首页</a></li>
                        <li><a href="/app/course?method=getAllCourseByUserId&pageIndex=${pageNum-1}&pageSize=6">上一页</a></li>
                        <c:forEach begin="1" end="${pages}" var="pageIndex">
                            <c:choose>
                                <c:when test="${pageNum==pageIndex}">
                                    <li class="active"><a href="/app/course?method=getAllCourseByUserId&pageIndex=${pageNum}&pageSize=6">${pageIndex}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="/app/course?method=getAllCourseByUserId&pageIndex=${pageIndex}&pageSize=6">${pageIndex}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <li><a href="/app/course?method=getAllCourseByUserId&pageIndex=${pageNum+1}&pageSize=6">下一页</a></li>
                        <li><a href="/app/course?method=getAllCourseByUserId&pageIndex=${pages}&pageSize=6">尾页</a></li>
                        <li><a disabled>共 ${total} 条</a></li>
                    </ul>
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
                            <h3 style="color: white;">准备好加入<br>Online Learning 了吗？</h3>
                            <div class="button">
                                <a href="#" class="btn" target="_blank">申请成为一名教师</a>
                            </div>
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