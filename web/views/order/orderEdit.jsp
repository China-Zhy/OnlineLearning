<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>编辑订单</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layuiadmin/layui/css/layui.css" media="all">
</head>
<body>

<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
    <div class="layui-form-item">
        <label class="layui-form-label">用户编号</label>
        <div class="layui-input-inline">
            <input type="number" name="userId" id="userId" lay-verify="required" placeholder="请输入用户编号" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">课程编号</label>
        <div class="layui-input-inline">
            <input type="number" name="courseId" id="courseId" lay-verify="required" placeholder="请输入课程编号" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">下单时间</label>
        <div class="layui-input-inline">
            <input type="text" name="time" id="time" lay-verify="datetime|required" placeholder="请选择开始时间" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">订单状态</label>
        <div class="layui-input-block">
            <div class="layui-input-inline">
                <select name="state" id="state">
                    <option value="1">等待支付</option>
                    <option value="2">购买成功</option>
                    <option value="3">购买失败</option>
                </select>
            </div>
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
    }).use(['index', 'form', 'upload', 'laydate'], function () {
        var $ = layui.$
            , form = layui.form
            , upload = layui.upload
            , laydate = layui.laydate;

        // 表格顶部时间选择部分
        laydate.render({
            elem: '#time',
            type: 'datetime', // 设置日期时间选择器类型为 datetime
            format: 'yyyy-MM-dd HH:mm:ss' // 指定日期时间格式
        });


        upload.render({
            elem: '#layuiadmin-upload-useradmin'
            , url: layui.setter.base + 'json/upload/demo.js'
            , accept: 'images'
            , method: 'get'
            , acceptMime: 'image/*'
            , done: function (res) {
                $(this.item).prev("div").children("input").val(res.data.src)
            }
        });
    })
</script>
</body>
</html>