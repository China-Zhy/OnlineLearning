<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head lang="en">
    <title>探索更多课程</title>
    <jsp:include page="/pages/assets/app/myCss.jsp"/>
</head>
<body>
<%--页面加载动画--%>
<jsp:include page="/pages/loading.jsp"/>

<!-- 头部搜索框 -->
<section class="newsletter-area section">
    <div class="container">
        <div class="row ">
            <div class="col-lg-8 offset-lg-2 col-12">
                <div class="section-title">
                    <span class="wow fadeInDown" data-wow-delay=".2s">搜索更多同类课程</span>
                    <h2 class="wow fadeInUp" data-wow-delay=".4s">输入课程名模糊查找</h2>
                    <h3 class="gray-bg">搜索更多同类课程</h3>
                </div>
                <div class="subscribe-text wow fadeInUp" data-wow-delay=".2s">
                    <form action="/app/course?method=searchMoreCourse" method="post" class="newsletter-inner">
                        <input name="name" placeholder="输入课程名模糊查找..." class="common-input" onfocus="this.placeholder = ''" onblur="this.placeholder = '输入课程名模糊查找...'" type="text">
                        <input name="courseType" type="text" value="${courses[0].courseType}" hidden="hidden">
                        <input name="typeName" type="text" value="${typeName}" hidden="hidden">
                        <div class="button">
                            <button class="btn" type="submit">搜 索 课 程</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- 课程信息和分页按钮 -->
<div class="latest-news-area section">
    <!-- 课程信息 -->
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="section-title">
                    <span class="wow fadeInDown" data-wow-delay=".2s">课程类型名称</span>
                    <h2 class="wow fadeInUp" data-wow-delay=".4s">${typeName}</h2>
                    <h3 class="gray-bg">${typeName}</h3>
                    <p class="wow fadeInUp" data-wow-delay=".6s">此处可放一些别的信息，现在简单演示一下。</p>
                </div>
            </div>
        </div>
        <div class="row">
            <c:forEach items="${courses}" var="course" varStatus="item">
                <div class="col-lg-4 col-md-6 col-12">
                    <div class="single-news custom-shadow-hover wow fadeInUp" data-wow-delay=".${item.count*2}s">
                        <div class="image">
                            <img class="thumb" src="${course.image}" alt="课程图片加载失败">
                        </div>
                        <div class="content-body">
                            <a class="cat" href="#">${course.name}</a>
                            <h4 class="title"><a href="#">￥ ${course.score} 积分</a></h4>
                            <p>${course.info}</p>
                            <div class="button">
                                <a href="/app/course?method=showCourseDetailsById&id=${course.id}&courseType=${typeName}" class="btn">了 解 此 课 程</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <!-- 分页按钮 -->
    <div class="pagination center">
        <ul class="pagination-list">
            <c:choose>
                <c:when test="${pageNum == 1}">
                    <li style="pointer-events: none;"><a href="/app/course?method=searchMoreCourse&pageIndex=1">首页</a></li>
                    <li style="pointer-events: none;"><a href="/app/course?method=searchMoreCourse&pageIndex=${pageNum-1}">上一页</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/app/course?method=searchMoreCourse&pageIndex=1">首页</a></li>
                    <li><a href="/app/course?method=searchMoreCourse&pageIndex=${pageNum-1}">上一页</a></li>
                </c:otherwise>
            </c:choose>

            <c:forEach begin="1" end="${pages}" var="pageIndex">
                <c:choose>
                    <c:when test="${pageNum==pageIndex}">
                        <li class="active"><a href="/app/course?method=searchMoreCourse&pageIndex=${pageNum}">${pageIndex}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/app/course?method=searchMoreCourse&pageIndex=${pageIndex}">${pageIndex}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:choose>
                <c:when test="${pageNum == pages}">
                    <li style="pointer-events: none;"><a href="/app/course?method=searchMoreCourse&pageIndex=${pageNum+1}">下一页</a></li>
                    <li style="pointer-events: none;"><a href="/app/course?method=searchMoreCourse&pageIndex=${pages}">尾页</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/app/course?method=searchMoreCourse&pageIndex=${pageNum+1}">下一页</a></li>
                    <li><a href="/app/course?method=searchMoreCourse&pageIndex=${pages}">尾页</a></li>
                </c:otherwise>
            </c:choose>

            <li><a disabled>共 ${count} 条</a></li>
            <li><a disabled>15条/页</a></li>
        </ul>
    </div>
</div>

<%--回到顶部按钮--%>
<jsp:include page="goHead.jsp"/>

<jsp:include page="/pages/assets/app/myJs.jsp"/>
</body>
</html>