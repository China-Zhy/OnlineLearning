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
        <label class="layui-form-label">公告标题</label>
        <div class="layui-input-inline">
            <input type="text" name="title" id="title" lay-verify="required" placeholder="请输入公告标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">公告内容</label>
        <div class="layui-input-block">
            <textarea name="info" id="info" placeholder="公告内容" lay-verify="required" class="layui-textarea"></textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">截止时间</label>
        <div class="layui-input-inline">
            <input type="text" name="dateline" id="dateline" lay-verify="datetime|required" placeholder="选择截止日期" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">公告类型</label>
        <div class="layui-input-block">
            <div class="layui-inline">
                <select name="target" id="target" lay-verify="required">
                    <option value="-1">选择类型</option>
                    <option value="0">系统公告</option>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">发布人</label>
        <div class="layui-input-block">
            <div class="layui-inline">
                <select name="userId" id="userId" lay-verify="required">
                    <option value="">选择发布人</option>
                </select>
            </div>
        </div>
    </div>

    <input type="number" name="id" id="id" hidden="hidden">

    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">
    </div>
</div>

<script src="/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '/layuiadmin/'
    }).extend({
        index: 'lib/index'
    }).use(['index', 'form', 'laydate'], function () {
        var $ = layui.$, form = layui.form, laydate = layui.laydate;

        laydate.render({
            elem: '#dateline',
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm:ss'
        });
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
                        document.getElementById('target').appendChild(option);
                    }
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
                            }
                            $.ajax({
                                type: 'POST',
                                url: '/notice?method=getOneNotice',
                                data: {'id': $('#id').val()},
                                dataType: 'json',
                                success: function (data) {
                                    let entity = data.data;
                                    $('#title').val(entity.title);
                                    $('#info').val(entity.info);
                                    $('#dateline').val(entity.dateline);
                                    $('#target').val(entity.target);
                                    $('#userId').val(entity.userId);
                                    form.render();  // 渲染form样式
                                },
                                error: function (xhr, status, error) {
                                    layer.msg('Ajax请求失败啦', {offset: '15px', icon: 5, time: 1000});
                                    console.log(error); // 控制台打印错误
                                }
                            });
                        },
                        error: function (xhr, status, error) {
                            console.log(error); // 控制台打印
                        }
                    });
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