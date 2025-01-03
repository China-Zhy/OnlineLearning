<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>添加作业</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layuiadmin/layui/css/layui.css" media="all">
</head>
<body>

<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
    <div class="layui-form-item">
        <label class="layui-form-label">作业标题</label>
        <div class="layui-input-inline">
            <input type="text" name="title" lay-verify="required" placeholder="请输入作业标题" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-inline">
            <div class="layui-form-mid layui-word-aux">必填项*</div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">用户编号</label>
        <div class="layui-input-inline">
            <input type="number" name="userId" lay-verify="required" value="1" autocomplete="off" class="layui-input" disabled>
        </div>
        <div class="layui-inline">
            <div class="layui-form-mid layui-word-aux">待修改*</div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">课程编号</label>
        <div class="layui-input-block">
            <div class="layui-inline">
                <select name="courseId" id="courseId" lay-verify="required">
                    <option value="">选择课程</option>
                    <option value="1">选择课程</option>
                    <option value="2">选择课程</option>
                </select>
            </div>
            <div class="layui-inline">
                <div class="layui-form-mid layui-word-aux">数据未填充！！！</div>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">开始日期</label>
        <div class="layui-input-inline">
            <input type="text" name="create" id="create" lay-verify="datetime|required" placeholder="选择开始日期" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-inline">
            <div class="layui-form-mid layui-word-aux">必填项*</div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">截止日期</label>
        <div class="layui-input-inline">
            <input type="text" name="dateline" id="dateline" lay-verify="datetime|required" placeholder="选择截止日期" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-inline">
            <div class="layui-form-mid layui-word-aux">必填项*</div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">作业描述</label>
        <div class="layui-input-block">
            <textarea name="info" id="info" rows="10" placeholder="请输入作业描述" lay-verify="required" class="layui-textarea"></textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">提交约束</label>
        <div class="layui-input-block">
            <input type="radio" name="again" value="1" title="允许补交" checked>
            <input type="radio" name="again" value="2" title="禁止补交">
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
        var $ = layui.$, form = layui.form, laydate = layui.laydate;

        // 日期组件的渲染
        laydate.render({
            elem: '#create',
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm:ss'
        });
        laydate.render({
            elem: '#dateline',
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm:ss'
        });
    })
</script>
</body>
</html>