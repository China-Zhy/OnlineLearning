<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/views/others/layuiError.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>充值积分</title>
    <meta name="description" content="在线学习平台"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="shortcut icon" type="image/x-icon" href="assets/images/favicon.svg"/>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/pages/assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/pages/assets/css/LineIcons.2.0.css"/>
    <link rel="stylesheet" href="/pages/assets/css/animate.css"/>
    <link rel="stylesheet" href="/pages/assets/css/tiny-slider.css"/>
    <link rel="stylesheet" href="/pages/assets/css/glightbox.min.css"/>
    <link rel="stylesheet" href="/pages/assets/css/main.css"/>
</head>

<body>
<%--页面加载动画--%>
<jsp:include page="loading.jsp"/>

<section class="newsletter-area section">
    <div class="container">
        <div class="row ">
            <div class="col-lg-8 offset-lg-2 col-12">
                <div class="section-title">
                    <span class="wow fadeInDown" data-wow-delay=".2s">【此处暂时模拟积分充值】</span>
                    <h2 class="wow fadeInUp" data-wow-delay=".4s">
                        充值后悔两年，不充值后悔一辈子。
                    </h2>
                    <h3 class="gray-bg">Let's start topping up</h3>
                </div>
                <div class="subscribe-text wow fadeInUp" data-wow-delay=".2s">
                    <input name="score" id="score" type="text" min="0" placeholder="请填写充值金额  1元=100积分">
                    <div class="button">
                        <button class="btn" id="doRecharge">充值积分</button>
                    </div>
                    <div class="button">
                        <button class="btn" id="goCourseHome">我的课程</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<div class="container mt-3">
    <table class="table table-bordered" id="pointInfo">
        <tr>
            <th colspan="4" style="text-align: center;"><a href="#" class="list-group-item list-group-item-action">${Amdmin.name}的积分记录</a></th>
        </tr>
        <tr>
            <th style="text-align: center;"><a href="#" class="list-group-item list-group-item-warning">序号：</a></th>
            <th style="text-align: center;"><a href="#" class="list-group-item list-group-item-primary">时间：</a></th>
            <th style="text-align: center;"><a href="#" class="list-group-item list-group-item-secondary">金额：</a></th>
            <th style="text-align: center;"><a href="#" class="list-group-item list-group-item-success">类型：</a></th>
        </tr>
        <c:forEach items="${allPoints}" var="point" varStatus="item">
            <tr>
                <th style="text-align: center;"><a href="#" class="list-group-item list-group-item-warning">${item.count}</a></th>
                <th style="text-align: center;"><a href="#" class="list-group-item list-group-item-primary">${point.time}</a></th>
                <th style="text-align: center;"><a href="#" class="list-group-item list-group-item-secondary">${point.number}</a></th>
                <th style="text-align: center;"><a href="#" class="list-group-item list-group-item-success">${point.type}</a></th>
            </tr>
        </c:forEach>
    </table>
</div>

