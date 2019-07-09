<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传图片</title>
 <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
 <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
</head>
<body>
	<button id="getBBS" style="font-size:45px;" onclick="submitOrderInfoClick();">获取地理位置</button>
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
		jsApiList : ['getLocation']      		     		    		     
	});
	wx.ready(function() {
		wx.checkJsApi({
			jsApiList : ['getLocation'],    
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
	            alert("小宝鸽获取地理位置成功，经纬度为：（" + res.latitude + "，" + res.longitude + "）" );
	        },
	        fail: function(error) {
	            AlertUtil.error("获取地理位置失败，请确保开启GPS且允许微信获取您的地理位置！");
	        }
	    });
	}
</script>