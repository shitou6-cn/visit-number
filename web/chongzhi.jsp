<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>" />
    <title>快充系统</title>


    <!-- 移动端适配   全屏  支持屏幕放大缩小的倍率 -->
    <meta name="full-screen" content="yes">

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta  http-equiv="X-UA-Compatible" content="IE=10" />
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <link href="css/style.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery-1.7.min.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script src="http://www.zeptojs.cn/zepto.min.js"/>
    <script type="text/javascript">

        //zepto对移动端滑动扩展
        (function ($) {
            var touch = {},
                touchTimeout, tapTimeout, swipeTimeout, longTapTimeout,
                longTapDelay = 750,
                gesture

            function swipeDirection(x1, x2, y1, y2) {
                return Math.abs(x1 - x2) >=
                Math.abs(y1 - y2) ? (x1 - x2 > 0 ? 'Left' : 'Right') : (y1 - y2 > 0 ? 'Up' : 'Down')
            }

            function longTap() {
                longTapTimeout = null
                if (touch.last) {
                    touch.el.trigger('longTap')
                    touch = {}
                }
            }

            function cancelLongTap() {
                if (longTapTimeout) clearTimeout(longTapTimeout)
                longTapTimeout = null
            }

            function cancelAll() {
                if (touchTimeout) clearTimeout(touchTimeout)
                if (tapTimeout) clearTimeout(tapTimeout)
                if (swipeTimeout) clearTimeout(swipeTimeout)
                if (longTapTimeout) clearTimeout(longTapTimeout)
                touchTimeout = tapTimeout = swipeTimeout = longTapTimeout = null
                touch = {}
            }

            function isPrimaryTouch(event) {
                return (event.pointerType == 'touch' ||
                    event.pointerType == event.MSPOINTER_TYPE_TOUCH)
                    && event.isPrimary
            }

            function isPointerEventType(e, type) {
                return (e.type == 'pointer' + type ||
                    e.type.toLowerCase() == 'mspointer' + type)
            }

            $(document).ready(function () {
                var now, delta, deltaX = 0, deltaY = 0, firstTouch, _isPointerType

                if ('MSGesture' in window) {
                    gesture = new MSGesture()
                    gesture.target = document.body
                }

                $(document)
                    .bind('MSGestureEnd', function (e) {
                        var swipeDirectionFromVelocity =
                            e.velocityX > 1 ? 'Right' : e.velocityX < -1 ? 'Left' : e.velocityY > 1 ? 'Down' : e.velocityY < -1 ? 'Up' : null
                        if (swipeDirectionFromVelocity) {
                            touch.el.trigger('swipe')
                            touch.el.trigger('swipe' + swipeDirectionFromVelocity)
                        }
                    })
                    .on('touchstart MSPointerDown pointerdown', function (e) {
                        if ((_isPointerType = isPointerEventType(e, 'down')) &&
                            !isPrimaryTouch(e)) return
                        firstTouch = _isPointerType ? e : e.touches[0]
                        if (e.touches && e.touches.length === 1 && touch.x2) {
                            // Clear out touch movement data if we have it sticking around
                            // This can occur if touchcancel doesn't fire due to preventDefault, etc.
                            touch.x2 = undefined
                            touch.y2 = undefined
                        }
                        now = Date.now()
                        delta = now - (touch.last || now)
                        touch.el = $('tagName' in firstTouch.target ?
                            firstTouch.target : firstTouch.target.parentNode)
                        touchTimeout && clearTimeout(touchTimeout)
                        touch.x1 = firstTouch.pageX
                        touch.y1 = firstTouch.pageY
                        if (delta > 0 && delta <= 250) touch.isDoubleTap = true
                        touch.last = now
                        longTapTimeout = setTimeout(longTap, longTapDelay)
                        // adds the current touch contact for IE gesture recognition
                        if (gesture && _isPointerType) gesture.addPointer(e.pointerId)
                    })
                    .on('touchmove MSPointerMove pointermove', function (e) {
                        if ((_isPointerType = isPointerEventType(e, 'move')) &&
                            !isPrimaryTouch(e)) return
                        firstTouch = _isPointerType ? e : e.touches[0]
                        cancelLongTap()
                        touch.x2 = firstTouch.pageX
                        touch.y2 = firstTouch.pageY

                        deltaX += Math.abs(touch.x1 - touch.x2)
                        deltaY += Math.abs(touch.y1 - touch.y2)
                    })
                    .on('touchend MSPointerUp pointerup', function (e) {
                        if ((_isPointerType = isPointerEventType(e, 'up')) &&
                            !isPrimaryTouch(e)) return
                        cancelLongTap()

                        // swipe
                        if ((touch.x2 && Math.abs(touch.x1 - touch.x2) > 30) ||
                            (touch.y2 && Math.abs(touch.y1 - touch.y2) > 30))

                            swipeTimeout = setTimeout(function () {
                                if (touch.el) {
                                    touch.el.trigger('swipe')
                                    touch.el.trigger('swipe' + (swipeDirection(touch.x1, touch.x2, touch.y1, touch.y2)))
                                }
                                touch = {}
                            }, 0)

                        // normal tap
                        else if ('last' in touch)
                            // don't fire tap when delta position changed by more than 30 pixels,
                            // for instance when moving to a point and back to origin
                            if (deltaX < 30 && deltaY < 30) {
                                // delay by one tick so we can cancel the 'tap' event if 'scroll' fires
                                // ('tap' fires before 'scroll')
                                tapTimeout = setTimeout(function () {

                                    // trigger universal 'tap' with the option to cancelTouch()
                                    // (cancelTouch cancels processing of single vs double taps for faster 'tap' response)
                                    var event = $.Event('tap')
                                    event.cancelTouch = cancelAll
                                    // [by paper] fix -> "TypeError: 'undefined' is not an object (evaluating 'touch.el.trigger'), when double tap
                                    if (touch.el) touch.el.trigger(event)

                                    // trigger double tap immediately
                                    if (touch.isDoubleTap) {
                                        if (touch.el) touch.el.trigger('doubleTap')
                                        touch = {}
                                    }

                                    // trigger single tap after 250ms of inactivity
                                    else {
                                        touchTimeout = setTimeout(function () {
                                            touchTimeout = null
                                            if (touch.el) touch.el.trigger('singleTap')
                                            touch = {}
                                        }, 250)
                                    }
                                }, 0)
                            } else {
                                touch = {}
                            }
                        deltaX = deltaY = 0

                    })
                    // when the browser window loses focus,
                    // for example when a modal dialog is shown,
                    // cancel all ongoing events
                    .on('touchcancel MSPointerCancel pointercancel', cancelAll)

                // scrolling the window indicates intention of the user
                // to scroll, not tap or swipe, so cancel all ongoing events
                $(window).on('scroll', cancelAll)
            })

            ;['swipe', 'swipeLeft', 'swipeRight', 'swipeUp', 'swipeDown',
                'doubleTap', 'tap', 'singleTap', 'longTap'].forEach(function (eventName) {
                $.fn[eventName] = function (callback) {
                    return this.on(eventName, callback)
                }
            })
        })(Zepto)

        $(function () {
            photoCarousel($(".images"), $(".images li"), $(".index ul"), -$(window).width(), $(window).width(), $(".images li").length, 3000, 1000);

            //图片轮播
            //$ul 容器,$li 元素,$index 索引,left 当前偏移量,width 一次滚动宽度,parentWidth 容器宽度,size 图片数量,interval 循环滚动间隔   0不循环播放,scrolltime 滚动过程时间
            function photoCarousel($ul, $li, $index, left, width, size, interval, scrolltime) {
                //move 0:默认左滑右滑；1：停止默认事件，图片跟随手势移动，time 时间大于200毫秒图片跟随手势移动  否则执行默认左滑 右滑事件
                var inner, move = 0, time = 0, parentWidth = (size + 2) * width;
                $ul.width(parentWidth).css("transition-duration", "0s").css("-webkit-transition-duration", "0s").css("transform", "translate3d(" + left + "px,0px,0px)").css("-webkit-transform", "translate3d(" + left + "px,0px,0px)");
                $li.width(width).css("line-height", $ul.height() + "px");
                $li.first().before($li.last().clone());
                $li.last().after($li.first().clone());
                $li.find("img").width(width);
                index();
                //触屏
                //触屏开始
                $li.bind("touchstart", function (event) {
                    if (interval != 0)
                        clearInterval(inner);
                    move = event.touches[0].pageX;
                    time = new Date().getTime();
                    //事件停止
                    event.preventDefault();
                });

                //触屏结束
                $li.bind("touchend", function (event) {
                    if (interval != 0)
                        inner = auto();
                    if (move != 0) {
                        $ul.css("transition-duration", (scrolltime / 1000.0) + "s").css("-webkit-transition-duration", (scrolltime / 1000.0) + "s").css("transform", "translate3d(" + left + "px,0px,0px)").css("-webkit-transform", "translate3d(" + left + "px,0px,0px)");
                    }
                    time = new Date().getTime();
                    event.preventDefault();
                });

                //触屏移动
                $li.bind("touchmove", function (event) {
                    if (interval != 0)
                        clearInterval(inner);
                    if (new Date().getTime() - time >= 200) {
                        touch = event.touches[0];
                        $ul.css("transition-duration", "0s").css("-webkit-transition-duration", "0s").css("transform", "translate3d(" + (left + touch.pageX - move) + "px,0px,0px)").css("-webkit-transform", "translate3d(" + (left + touch.pageX - move) + "px,0px,0px)");
                        event.preventDefault();
                    }
                });

                //左滑
                $li.swipeLeft(function () {
                    move = 0;
                    left = -left != parentWidth - width ? left - width : 0;
                    $ul.css("transition-duration", (scrolltime / 1000.0) + "s").css("-webkit-transition-duration", (scrolltime / 1000.0) + "s").css("transform", "translate3d(" + left + "px,0px,0px)").css("-webkit-transform", "translate3d(" + left + "px,0px,0px)");
                    //重置滚动位置
                    if (-left == (parentWidth - width)) {
                        left = -width;
                        setTimeout(function () {
                            $ul.css("transition-duration", "0s").css("-webkit-transition-duration", "0s").css("transform", "translate3d(" + left + "px,0px,0px)").css("-webkit-transform", "translate3d(" + left + "px,0px,0px)");
                        }, scrolltime);
                    }
                    index();
                })

                //右滑
                $li.swipeRight(function () {
                    move = 0;
                    left = left != 0 ? left + width : -parentWidth + width;
                    $ul.css("transition-duration", (scrolltime / 1000.0) + "s").css("-webkit-transition-duration", (scrolltime / 1000.0) + "s").css("transform", "translate3d(" + left + "px,0px,0px)").css("-webkit-transform", "translate3d(" + left + "px,0px,0px)");
                    //重置滚动位置
                    if (left == 0) {
                        left = -(parentWidth - 2 * width);
                        setTimeout(function () {
                            $ul.css("transition-duration", "0s").css("-webkit-transition-duration", "0s").css("transform", "translate3d(" + left + "px,0px,0px)").css("-webkit-transform", "translate3d(" + left + "px,0px,0px)");
                        }, scrolltime);
                    }
                    index();
                })

                //更新图片下方焦点
                function index() {
                    $index.children().removeClass("on");
                    $index.children().eq(-left / width - 1).addClass("on");
                    $index.parent().parent().find(".name").text($li.eq(-left / width - 1).find("img").attr("alt"));
                }

                function auto() {
                    inner = setInterval(function () {
                        move = 0;
                        left = -left != parentWidth - width ? left - width : 0;
                        $ul.css("transition-duration", (scrolltime / 1000.0) + "s").css("-webkit-transition-duration", (scrolltime / 1000.0) + "s").css("transform", "translate3d(" + left + "px,0px,0px)").css("-webkit-transform", "translate3d(" + left + "px,0px,0px)");
                        if (-left == (parentWidth - width)) {
                            left = -width;
                            setTimeout(function () {
                                $ul.css("transition-duration", "0s").css("-webkit-transition-duration", "0s").css("transform", "translate3d(" + left + "px,0px,0px)").css("-webkit-transform", "translate3d(" + left + "px,0px,0px)");
                            }, scrolltime);
                        }
                        index();
                    }, interval);
                    return inner;
                }

                //横竖屏切换
                $(window).bind("orientationchange", function (event) {
                    clearInterval(inner);
                    width = $(window).width();
                    left = -width;
                    parentWidth = (size + 2) * width;
                    $li.width(width).css("line-height", $ul.height() + "px");
                    $li.find("img").width(width);
                    $ul.width(parentWidth).css("transition-duration", "0s").css("-webkit-transition-duration", "0s").css("transform", "translate3d(" + left + "px,0px,0px)").css("-webkit-transform", "translate3d(" + left + "px,0px,0px)");
                    index();
                    if (interval != 0)
                        inner = auto();
                });

                if (interval != 0) {
                    auto();
                }
            }
        })
        var h = new HYCX(), gmdiv,gmdivhf,gmdivsp;
    </script>
    <script src="http://www.zeptojs.cn/zepto.min.js"></script>

    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
            list-style: none;
        }

        .pictrue {
            width: 100%;
            margin: 0 auto;
            position: relative;
            height: 200px;
            background-color: white;
            overflow: hidden;
            margin-bottom: 5px;
        }

        .images {
            height: 200px;
        }

        .images li {
            height: 200px;
            float: left;
        }

        .images li img {
            height: 200px;
            width: 100%;
        }

        .index {
            height: 10px;
            margin-top: -30px;
            position: absolute;
            width: 100%;
            text-align: center
        }

        .index ul {
            display: inline-block;
        }

        .index li {
            width: 10px;
            height: 10px;
            border-radius: 10px;
            background-color: #9a9a9a;
            float: left;
            margin-right: 8px;
        }

        .index li.on {
            background-color: white;
        }

        hr {
            height:0px;
            border-top:1px solid #999;
            border-right:0px;
            border-bottom:0px;
            border-left:0px;
        }
        .hr1 {
            height:1px;
            border-top:9px solid #DBDBDB;
            border-right:1px;
            border-bottom:1px;
            border-left:1px;
        }
        .hotDiv{width:100%; overflow:hidden; margin:0 auto;}
        .hotDiv ul{width:100%; margin:10px 0;}
        .hotDiv ul li{
            width:24%;
            height:90px;
            text-align:center;
            font-size:10px;
            border:0px solid #d4d4d4;
            border-radius:5px;
            float:left;
            /*margin:0 5 30px 0;*/
        }

    </style>
