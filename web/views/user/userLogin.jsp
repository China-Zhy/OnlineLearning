<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/views/others/layuiError.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>欢迎登录 Online Learning</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="/layuiadmin/style/login.css" media="all">
</head>
<body style="font-weight: bold;">

<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">
    <!--主体部分-->
    <div class="layadmin-user-login-main">
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <h2 style="font-weight: bold;">Online Learning</h2>
        </div>
        <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-username"></label>
                <input type="text" name="account" id="LAY-user-login-username" lay-verify="required" placeholder="手机号/邮箱号" class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
                <input type="password" name="password" id="LAY-user-login-password" lay-verify="required" placeholder="密码" class="layui-input">
            </div>
            <div class="layui-form-item">
                <div class="layui-row">
                    <div class="layui-col-xs7">
                        <label class="layadmin-user-login-icon layui-icon layui-icon-vercode" for="LAY-user-login-vercode"></label>
                        <input type="text" name="vercode" id="LAY-user-login-vercode" lay-verify="required" placeholder="图形验证码" class="layui-input">
                    </div>
                    <div class="layui-col-xs5">
                        <div style="margin-left: 10px;">
                            <img src="https://www.oschina.net/action/user/captcha" class="layadmin-user-login-codeimg" id="LAY-user-get-vercode">
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-form-item" style="margin-bottom: 20px;">
                <input type="checkbox" name="remember" lay-skin="primary" title="记住密码">
                <a href="forget.html" class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;">找回密码</a>
            </div>
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-login-submit">登&ensp;录</button>
            </div>
            <div class="layui-trans layui-form-item layadmin-user-login-other">
                <label>其他登录方式</label>
                <a href="javascript:void(0);"><i class="layui-icon layui-icon-login-qq" style="font-weight: normal;"></i></a>
                <a href="javascript:void(0);"><i class="layui-icon layui-icon-login-wechat" style="font-weight: normal;"></i></a>
                <a href="javascript:void(0);"><i class="layui-icon layui-icon-login-weibo" style="font-weight: normal;"></i></a>
                <a href="/views/user/userRegister.jsp" class="layadmin-user-jump-change layadmin-link">注册帐号</a>
            </div>
        </div>
    </div>
    <!--页脚部分-->
    <div class="layui-trans layadmin-user-login-footer">
        <p><a href="https://webvpn.nxu.edu.cn" target="_blank">© 2024 webvpn.nxu.edu.cn</a></p>
    </div>
</div>

<script src="/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'user'], function () {
        var admin = layui.admin, form = layui.form;
        form.render();

        // layer.msg('验证码还没有弄', { offset: '15px', icon: 1});
        // layer.msg(JSON.stringify(res));

        form.on('submit(LAY-user-login-submit)', function (obj) {
            admin.req({
                url: '/user?method=login',
                data: obj.field,
                done: function (res) {
                    if (res.result) {
                        layer.msg(res.msg, {offset: '15px', icon: 1, time: 1000}, function () {
                            location.href = '/';
                        });
                    } else {
                        layer.msg(res.msg, {offset: '15px', icon: 2, time: 1000});
                    }
                }
            });
        });
    });
</script>
</body>
</html>