<section id="team" class="team section">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="section-title align-center gray-bg">
                    <span class="wow fadeInDown" data-wow-delay=".2s">扫码充值更方便</span>
                    <h2 class="wow fadeInUp" data-wow-delay=".4s">好好充值，好好学习。天天充值，天天向上。</h2>
                    <h3 class="gray-bg">Let's start topping up</h3>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-3 col-md-6 col-12">
                <div class="single-team wow fadeInUp" data-wow-delay=".2s">
                    <div class="image">
                        <img src="/pages/assets/images/money/vx-txy.jpg" alt="收款码加载失败">
                        <ul class="social">
                            <li><a href="#"><i class="lni lni-facebook-filled"></i></a></li>
                            <li><a href="#"><i class="lni lni-twitter-original"></i></a></li>
                            <li><a href="#"><i class="lni lni-linkedin-original"></i></a></li>
                            <li><a href="#"><i class="lni lni-behance-original"></i></a></li>
                        </ul>
                    </div>
                    <div class="info-head">
                        <div class="info-box">
                            <h4 class="name"><a href="#">方式一</a></h4>
                            <span class="designation">唐馨源</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-12">
                <div class="single-team wow fadeInUp" data-wow-delay=".4s">
                    <div class="image">
                        <img src="/pages/assets/images/money/zfb-txy.jpg" alt="收款码加载失败">
                        <ul class="social">
                            <li><a href="#"><i class="lni lni-facebook-filled"></i></a></li>
                            <li><a href="#"><i class="lni lni-twitter-original"></i></a></li>
                            <li><a href="#"><i class="lni lni-linkedin-original"></i></a></li>
                            <li><a href="#"><i class="lni lni-behance-original"></i></a></li>
                        </ul>
                    </div>
                    <div class="info-head">
                        <div class="info-box">
                            <h4 class="name"><a href="#">方式二</a></h4>
                            <span class="designation">樊雪儿</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-12">
                <div class="single-team wow fadeInUp" data-wow-delay=".6s">
                    <div class="image">
                        <img src="/pages/assets/images/money/vx-zhy.jpg" alt="收款码加载失败">
                        <ul class="social">
                            <li><a href="#"><i class="lni lni-facebook-filled"></i></a></li>
                            <li><a href="#"><i class="lni lni-twitter-original"></i></a></li>
                            <li><a href="#"><i class="lni lni-linkedin-original"></i></a></li>
                            <li><a href="#"><i class="lni lni-behance-original"></i></a></li>
                        </ul>
                    </div>
                    <div class="info-head">
                        <div class="info-box">
                            <h4 class="name"><a href="#">方式三</a></h4>
                            <span class="designation">张宏业</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-12">
                <div class="single-team wow fadeInUp" data-wow-delay=".8s">
                    <div class="image">
                        <img src="/pages/assets/images/money/zfb-zhy.jpg" alt="收款码加载失败">
                        <ul class="social">
                            <li><a href="#"><i class="lni lni-facebook-filled"></i></a></li>
                            <li><a href="#"><i class="lni lni-twitter-original"></i></a></li>
                            <li><a href="#"><i class="lni lni-linkedin-original"></i></a></li>
                            <li><a href="#"><i class="lni lni-behance-original"></i></a></li>
                        </ul>
                    </div>
                    <div class="info-head">
                        <div class="info-box">
                            <h4 class="name"><a href="#">方式四</a></h4>
                            <span class="designation">胡&ensp;昊</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<a href="#" class="scroll-top btn-hover">
    <i class="lni lni-chevron-up"></i>
</a>
<!-- js文件 -->
<script src="/pages/assets/js/bootstrap.min.js"></script>
<script src="/pages/assets/js/count-up.min.js"></script>
<script src="/pages/assets/js/wow.min.js"></script>
<script src="/pages/assets/js/tiny-slider.js"></script>
<script src="/pages/assets/js/glightbox.min.js"></script>
<script src="/pages/assets/js/imagesloaded.min.js"></script>
<script src="/pages/assets/js/isotope.min.js"></script>
<script src="/pages/assets/js/main.js"></script>
</body>
<script src="/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index'], function () {
        let $ = layui.$;
        $('#doRecharge').click(function () {
            if ($('#score').val() == null || $('#score').val() === '') {
                layer.msg('请填写金额', {offset: '15px', icon: 5, time: 1000});
                return;
            }
            let score = $('#score').val();
            $.ajax({
                type: 'POST',
                url: '/app/course?method=doRechargeScore',
                data: {'score': score},
                dataType: 'json',
                success: function (data) {
                    if (data.result) {
                        layer.msg(data.info, {offset: '15px', icon: 1, time: 1000});
                        let row =`<tr>
                                    <th style="text-align: center;"><a href="#" class="list-group-item list-group-item-warning">0</a></th>
                                    <th style="text-align: center;"><a href="#" class="list-group-item list-group-item-primary">`+getCurrentTime()+`</a></th>
                                    <th style="text-align: center;"><a href="#" class="list-group-item list-group-item-secondary">`+score+`</a></th>
                                    <th style="text-align: center;"><a href="#" class="list-group-item list-group-item-success">用户充值</a></th>
                                   </tr>`;
                        $('#pointInfo tr:eq(1)').after(row);
                    } else {
                        layer.msg(data.info, {offset: '15px', icon: 2, time: 1000});
                    }
                },
                error: function (xhr, status, error) {
                    layer.msg(data.info, {offset: '15px', icon: 5, time: 1000});
                    console.log(error); // 控制台打印错误
                }
            });
        });
        $('#goCourseHome').click(function () {
            window.location.href = '/app/course?method=getAllCourseByUserId&pageIndex=1&pageSize=6';
        });

        /**
         * 获取当前日期
         * @returns {string} 返回值类型”yyyy-MM-dd HH:mm:ss“，例如：2022-09-13 21:18:23。
         */
        function getCurrentTime() {
            let datetime = new Date();
            let year = datetime.getFullYear();
            let month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
            let date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
            let hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
            let minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
            let second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
            return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
        }
    });
</script>
</html>