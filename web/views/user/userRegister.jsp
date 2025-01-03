<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/views/others/layuiError.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>欢迎注册 Online Learning</title>
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
                <label class="layadmin-user-login-icon layui-icon layui-icon-cellphone" for="LAY-user-login-cellphone"></label>
                <input type="text" name="phone" id="LAY-user-login-cellphone" lay-verify="phone" placeholder="手机号" class="layui-input">
            </div>
            <div class="layui-form-item">
                <div class="layui-row">
                    <div class="layui-col-xs7">
                        <label class="layadmin-user-login-icon layui-icon layui-icon-vercode" for="LAY-user-login-vercode"></label>
                        <input type="text" name="vercode" id="LAY-user-login-vercode" lay-verify="required" placeholder="验证码" class="layui-input">
                    </div>
                    <div class="layui-col-xs5">
                        <div style="margin-left: 10px;">
                            <button type="button" class="layui-btn layui-btn-primary layui-btn-fluid" id="LAY-user-getsmscode">
                                获取验证码
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
                <input type="password" name="password" id="LAY-user-login-password" lay-verify="pass" placeholder="密码" class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-repass"></label>
                <input type="password" name="repass" id="LAY-user-login-repass" lay-verify="required" placeholder="确认密码" class="layui-input">
            </div>
            <div class="layui-form-item">
                <input type="checkbox" name="agreement" lay-skin="primary" title="同意用户协议" checked>
            </div>
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-reg-submit">注&ensp;册
                </button>
            </div>
            <div class="layui-trans layui-form-item layadmin-user-login-other">
                <label>社交账号注册</label>
                <a href="#"><i class="layui-icon layui-icon-login-qq" style="font-weight: normal;"></i></a>
                <a href="#"><i class="layui-icon layui-icon-login-wechat" style="font-weight: normal;"></i></a>
                <a href="#"><i class="layui-icon layui-icon-login-weibo" style="font-weight: normal;"></i></a>
                <a href="/views/user/userLogin.jsp" class="layadmin-user-jump-change layadmin-link layui-hide-xs">用已有帐号登录</a>
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

        form.on('submit(LAY-user-reg-submit)', function (obj) {
            //确认密码
            if (obj.field.password !== obj.field.repass) {
                return layer.msg('两次密码输入不一致！');
            }
            //是否同意用户协议
            if (!obj.field.agreement) {
                return layer.msg('您必须同意用户协议才能注册！');
            }

            //请求接口
            admin.req({
                url: '/user?method=register',
                data: obj.field,
                done: function (res) {
                    if (res.result) {
                        layer.msg(res.msg, {offset: '15px', icon: 1, time: 1000}, function () {
                            window.location.href = '/user?method=toCompleteUserInfo&phone=' + res.phone + '&password=' + res.password;
                        });
                    } else {
                        layer.msg(res.msg, {offset: '15px', icon: 2, time: 1000});
                    }
                }
            });
            return false;
        });
    });
</script>
</body>
</html>