<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>数据表格模板</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="icon" href="/layuiadmin/resource/image/lmgcon.png">
    <link rel="stylesheet" href="/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/layuiadmin/style/admin.css" media="all">
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md15">
            <div class="layui-card">
                <div class="layui-form layui-card-header layuiadmin-card-header-auto">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label" style="font-weight: bold;">用户编号</label>
                            <div class="layui-input-block">
                                <div class="layui-input-inline">
                                    <input type="text" name="userId" id="userId" placeholder="请输入用户编号" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label" style="font-weight: bold;">课程编号</label>
                            <div class="layui-input-block">
                                <div class="layui-input-inline">
                                    <input type="text" name="courseId" id="courseId" placeholder="请输入课程编号" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label" style="font-weight: bold;">评论时间</label>
                            <div class="layui-input-inline">
                                <input type="text" name="time" id="LAY-component-form-group-time" placeholder="选择时间" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline" style="font-weight: bold;">
                            <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="nxu-search">
                                <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i> 查 询
                            </button>
                            <button class="layui-btn layui-btn-primary" id="clear">清空条件</button>
                        </div>
                    </div>
                </div>
                <div class="layui-card-body">

                    <!--表格左侧头部工具栏-->
                    <div class="layui-btn-group OnlineLearning-btn" style="margin-bottom: 10px; font-weight: bold;">
                        <button class="layui-btn layui-btn-primary" data-type="getCheckData">获取选中行的数据</button>
                        <button class="layui-btn layui-btn-primary" data-type="getCheckLength">获取选中行的数量</button>
                        <button class="layui-btn layui-btn-primary" data-type="isAll">验证是否全选</button>
                    </div>

                    <!--表格主体-->
                    <table class="layui-hide" id="OnlineLearning" lay-filter="OnlineLearning"></table>

                    <!--表格右侧身体工具栏-->
                    <script type="text/html" id="OnlineLearning-Tools">
                        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="detail">
                            <i class="layui-icon layui-icon-about"></i>查看
                        </a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">
                            <i class="layui-icon layui-icon-delete"></i>删除
                        </a>
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'table', 'form', 'rate', 'laydate'], function () {
        let table = layui.table, form = layui.form, rate = layui.rate, laydate = layui.laydate, $ = layui.$, layer = layui.layer;

        // 表格数据显示部分
        table.render({
            elem: '#OnlineLearning',
            url: '/comment?method=queryAllComment',
            cols: [[
                {type: 'checkbox', fixed: 'left', align: "center"},
                {field: 'id', width: 80, title: 'ID', sort: true, fixed: 'left', align: "center"},
                {field: 'courseId', width: 80, title: '课程', align: "center"},
                {field: 'userId', width: 80, title: '用户', align: "center"},
                {
                    field: 'score', width: 180, title: '评价星级', align: 'center', sort: true, id: 'rate',
                    templet: function (d) {
                        return '<div id="star' + d.id + '" style="top: -10px;"></div>'
                    }
                },
                {field: 'info', width: 160, title: '评论内容', align: "center"},
                {field: 'time', width: 160, title: '评论时间', sort: true, align: "center"},
                {field: 'good', width: 140, title: '获赞数量', sort: true, align: "center"},
                {title: '更多操作', width: 180, align: 'center', fixed: 'right', toolbar: '#OnlineLearning-Tools'}

            ]],
            page: true,
            done: function (res) {
                let data = res.data;
                for (let item in data) {
                    rate.render({
                        elem: '#star' + data[item].id + '',
                        length: 5,
                        value: data[item].score,
                        theme: '#48466d',
                        text: false,
                        readonly: true
                    });
                }
            }
        });

        // 监听表格顶部的搜索操作(改这里)
        form.on('submit(nxu-search)', function (data) {
            // 条件搜索后，根据新数据重载表格(改这里)
            table.reload('OnlineLearning', {
                url: '/comment?method=queryAllComment',
                method: 'post',
                where: data.field  // 装载提交到后台的请求参数
            }, 'data');
            return false;
        });

        laydate.render({
            elem: '#LAY-component-form-group-time'
        });

        // 监听表格复选框选择(勿改)
        table.on('checkbox(OnlineLearning)', function (obj) {
            // console.log(obj); // 控制台打印
            layer.msg('点击了 ID: ' + obj.data.id + ' 的复选框');
        });

        // 监听表格右侧工具栏事件
        table.on('tool(OnlineLearning)', function (obj) {
            let data = obj.data;
            if (obj.event === 'detail') {
                layer.alert(JSON.stringify(data))
            } else if (obj.event === 'delete') {
                layer.confirm('真的要删除这条数据吗?', function (index) {
                    $(document).ready(function () {
                        $.ajax({
                            type: 'post',
                            url: '/comment?method=deleteComment',
                            data: {'id': obj.data.id},
                            dataType: 'json',
                            success: function (data) {
                                if (data.result === 1) {
                                    layer.msg(data.info, {offset: '15px', icon: 1, time: 1000});
                                } else {
                                    layer.msg(data.info, {offset: '15px', icon: 2, time: 1000});
                                }
                            },
                            error: function (xhr, status, error) {
                                console.log(error); // 控制台打印
                            }
                        })
                    })
                    obj.del();
                    layer.close(index);
                });
            }
        });

        // 表格头部的几个小工具
        let active = {
            getCheckData: function () { //获取选中数据(勿改)
                let checkStatus = table.checkStatus('OnlineLearning'), data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            },
            getCheckLength: function () { //获取选中数目(勿改)
                let checkStatus = table.checkStatus('OnlineLearning'), data = checkStatus.data;
                layer.msg('选中了: ' + data.length + ' 个数据');
            },
            isAll: function () { //验证是否全选(勿改)
                let checkStatus = table.checkStatus('OnlineLearning');
                layer.msg(checkStatus.isAll ? '已全选' : '未全选')
            },

        };

        // 按钮事件相关的控制(勿改)
        $('.OnlineLearning-btn .layui-btn').on('click', function () {
            let type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        // 清空表格头部表单中的全部内容
        $('#clear').click(function () {
            $('#userId').val('');
            $('#courseId').val('');
            $('#LAY-component-form-group-time').val('');
            form.render("select");  // 渲染layui的下拉菜单(必须要有)
        });
    });
</script>
</body>
</html>