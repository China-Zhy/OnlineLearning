<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>订单数据表格</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
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
                                <input type="number" name="userId" id="userId" placeholder="请输入用户编号" autocomplete="off" class="layui-input" style="letter-spacing: 1px;">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label" style="font-weight: bold;">课程编号</label>
                            <div class="layui-input-block">
                                <input type="number" name="courseId" id="courseId" placeholder="请输入课程编号" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label" style="font-weight: bold;">下单时间</label>
                            <div class="layui-input-inline">
                                <input type="text" name="time" id="time" placeholder="请选择下单时间" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label" style="font-weight: bold;">订单状态</label>
                            <div class="layui-input-block">
                                <select name="state" id="state">
                                    <option value="0">不限类型</option>
                                    <option value="1">等待支付</option>
                                    <option value="2">购买成功</option>
                                    <option value="3">购买失败</option>
                                </select>
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

                    <!--脚本控制显示不同状态样式-->
                    <script type="text/html" id="buttonTpl">
                        {{#  if(d.state == 2){ }}
                        <button class="layui-btn layui-btn-xs">购买成功</button>
                        {{#  } else if(d.state == 3){ }}
                        <button class="layui-btn layui-btn-danger layui-btn-xs">购买失败</button>
                        {{#  } else { }}
                        <button class="layui-btn layui-btn-primary layui-btn-xs">等待支付</button>
                        {{#  } }}
                    </script>

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
    }).use(['index', 'table', 'laydate', 'form'], function () {
        let table = layui.table, laydate = layui.laydate, $ = layui.$, form = layui.form;

        // 表格顶部时间选择部分
        laydate.render({
            elem: '#time'
        });

        // 表格数据显示部分
        table.render({
            elem: '#OnlineLearning',
            url: '/order?method=queryAllOrder',
            cols: [[
                {type: 'checkbox', fixed: 'left'},
                {field: 'id', width: 80, title: '编号', sort: true, fixed: 'left', align: "center"},
                {field: 'userId', width: 200, title: '用户编号', align: "center"},
                {field: 'courseId', width: 220, title: '课程编号', align: "center"},
                {field: 'time', width: 160, title: '下单时间', sort: true, align: "center"},
                {field: 'state', width: 120, title: '订单状态', sort: true, templet: "#buttonTpl", align: "center"},
                {title: '更多操作', width: 250, align: 'center', fixed: 'right', toolbar: '#OnlineLearning-Tools'}
            ]],
            page: true
        });

        // 监听表格顶部的搜索操作(改这里)
        form.on('submit(nxu-search)', function (data) {
            // 条件搜索后，根据新数据重载表格(改这里)
            table.reload('OnlineLearning', {
                url: '/order?method=queryAllOrder',
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
                    $(document).ready(function () {
                        $.ajax({
                            type: 'post',
                            url: '/order?method=deleteOrder',
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
            } else if (obj.event === 'edit') { // 编辑数据(改这里)
                layer.open({
                    type: 2,
                    title: '编辑订单数据',
                    content: '/views/order/orderEdit.jsp',
                    maxmin: true,
                    area: ['450px', '580px'],
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        let iframeWindow = window['layui-layer-iframe' + index], submitID = 'LAY-user-front-submit',
                            submit = layero.find('iframe').contents().find('#' + submitID);

                        //监听提交
                        iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                            let field = data.field; //获取提交的字段

                            //提交 Ajax 成功后，静态更新表格中的数据
                            //$.ajax({});
                            table.reload('LAY-user-front-submit'); //数据刷新
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
                    title: '添加订单数据',
                    content: '/views/order/orderInsert.jsp',
                    maxmin: true,
                    area: ['500px', '580px'],
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        let iframeWindow = window['layui-layer-iframe' + index], submitID = 'LAY-user-front-submit',
                            submit = layero.find('iframe').contents().find('#' + submitID);

                        //监听提交
                        iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                            $.ajax({
                                type: 'post',
                                url: '/order?method=insertOrder',
                                data: data.field,
                                dataType: 'json',
                                success: function (data) {
                                    if (data.result === 1) {
                                        layer.msg(data.info, {offset: '15px', icon: 1, time: 1000});
                                    } else {
                                        layer.msg(data.info, {offset: '15px', icon: 2, time: 1000});
                                    }
                                },
                                error: function (xhr, status, error) {
                                    layer.msg('出错了')
                                    console.log(error); // 控制台打印
                                }
                            });
                            table.reload('OnlineLearning'); //数据刷新
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
            $('#userId').val('');
            $('#courseId').val('');
            $('#time').val('');
            $('#state').val(0);
            form.render("select");  // 渲染layui的下拉菜单(必须要有)
        });
    });
</script>
</body>
</html>