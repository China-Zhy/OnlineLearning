<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>OnlineLearning后台管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/layuiadmin/style/admin.css" media="all">
</head>
<body class="layui-layout-body" style="font-weight: bold;">

<div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <!-- 头部左端功能按钮 -->
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item layadmin-flexible" lay-unselect>
                    <a href="#" layadmin-event="flexible" title="伸缩边栏"><i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i></a>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="/app/course?method=showCourseGroupByType" target="_blank" title="前台页面"><i class="layui-icon layui-icon-website"></i></a>
                </li>
                <li class="layui-nav-item" lay-unselect>
                    <a href="#" layadmin-event="refresh" title="刷新页面"><i class="layui-icon layui-icon-refresh-3"></i></a>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <input type="text" placeholder="输入关键字进行搜索..." autocomplete="off" class="layui-input layui-input-search" layadmin-event="search" lay-action="template/search.html?keywords=">
                </li>
            </ul>

            <!-- 头部右端右端按钮 -->
            <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">
                <li class="layui-nav-item" lay-unselect>
                    <a lay-href="/app/message/index.html" layadmin-event="message" lay-text="消息中心">
                        <i class="layui-icon layui-icon-notice"></i>
                        <!-- 如果有新消息，则显示小圆点 -->
                        <span class="layui-badge-dot"></span>
                    </a>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="#" layadmin-event="theme"><i class="layui-icon layui-icon-theme"></i></a>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="#" layadmin-event="note"><i class="layui-icon layui-icon-note"></i></a>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="#" layadmin-event="fullscreen"><i class="layui-icon layui-icon-screen-full"></i></a>
                </li>
                <li class="layui-nav-item" lay-unselect>
                    <a href="#"><cite>${Admin.name}</cite></a>
                    <dl class="layui-nav-child">
                        <dd><a lay-href="">基本资料</a></dd>
                        <dd><a lay-href="">修改密码</a></dd>
                        <hr/>
                        <dd style="text-align: center;">
                            <a href="/user?method=logout" onclick="layer.msg('退出成功', {offset: '15px', icon: 1, time: 1000});">退出登录</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="#" layadmin-event="about"><i class="layui-icon layui-icon-more-vertical"></i></a>
                </li>
                <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-unselect>
                    <a href="#" layadmin-event="more"><i class="layui-icon layui-icon-more-vertical"></i></a>
                </li>
            </ul>
        </div>

        <!-- 左侧竖列功能菜单 -->
        <div class="layui-side layui-side-menu" style="letter-spacing: 1px;">
            <div class="layui-side-scroll">
                <div class="layui-logo" lay-href="/views/others/application.jsp">
                    <span style="font-weight: bold;">学习平台后端管理</span>
                </div>
                <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">
                    <li data-name="home" class="layui-nav-item layui-nav-itemed layui-this">
                        <a href="#" lay-href="/views/others/application.jsp" lay-tips="后台主页" lay-direction="2">
                            <i class="layui-icon layui-icon-home"></i><cite>后台主页</cite>
                        </a>
                    </li>

                    <li data-name="course" class="layui-nav-item">
                        <a href="#" lay-tips="课程管理" lay-direction="2">
                            <i class="layui-icon layui-icon-template"></i><cite>课程管理</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd><a lay-href="/views/course/courseTable.jsp">课程信息</a></dd>
                            <dd><a lay-href="/views/course/courseTypeTable.jsp">课程类型</a></dd>
                            <dd><a lay-href="/views/course/courseCodeTable.jsp">课程邀请码</a></dd>
                        </dl>
                    </li>

                    <li data-name="homework" class="layui-nav-item">
                        <a href="#" lay-href="/views/homework/homeworkTable.jsp" lay-tips="作业管理" lay-direction="2">
                            <i class="layui-icon layui-icon-app"></i><cite>作业管理</cite>
                        </a>
                    </li>

                    <li data-name="file" class="layui-nav-item">
                        <a href="#" lay-href="/views/file/fileTable.jsp" lay-tips="文件管理" lay-direction="2">
                            <i class="layui-icon layui-icon-file-b"></i><cite>文件管理</cite>
                        </a>
                    </li>

                    <li data-name="comment" class="layui-nav-item">
                        <a href="#" lay-href="/views/comment/commentTable.jsp" lay-tips="评论管理" lay-direction="2">
                            <i class="layui-icon layui-icon-dialogue"></i><cite>评论管理</cite>
                        </a>
                    </li>

                    <li data-name="notice" class="layui-nav-item">
                        <a href="#" lay-href="/views/notice/noticeTable.jsp" lay-tips="公告管理" lay-direction="2">
                            <i class="layui-icon layui-icon-notice"></i><cite>公告管理</cite>
                        </a>
                    </li>

                    <li data-name="active" class="layui-nav-item">
                        <a href="#" lay-href="/views/active/activeTable.jsp" lay-tips="活动管理" lay-direction="2">
                            <i class="layui-icon layui-icon-flag"></i><cite>活动管理</cite>
                        </a>
                    </li>

                    <li data-name="order" class="layui-nav-item">
                        <a href="#" lay-href="/views/order/orderTable.jsp" lay-tips="订单管理" lay-direction="2">
                            <i class="layui-icon layui-icon-cart"></i><cite>订单管理</cite>
                        </a>
                    </li>

                    <li data-name="score" class="layui-nav-item">
                        <a href="#" lay-href="/views/points/pointsTable.html" lay-tips="积分管理" lay-direction="2">
                            <i class="layui-icon layui-icon-rmb"></i><cite>积分管理</cite>
                        </a>
                    </li>

                    <c:if test="${not empty Admin}">
                        <c:if test="${Admin.type==1 || Admin.type==2}">
                            <li data-name="user" class="layui-nav-item">
                                <a href="#" lay-href="/views/user/userTable.jsp" lay-tips="用户管理" lay-direction="2">
                                    <i class="layui-icon layui-icon-user"></i><cite>用户管理</cite>
                                </a>
                            </li>

                            <li data-name="setting" class="layui-nav-item">
                                <a href="#" lay-tips="基本设置" lay-direction="2">
                                    <i class="layui-icon layui-icon-set"></i><cite>基本设置</cite>
                                </a>
                                <dl class="layui-nav-child">
                                    <dd class="layui-nav-itemed">
                                        <a href="#">页面设置1</a>
                                        <dl class="layui-nav-child">
                                            <dd><a lay-href="/views/user/userLogin.jsp">登录页面</a></dd>
                                            <dd><a lay-href="/views/user/userRegister.jsp">注册页面</a></dd>
                                        </dl>
                                    </dd>
                                    <dd class="layui-nav-itemed">
                                        <a href="#">页面设置2</a>
                                        <dl class="layui-nav-child">
                                            <dd><a lay-href="/views/others/layui404.jsp">404页面</a></dd>
                                            <dd><a lay-href="/views/others/layuiError.jsp">错误页面</a></dd>
                                        </dl>
                                    </dd>
                                </dl>
                            </li>
                        </c:if>
                    </c:if>

                    <li data-name="author" class="layui-nav-item">
                        <a href="#" lay-href="https://webvpn.nxu.edu.cn" lay-tips="关于作者" lay-direction="2">
                            <i class="layui-icon layui-icon-service"></i><cite>关于作者</cite>
                        </a>
                    </li>
                </ul>
            </div>
        </div>

        <!-- 页面标签控制 下拉菜单(选项卡最右端) -->
        <div class="layadmin-pagetabs" id="LAY_app_tabs">
            <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-down">
                <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
                    <li class="layui-nav-item" lay-unselect>
                        <a href="#"></a>
                        <dl class="layui-nav-child layui-anim-fadein">
                            <dd layadmin-event="closeThisTabs"><a href="#">关闭当前标签页</a></dd>
                            <dd layadmin-event="closeOtherTabs"><a href="#">关闭其它标签页</a></dd>
                            <dd layadmin-event="closeAllTabs"><a href="#">关闭全部标签页</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
            <!-- 后台默认页面的设置 -->
            <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
                <ul class="layui-tab-title" id="LAY_app_tabsheader">
                    <li lay-id="/views/others/application.jsp" lay-attr="/views/others/application.jsp" class="layui-this">
                        <i class="layui-icon layui-icon-home"></i></li>
                </ul>
            </div>
        </div>

        <!-- 主体区域内容 -->
        <div class="layui-body" id="LAY_app_body">
            <div class="layadmin-tabsbody-item layui-show">
                <iframe src="/views/others/application.jsp" frameborder="0" class="layadmin-iframe"></iframe>
            </div>
        </div>

        <!-- 辅助元素，一般用于移动设备下遮罩 -->
        <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
</div>

<script src="/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use('index');
</script>
</body>
</html>