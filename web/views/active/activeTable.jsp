<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>活动数据表格</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../../layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../layuiadmin/style/admin.css" media="all">
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md15">
            <div class="layui-card">
                <div class="layui-form layui-card-header layuiadmin-card-header-auto">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label" style="font-weight: bold;">活动标题</label>
                            <div class="layui-input-block">
                                <input type="text" name="title" id="title" placeholder="请输入活动标题" autocomplete="off" class="layui-input" style="letter-spacing: 1px;">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label" style="font-weight: bold;">创建用户</label>
                            <div class="layui-input-block">
                                <input type="number" name="userId" id="userId" placeholder="请输入创建用户" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label" style="font-weight: bold;">开始日期</label>
                            <div class="layui-input-inline">
                                <input type="text" name="create" id="create" placeholder="请选择开始时间" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label" style="font-weight: bold;">截止日期</label>
                            <div class="layui-input-inline">
                                <input type="text" name="deadline" id="deadline" placeholder="请选择截止时间" autocomplete="off" class="layui-input">
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
                    <div class="layui-btn-group OnlineLearning-btn" style="margin-bottom: 10px; font-weight: bold;">
                        <button class="layui-btn" data-type="add">新增数据</button>
                    </div>

                    <!--表格主体-->
                    <table class="layui-hide" id="OnlineLearning" lay-filter="OnlineLearning"></table>

                    <!--通过脚本控制显示照片-->
                    <script type="text/html" id="imageTpl">
                        <img style="display: inline-block; width: 25px; height: 25px;" src={{ d.image }}>
                    </script>

                    <!--表格右侧身体工具栏-->
                    <script type="text/html" id="OnlineLearning-Tools">
                        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="detail">
                            <i class="layui-icon layui-icon-about"></i>查看
                        </a>
                        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">
                            <i class="layui-icon layui-icon-edit"></i>编辑
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

<script src="../../layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '../../layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'table', 'laydate', 'form'], function () {
        let table = layui.table, laydate = layui.laydate, $ = layui.$, form = layui.form;

        // 表格顶部时间选择部分
        laydate.render({
            elem: '#create'
        });

        laydate.render({
            elem: '#deadline'
        });

        // 表格数据显示部分
        table.render({
            elem: '#OnlineLearning',
            url: '/active?method=queryAllActive',
            cols: [[
                {type: 'checkbox', fixed: 'left'},
                {field: 'id', width: 80, title: '编号', sort: true, fixed: 'left', align: "center"},
                {field: 'title', width: 180, title: '活动标题', align: "center"},
                {field: 'info', width: 200, title: '活动内容'},
                {field: 'image', width: 150, title: '活动图片', templet: "#imageTpl", align: "center"},
                {field: 'discount', width: 110, title: '折扣力度', sort: true, align: "center"},
                {field: 'create', width: 160, title: '开始时间', sort: true, align: "center"},
                {field: 'deadline', width: 160, title: '截止时间', sort: true, align: "center"},
                {field: 'userId', width: 120, title: '创建用户', sort: true, align: "center"},
                {title: '更多操作', width: 250, align: 'center', fixed: 'right', toolbar: '#OnlineLearning-Tools'}
            ]],
            page: true
        });

        // 监听表格顶部的搜索操作(改这里)
        form.on('submit(nxu-search)', function (data) {
            // 条件搜索后，根据新数据重载表格
            table.reload('OnlineLearning', {
                url: '/active?method=queryAllActive',
                method: 'post',
                where: data.field  // 装载提交到后台的请求参数
            }, 'data');
            return false;
        });

        // 监听表格复选框选择
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
                    // 使用Ajax异步加载表格顶部form表单中的下拉菜单 改这里！
                    $.ajax({
                        type: 'post',
                        url: '/active?method=deleteActive',
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
                    });
                    obj.del();
                    layer.close(index);
                });
            } else if (obj.event === 'edit') { // 编辑数据(改这里)
                layer.open({
                    type: 2,
                    title: '编辑活动数据',
                    content: '/views/active/activeEdit.jsp',
                    maxmin: true,
                    area: ['700px', '600px'],
                    btn: ['确定', '取消'],
                    success: function (layero, index) {
                        // 给弹出层中的隐藏表达赋值
                        layer.getChildFrame('body', index).find('input[id="id"]').val(data.id);
                    },
                    yes: function (index, layero) {
                        let iframeWindow = window['layui-layer-iframe' + index], submitID = 'LAY-user-front-submit',
                            submit = layero.find('iframe').contents().find('#' + submitID);

                        //监听提交
                        iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                            //提交 Ajax 成功后，静态更新表格中的数据
                            $.ajax({
                                type: 'post',
                                url: '/active?method=updateActive',
                                data: data.field,
                                dataType: 'json',
                                success: function (data) {
                                    if (data.result === 1) {
                                        layer.msg(data.info, {offset: '15px', icon: 1, time: 1000});
                                        table.reload('OnlineLearning'); // 数据刷新，记得改成OnlineLearning
                                    } else {
                                        layer.msg(data.info, {offset: '15px', icon: 2, time: 1000});
                                    }
                                },
                                error: function (xhr, status, error) {
                                    layer.msg('可恶，又出错了')
                                    console.log(error); // 控制台打印
                                }
                            });
                            layer.close(index); //关闭弹层
                        });
                        submit.trigger('click');
                    }
                });
            }
        });

        // 表格的几个小工具
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
            add: function () { // 新增数据(改这里)
                layer.open({
                    type: 2,
                    title: '添加活动数据',
                    content: '/views/active/activeInsert.jsp',
                    maxmin: true,
                    area: ['700px', '600px'],
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        let iframeWindow = window['layui-layer-iframe' + index],
                            submitID = 'LAY-user-front-submit',
                            submit = layero.find('iframe').contents().find('#' + submitID);

                        //监听提交
                        iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                            //提交 Ajax 成功后，静态更新表格中的数据
                            $.ajax({
                                type: 'post',
                                url: '/active?method=insertActive',
                                data: data.field,
                                dataType: 'json',
                                success: function (data) {
                                    if (data.result === 1) {
                                        layer.msg(data.info, {offset: '15px', icon: 1, time: 1000});
                                        table.reload('OnlineLearning'); //数据刷新
                                    } else {
                                        layer.msg(data.info, {offset: '15px', icon: 2, time: 1000});
                                    }
                                },
                                error: function (xhr, status, error) {
                                    layer.msg('出错了')
                                    console.log(error); // 控制台打印
                                }
                            });
                            layer.close(index); //关闭弹层
                        });
                        submit.trigger('click');
                    }
                });
            }
        };

        // 按钮事件相关的控制(勿改)
        $('.OnlineLearning-btn .layui-btn').on('click', function () {
            let type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        // 清空表格头部表单中的全部内容
        $('#clear').click(function () {
            $('#title').val('');
            $('#userId').val('');
            $('#create').val('');
            $('#deadline').val('');
        });
    });
</script>
</body>
</html>