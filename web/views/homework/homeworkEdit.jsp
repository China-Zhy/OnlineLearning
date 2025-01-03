<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>编辑作业</title>
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
            <input type="text" name="title" id="title" lay-verify="required" placeholder="请输入作业标题" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-inline">
            <div class="layui-form-mid layui-word-aux">必填项*</div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">用户编号</label>
        <div class="layui-input-inline">
            <input type="number" name="userId" id="userId" lay-verify="required" autocomplete="off" class="layui-input" disabled>
        </div>
        <div class="layui-inline">
            <div class="layui-form-mid layui-word-aux">不可更改</div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">课程编号</label>
            <div class="layui-input-inline">
                <select name="courseId" id="courseId" lay-verify="required">
                    <option value="">选择课程</option>
                    <option value="1">课程姓名1</option>
                    <option value="2">课程姓名2</option>
                    <option value="3">课程姓名3</option>
                </select>
            </div>
            <div class="layui-inline">
                <div class="layui-form-mid layui-word-aux">必填项*</div>
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
            <input type="radio" name="again" value="1" title="允许补交">
            <input type="radio" name="again" value="2" title="禁止补交">
        </div>
    </div>

    <!--这个用来存储ID，然后一起传到后端，用来修改信息，所以设为隐藏-->
    <input type="number" name="id" id="id" hidden="hidden">

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

        // 使用Ajax异步加载数据信息(改这里)
        $(document).ready(function () {
            $.ajax({
                type: 'POST',
                url: '/homework?method=getOneHomework',
                data: {'id': $('#id').val()},
                dataType: 'json',
                success: function (data) {
                    let entity = data.data; // 获取后端传过来的实体信息
                    $('#title').val(entity.title);
                    $('#courseId').val(entity.courseId);
                    $('#userId').val(entity.userId);
                    $('#create').val(entity.create);
                    $('#dateline').val(entity.dateline);
                    $('#info').val(entity.info);
                    $(":radio[name='again'][value='" + entity.again + "']").prop("checked", "checked"); // 单选按钮的选中
                    form.render();  // 渲染form样式
                },
                error: function (xhr, status, error) {
                    layer.msg('Ajax请求失败啦', {offset: '15px', icon: 5, time: 1000});
                    console.log(error); // 控制台打印错误
                }
            });
        });
    })
</script>
</body>
</html>