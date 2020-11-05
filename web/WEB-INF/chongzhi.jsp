<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>" />
    <title>快充系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=10" />
    <meta
            content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"
            name="viewport">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <link href="css/style.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery-1.7.min.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript">
        var h = new HYCX(), gmdiv, gmdivhf, gmdivsp;
    </script>

    <style type="text/css">
        hr {
            height: 0px;
            border-top: 1px solid #999;
            border-right: 0px;
            border-bottom: 0px;
            border-left: 0px;
        }

        .hr1 {
            height: 1px;
            border-top: 9px solid #DBDBDB;
            border-right: 1px;
            border-bottom: 1px;
            border-left: 1px;
        }

        .hotDiv {
            width: 100%;
            overflow: hidden;
            margin: 0 auto;
        }

        .hotDiv ul {
            width: 100%;
            margin: 10px 0;
        }

        .hotDiv ul li {
            width: 24%;
            height: 90px;
            text-align: center;
            font-size: 10px;
            border: 0px solid #d4d4d4;
            border-radius: 5px;
            float: left;
            margin: 0 5 30px 0;
        }
    </style>
</head>
<body backcground="images/index_.jpg" width="100%">
<table border="0" width="100%">






    <!--
		<img src="images/index_7.jpg" width="100%" height="100" />
  <tr>
     <!-- <td width="45%" style="padding-left:10px;  padding-top: 10px;"><a href="charger/chongzhi_ll.action?id=${wxid}">
    	<img alt="图片找不到" src="images/index_3.png" width="160" height="70" /></a>
    </td>

    <td width="5%"></td>
    <td width="45%" style="padding-right:10px; padding-top: 10px;"><a href="charger/chongzhi_sp.action?id=${wxid}">
    	<img alt="图片找不到" src="images/index_2.png" width="160" height="70"/></a>
    </td>

  </tr>
  <tr></tr>
  <tr></tr>

  <tr>
    <td width="45%" style="padding-left:10px;"><a href="charger/chongzhi_hf.action?id=${wxid}">
    	<img alt="图片找不到" src="images/index_1.png" width="160" height="70"/></a>
    </td>
    <td width="5%"></td>
    <td width="45%" style="padding-right:10px;"><a href="https://ecentre.spdbccc.com.cn/creditcard/indexActivity.htm?data=I2725838&itemcode=kmkysj0010">
    	<img alt="图片找不到" src="images/xyk.png" width="160" height="70" /></a>
    </td>
  </tr>
</table> -->
    <hr class="hr1">

    <div>
        <p style="font-size: 16px;">
					<span><img width="5" height="15" src="images/hot1.png"
                               style="margin-left: 9px; margin-right: 9px;"> </span> 热销产品
            <span><img width="15" height="18" src="images/hot2.png"
                       style="margin-left: 240px;"> </span>
        </p>
        <hr>
    </div>
    <div class="hotDiv">
        <ul>
            <c:forEach items="${hotPro }" var="p" varStatus="i">
                <c:if test="${p.actype==2 }">
                    <c:set var="url"
                           value="charger/chongzhi_sp_1.action?ftid=${p.id}&id=${wxid}&name=${p.name }"></c:set>
                </c:if>
                <c:if test="${p.actype==0 }">
                    <c:set var="url"
                           value="charger/chongzhi_ll.action?ftid=${p.id}&id=${wxid}"></c:set>
                </c:if>
                <c:if test="${p.actype==1 }">
                    <c:set var="url"
                           value="charger/chongzhi_hf.action?ftid=${p.id}&id=${wxid}"></c:set>
                </c:if>

                <li>
                    <a href="${url}"> <img alt="图片找不到" src="${p.imgUrl }"
                                           width="55" height="50" style="margin-left: 9px;" /> <br>
                        <p align=center">
                            <font style="font-size: 12px; margin-left: 9px;">${fn:replace(p.name,"会员","")}</font>
                        </p> </a>
                </li>
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
        <table>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td></td>
            </tr>
        </table>
    </div>
    <div class="foot">
        <p>
            <input type="hidden" value='${wxchcode}' id='wxchcode'>
            <font color="black">${wxname}&nbsp;&nbsp;官方授权</font>
        </p>
    </div>
</body>
</html>
