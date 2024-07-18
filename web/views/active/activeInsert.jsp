<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>活动新增</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
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

<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">

    <div class="layui-form-item">
        <label class="layui-form-label">活动标题</label>
        <div class="layui-input-inline">
            <input type="text" name="title" id="title" lay-verify="required" placeholder="请输入活动标题" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-upload-list">
            <img class="layui-upload-img" id="test-upload-normal-img">
            <p id="test-upload-demoText"></p>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">开始时间</label>
        <div class="layui-input-inline">
            <input type="text" name="create" id="create" lay-verify="datetime|required" placeholder="请选择开始时间" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">截止时间</label>
        <div class="layui-input-inline">
            <input type="text" name="deadline" id="deadline" lay-verify="datetime|required" placeholder="请选择截止时间" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">折扣力度</label>
        <div class="layui-input-inline">
            <input type="number" name="discount" id="discount" lay-verify="required" placeholder="请输入折扣力度" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">活动图片</label>
        <div class="layui-input-inline">
            <input type="text" name="file" id="file" lay-verify="required" placeholder="请上传活动图片" autocomplete="off" class="layui-input">
            <input type="text" name="image" id="image" hidden="hidden">
        </div>
        <button style="float: left;" type="button" class="layui-btn" id="test-upload-normal">上传图片</button>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">活动内容</label>
        <div class="layui-input-block" style="width: 400px">
            <textarea type="text" name="info" id="info" lay-verify="required" placeholder="请输入活动内容" class="layui-textarea"></textarea>
        </div>
    </div>

    <!--这个用来存储ID，然后一起传到后端，用来修改信息，所以设为隐藏-->
    <input type="number" name="userId" id="userId" hidden="hidden">

    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">
    </div>
</div>

<script src="/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'form', 'upload', 'laydate'], function () {
        var $ = layui.$
            , form = layui.form
            , upload = layui.upload
            , laydate = layui.laydate;

        // 表格顶部时间选择部分
        laydate.render({
            elem: '#create',
            type: 'datetime', // 设置日期时间选择器类型为 datetime
            format: 'yyyy-MM-dd HH:mm:ss' // 指定日期时间格式
        });

        laydate.render({
            elem: '#deadline',
            type: 'datetime', // 设置日期时间选择器类型为 datetime
            format: 'yyyy-MM-dd HH:mm:ss' // 指定日期时间格式
        });

        //普通图片上传
        let uploadInst = upload.render({
            elem: '#test-upload-normal',
            url: '/uploadServlet',
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
    })
</script>
</body>
</html>