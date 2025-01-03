<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>添加公告</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layuiadmin/layui/css/layui.css" media="all">
</head>
<body>

<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
    <div class="layui-form-item">
        <label class="layui-form-label">标题：</label>
        <div class="layui-input-inline">
            <input type="text" name="title" id="title" lay-verify="required" placeholder="请输入公告标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">内容：</label>
        <div class="layui-input-block">
            <textarea name="info" placeholder="公告内容" lay-verify="required" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">截止时间：</label>
        <div class="layui-input-inline">
            <input type="text" name="dateline" id="LAY-component-form-group-dateline" lay-verify="datetime|required" placeholder="请选择截止时间" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">课程名称：</label>
        <div class="layui-input-inline">
            <select name="course" id="courseId" lay-verify="required" lay-search="">
                <option value="">选择课程</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">发布人：</label>
        <div class="layui-input-inline">
            <select name="userId" id="userId" lay-verify="required" lay-search="">
                <option value=""></option>
            </select>
        </div>
    </div>
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
    }).use(['index', 'form', 'laydate'], function () {
        var $ = layui.$
            , form = layui.form
            , laydate = layui.laydate;
        laydate.render({
            elem: '#LAY-component-form-group-dateline'
            , type: 'datetime'
            , format: 'yyyy-MM-dd HH:mm:ss'
        });
        // 使用Ajax异步加载表格顶部form表单中的下拉菜单
        $(document).ready(function () {
            $.ajax({
                type: 'GET',
                url: '/course?method=queryCourse',
                data: '',
                dataType: 'json',
                success: function (data) {
                    for (let i = 0; i < data.data.length; i++) {
                        let option = document.createElement("option");
                        option.value = data.data[i].id;
                        option.text = data.data[i].name;
                        document.getElementById('courseId').appendChild(option);
                        form.render("select");  // 渲染layui的下拉菜单
                    }
                },
                error: function (xhr, status, error) {
                    console.log(error); // 控制台打印
                }
            });

            $.ajax({
                type: 'GET',
                url: '/user?method=queryAllUser',
                data: '',
                dataType: 'json',
                success: function (data) {
                    for (let i = 0; i < data.data.length; i++) {
                        let option = document.createElement("option");
                        option.value = data.data[i].id;
                        option.text = data.data[i].name;
                        document.getElementById('userId').appendChild(option);
                        form.render("select");  // 渲染layui的下拉菜单(必须要有)
                    }
                },
                error: function (xhr, status, error) {
                    console.log(error); // 控制台打印
                }
            });
        });
    })
</script>
</body>
</html>