</head>
<body background=""  width="100%"  >

<div class="pictrue">
    <ul class="images">
        <li style="background-color: red;"><img alt=""
                                                src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png">
        </li>
        <li style="background-color: gray;"><img alt=""
                                                 src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png">
        </li>
        <li style="background-color: green;"><img alt=""
                                                  src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png">
        </li>
        <li style="background-color: black;"><img alt=""
                                                  src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png">
        </li>
    </ul>
    <div class="index">
        <ul>
            <li class="on"></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>
</div>


<table border="0" width="100%">

    <div class="pictrue">
        <ul class="images">
            <li style="background-color: red;"><img alt=""
                                                    src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png">
            </li>
            <li style="background-color: gray;"><img alt=""
                                                     src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png">
            </li>
            <li style="background-color: green;"><img alt=""
                                                      src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png">
            </li>
            <li style="background-color: black;"><img alt=""
                                                      src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png">
            </li>
        </ul>
        <div class="index">
            <ul>
                <li class="on"></li>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        </div>
    </div>


    <tr>
        <!--

          -->
        <td width="45%" style="padding-left:10px;  padding-top: 10px;"><a href="charger/chongzhi_ll.action?id=${wxid}">
            <img alt="图片找不到" src="images/index_3.png" width="160" height="70" /></a>
        </td>

        <td width="5%"></td>
        <td width="45%" style="padding-right:10px; padding-top: 10px;"><a href="charger/chongzhi_sp.action?id=${wxid}">
            <img alt="图片找不到" src="images/index_2.png" width="160" height="70"/></a>
        </td>

    </tr>
    <tr></tr>
    <tr></tr>
    <!--
  <tr>
    <td width="45%" style="padding-left:10px;"><a href="charger/chongzhi_hf.action?id=${wxid}">
    	<img alt="图片找不到" src="images/index_1.png" width="160" height="70"/></a>
    </td>
    <td width="5%"></td>
    <td width="45%" style="padding-right:10px;"><a href="https://ecentre.spdbccc.com.cn/creditcard/indexActivity.htm?data=I2725838&itemcode=kmkysj0010">
    	<img alt="图片找不到" src="images/xyk.png" width="160" height="70" /></a>
    </td>
  </tr>
  -->
