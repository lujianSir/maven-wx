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
	<div id="ceshi">
        <input type="button" value="上传图片"  onclick="chooseImage()" style="font-size: 35px;"/>
  		
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
		jsApiList : ['chooseImage','uploadImage', 'downloadImage']      		     		    		     
	});
	wx.ready(function() {
		wx.checkJsApi({
			jsApiList : ['chooseImage','uploadImage', 'downloadImage'],    
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

	
//打开相机	
function chooseImage(){
    wx.chooseImage({
        count: 1, // 默认9
        sizeType: ['original', 'compressed'], // 指定是原图还是压缩图，默认都有
        sourceType: ['album', 'camera'], // 指定来源是相册还是相机，默认都有
        success: function (res) {       	
            var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
            alert(localIds);
            wx.uploadImage({
                localId: localIds.toString(), // 需要上传的图片的ID，由chooseImage接口获得
                isShowProgressTips: 1, // 进度提示
                success: function (res) {
                    var mediaId = res.serverId; // 返回图片的服务器端ID，即mediaId
                    //将获取到的 mediaId 传入后台 方法savePicture
                    $.post("<%=request.getContextPath()%>/savePicture",{"mediaId":mediaId},function(res){
                        //填写你自己的业务逻辑
                        alert(res);
                        var url='<%=request.getContextPath()%>'+res;
                        alert(url);
                        $("#ceshi").append("<img src='"+url+"'  class='img-responsive'>");
                    });
                },
                fail: function (res) {
                    alertModal('图片上传失败，请重试');
                }
            }); 
        }
    });
}

</script>