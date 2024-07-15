<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/views/others/layuiError.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>完善用户信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="icon" href="/layuiadmin/resource/image/icon.png">
    <link rel="stylesheet" href="/layuiadmin/layui/css/layui.css" media="all">
    <style>
        .layui-upload-img {
            width: 197px;
            height: 197px;
            margin: 0 10px 10px 0;
            position: absolute;
        }
    </style>
</head>
<body>
<form class="layui-form" action="" lay-filter="component-form-element">
    <div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">

        <div class="layui-form-item">
            <label class="layui-form-label">手机号码</label>
            <div class="layui-input-inline">
                <input type="text" name="phone" lay-verify="phone" value="${phone}" autocomplete="off" class="layui-input" style="color: green;">
            </div>
            <div class="layui-upload-list">
                <img class="layui-upload-img" id="test-upload-normal-img">
                <p id="test-upload-demoText"></p>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">账户密码</label>
            <div class="layui-input-inline">
                <input type="password" name="password" value="${password}" autocomplete="off" class="layui-input" style="color: green;">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">用户昵称</label>
            <div class="layui-input-inline">
                <input type="text" name="name" lay-verify="required" placeholder="请输入昵称" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">电子邮箱</label>
            <div class="layui-input-inline">
                <input type="text" name="email" lay-verify="email" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">用户头像</label>
            <div class="layui-input-inline">
                <input type="text" name="file" id="file" lay-verify="required" placeholder="请上传图片" autocomplete="off" class="layui-input">
                <input type="text" name="image" id="image" hidden="hidden">
            </div>
            <button style="float: left;" type="button" class="layui-btn" id="test-upload-normal">上传图片</button>
        </div>

        <div class="layui-form-item" lay-filter="sex">
            <label class="layui-form-label">选择性别</label>
            <div class="layui-input-block">
                <input type="radio" name="gender" value="1" title="男" checked>
                <input type="radio" name="gender" value="2" title="女">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">其它信息</label>
            <div class="layui-input-block">
                <textarea name="info" placeholder="填写您的其他信息..." class="layui-textarea" style="width: 500px;"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="component-form-element">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>
</form>

<script src="/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'form', 'upload'], function () {
        let $ = layui.$, form = layui.form, upload = layui.upload;

        form.render();

        //普通图片上传
        let uploadInst = upload.render({
            elem: '#test-upload-normal',
            url: '/uploadServlet',
            processData: false,
            contentType: false,
            before: function (obj) {
                obj.preview(function (index, file, result) {
                    $('#test-upload-normal-img').attr('src', result); //图片链接（base64）
                });
            },
            done: function (res) {
                //如果上传失败
                if (res.code > 0) {
                    return layer.msg('上传失败', {offset: '15px', icon: 2, time: 1000});
                }
                //上传成功
                layer.msg('上传成功', {offset: '15px', icon: 1, time: 1000});
                $('#file').val(res.fileName);
                $('#image').val(res.fileName);
            },
            error: function () {
                //演示失败状态，并实现重传
                let demoText = $('#test-upload-demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">请重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });

        // 进行用户注册
        form.on('submit(component-form-element)', function (obj) {
            $.ajax({
                type: 'POST',
                url: '/user?method=doCompleteUserInfo',
                data: obj.field,
                dataType: 'json',
                success: function (res) {
                    if (res.result) {
                        layer.msg(res.msg, {offset: '15px', icon: 1, time: 1000}, function () {
                            window.location.href = '/views/user/userLogin.jsp';    // 前往登录页面
                        });
                    } else {
                        layer.msg(res.msg, {offset: '15px', icon: 2, time: 1000});  // 用户信息完善失败(注册失败)
                    }
                },
                error: function (xhr, status, error) {
                    layer.msg('Ajax请求失败啦', {offset: '15px', icon: 5, time: 1000});
                    console.log(error); // 控制台打印错误
                }
            });
            return false;
        });
    });
</script>
</body>
</html>