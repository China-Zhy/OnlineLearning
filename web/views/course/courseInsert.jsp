<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>新增课程</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="icon" href="/layuiadmin/resource/image/courseInsert.png">
    <link rel="stylesheet" href="/layuiadmin/layui/css/layui.css" media="all">
    <style>
        .layui-upload-img {
            width: 320px;
            height: 320px;
            margin: 0 10px 10px 0;
            position: absolute;
        }
    </style>
</head>
<body>

<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">

    <div class="layui-form-item">
        <label class="layui-form-label">课程名称</label>
        <div class="layui-input-inline">
            <input type="text" name="name" id="name" lay-verify="required" placeholder="请输入课程名称" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-upload-list">
            <img class="layui-upload-img" id="test-upload-normal-img">
            <p id="test-upload-demoText"></p>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">课程类型</label>
            <div class="layui-input-inline">
                <select name="courseType" id="courseType" lay-verify="required">
                    <option value="0">全部类型</option>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">课程积分</label>
        <div class="layui-input-inline">
            <input type="number" name="score" id="score" lay-verify="required" placeholder="请输入相应课程积分" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">课程状态</label>
            <div class="layui-input-inline">
                <select name="state" id="state" lay-verify="required">
                    <option value="1">原价课程</option>
                    <option value="2">折扣课程</option>
                    <option value="3">限免课程</option>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">课程描述</label>
        <div class="layui-input-block">
            <textarea name="info" placeholder="请输入课程描述" class="layui-textarea" style="width: 190px;"></textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">课程封面</label>
        <div class="layui-input-inline">
            <input type="text" name="file" id="file" lay-verify="required" placeholder="请上传图片" autocomplete="off" class="layui-input">
            <input type="text" name="image" id="newImage" hidden="hidden">
        </div>
        <button style="float: left;" type="button" class="layui-btn" id="test-upload-normal">上传图片</button>
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
    }).use(['index', 'form', 'upload'], function () {
        let $ = layui.$, form = layui.form, upload = layui.upload;

        //渲染课程类型下拉菜单
        $(document).ready(function () {
            $.ajax({
                type: 'GET',
                url: '/courseType?method=queryCourseType',
                data: '',
                dataType: 'json',
                success: function (data) {
                    for (let i = 0; i < data.data.length; i++) {
                        let option = document.createElement("option");
                        option.value = data.data[i].id;
                        option.text = data.data[i].name;
                        document.getElementById('courseType').appendChild(option);
                        form.render();  // 渲染layui的下拉菜单(必须要有)
                    }
                },
                error: function (xhr, status, error) {
                    console.log(error); // 控制台打印
                }
            })
        });

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
                $('#newImage').val(res.fileName);
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
    });
</script>
</body>
</html>