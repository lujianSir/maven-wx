<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>获取地理位置</title>
<!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
 <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
 <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
 <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<style type="text/css">
	.row{
		    margin: 20px 20px 20px 20px;
	}
	#getBBS{
	font-size: 40px;
    margin-top: 40px;
	}
</style>
</head>
<body>
	<div class="container-fluid" style="text-align: center;">
		<div class="row">
			 <button id="getBBS" type="button" class="btn btn-primary" onclick="submitOrderInfoClick()">获取地理位置</button>			 
		</div>		
	</div>
</body>
</html>

<script type="text/javascript">

//初始化
$(function (){	
	wx.config({
	    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: '${wxsign.appId}', // 必填，公众号的唯一标识
		nonceStr: '${wxsign.nonceStr}', // 必填，生成签名的随机串
	    signature: '${wxsign.signature}',// 必填，签名，见附录1
		timestamp :'${wxsign.timestamp}', // 必填，生成签名的时间戳								
		jsApiList : ['getLocation','openLocation']      		     		    		     
	});
	wx.ready(function() {
		wx.checkJsApi({
			jsApiList : ['getLocation','openLocation'],    
	        success: function(res) {
	            // 以键值对的形式返回，可用的api值true，不可用为false
	            // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
	       		alert(JSON.stringify(res));
	        }
	    });
	
	});
	wx.error(function(res) {
		// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
		console.log(res);
		alert("调用微信jsapi返回的状态:"+res.errMsg);
	});
	
});

function submitOrderInfoClick(){
	  wx.getLocation({
	        success: function (res) {
	            alert("获取地理位置成功，经纬度为：(" + res.latitude + "," + res.longitude + ")" );
	            
	            wx.ready(function(){
	                wx.openLocation({
	                    latitude:res.latitude, // 纬度，浮点数，范围为90 ~ -90
	                    longitude:res.longitude, // 经度，浮点数，范围为180 ~ -180。
	                    name: '本地位置', // 位置名
	                    address: '测试本地位置准不准', // 地址详情说明
	                   // scale: 25, // 地图缩放级别,整形值,范围从1~28。默认为最大
	                    infoUrl: 'http://www.wolfcode.cn/' // 在查看位置界面底部显示的超链接,可点击跳转
	                });
	            });
	            
	        },
	        fail: function(error) {
	            AlertUtil.error("获取地理位置失败，请确保开启GPS且允许微信获取您的地理位置！");
	        }
	    });
	}
</script>