</table>
<hr class="hr1">

<div>
    <p style="font-size:16px;" >
        <span><img width="5" height="15" src="images/hot1.png" style="margin-left: 9px; margin-right: 9px;"></span>
        热销产品
        <span><img width="15" height="18" src="images/hot2.png" style="margin-left: 240px;"></span></p>
    <hr>
</div>
<div class="hotDiv">
    <ul>
        <c:forEach items="${hotPro }" var="p" varStatus="i">
            <c:if test="${p.actype==2 }"><c:set var="url" value="charger/chongzhi_sp_1.action?ftid=${p.id}&id=${wxid}&name=${p.name }"></c:set></c:if>
            <c:if test="${p.actype==0 }"><c:set var="url" value="charger/chongzhi_ll.action?ftid=${p.id}&id=${wxid}"></c:set></c:if>
            <c:if test="${p.actype==1 }"><c:set var="url" value="charger/chongzhi_hf.action?ftid=${p.id}&id=${wxid}"></c:set></c:if>

            <li><a href="${url}">
                <img alt="图片找不到" src="${p.imgUrl }" width="55" height="50" style="margin-left: 9px;" />
                <br><p align=center "><font style="font-size:12px;margin-left: 9px;">${fn:replace(p.name,"会员","")}</font></p></a></li>
        </c:forEach>
    </ul>
</div>
<!--
<hr class="hr1">
	<div>
		<p style="color:red;font-size:30px; font-style: oblique;font-weight: bold;">更多产品</p>
	</div>
	<c:forEach items="${mm }" var="p" >
		<div style="background-color:  Silver;"><p style="font-size:21px;">${p.key }</p></div>
		<c:forEach items="${p.value }" var="py"  varStatus="i">
		<c:if test="${!i.last}" >
		<div style="margin : 3px 0px 5px 20px;"><p style="font-size:18px;">${py }</p>
		<hr class="hr2" >
		</div>
		</c:if>
		<c:if test="${i.last}" >
		<div style="margin : 3px 0px 5px 20px;"><p style="font-size:18px;">${py }</p>
		</div>
		</c:if>
		</c:forEach>
	</c:forEach>
	-->

<div>
    <table><tr><td></td></tr>
        <tr><td></td></tr>
    </table>
</div>
<div class="foot">
    <p><input type="hidden" value='${wxchcode}' id='wxchcode' ><font color="black">${wxname}&nbsp;&nbsp;官方授权</font></p></div>
</body>
</html>
