<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>验证人脸识别</title>
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

</style>
</head>
<body>
	<div class="container-fluid" style="text-align: center;">
		<div class="row">
			 <button type="button" class="btn btn-primary" onclick="chooseImage()">上传图片</button>			 
		</div>
		<div class="row">
				<div id="ceshi">
  		
  				</div>	 
		</div>
		<div class="row">
			 <button type="button" class="btn btn-primary" onclick="faceImage()">验证人脸</button>			 
		</div>
		<input type="hidden" value="" id="mediaId">
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
	 $("#ceshi").empty();
    wx.chooseImage({
        count: 1, // 默认9
        sizeType: ['original', 'compressed'], // 指定是原图还是压缩图，默认都有
        sourceType: ['album', 'camera'], // 指定来源是相册还是相机，默认都有
        success: function (res) {       	
            var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
            wx.uploadImage({
                localId: localIds.toString(), // 需要上传的图片的ID，由chooseImage接口获得
                isShowProgressTips: 1, // 进度提示
                success: function (res) {
                    var mediaId = res.serverId; // 返回图片的服务器端ID，即mediaId
                    $("#mediaId").val(mediaId);
                    //将获取到的 mediaId 传入后台 方法savePicture
                    $.post("<%=request.getContextPath()%>/uploadPicture",{"mediaId":mediaId},function(res){
                        //填写你自己的业务逻辑                      
                        var url='<%=request.getContextPath()%>/'+res;
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

//校验人脸
function faceImage(){
	
	var mediaId=$("#mediaId").val();
	if(mediaId==null||mediaId==""){
		alert("请上传图片");
		return;
	}
	$.ajax({
		 type: "POST",
		 async:true,
		 url: '<%=request.getContextPath()%>/faceSearch',
		 data:{"mediaId":mediaId},
		 dataType: "json",
		  success: function (data) {			 
			  alert("识别内容:"+JSON.stringify(data));
		  }			   		   
		});
}

</script>