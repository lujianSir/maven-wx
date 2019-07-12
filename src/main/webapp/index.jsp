<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>功能整合</title>
<!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
 <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
 <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
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
			<div class="col-md-6">获取用户授权信息</div>
  			<div class="col-md-6"><input type="button" onclick="getUserMessage()" value="获取授权信息" > </div>
		</div>
	
  		<div class="row"> 		
  			<div class="col-md-6">点击跳转到上传图片的页面</div>
  			<div class="col-md-6"><input type="button" onclick="jumpImg()" value="跳转图片地址" ></div>
  				 	
		</div>
		<div class="row">
			<div class="col-md-6">点击跳转到获取地理位置的页面</div>
  			<div class="col-md-6"><input type="button" onclick="jumpLocation()" value="跳转位置地址" > </div>
		</div>
		
		<div class="row">
			<div class="col-md-6">点击跳转到可以分享的页面</div>
  			<div class="col-md-6"><input type="button" onclick="jumpShare()" value="跳转分享地址" > </div>
		</div>
		
		<div class="row">
			<div class="col-md-6">点击跳转到录音的页面</div>
  			<div class="col-md-6"><input type="button" onclick="jumpRecord()" value="跳转录音地址" > </div>
		</div>
		
		<div class="row">
			<div class="col-md-6">点击人工智能识别图片页面</div>
  			<div class="col-md-6"><input type="button" onclick="jumpArtificial()" value="跳转智能识别图片地址" > </div>
		</div>
		
		<div class="row">
			<div class="col-md-6">点击人脸识别页面</div>
  			<div class="col-md-6"><input type="button" onclick="jumpFace()" value="跳转人脸识别地址" > </div>
		</div>
	</div>

</body>
</html>

<script type="text/javascript">
	function getUserMessage(){
		var returnUrl="http://"+window.location.host+"/WeChatOfficialAccount/user/getperson";
		var url="http://"+window.location.host+"/WeChatOfficialAccount/code/getWXCode?backUrl="+returnUrl+"";
		window.location.href=url;
	}
	function jumpImg(){
		window.location.href='<%=request.getContextPath()%>/getImage';
	}
	function jumpLocation(){
		window.location.href='<%=request.getContextPath()%>/getlocation';
	}
	function jumpShare(){
		window.location.href='<%=request.getContextPath()%>/share';
	}
	function jumpRecord(){
		window.location.href='<%=request.getContextPath()%>/getRecord';
	}
	function jumpArtificial(){
		window.location.href='<%=request.getContextPath()%>/inintWXImage';
	}
	function jumpFace(){
		window.location.href='<%=request.getContextPath()%>/inintFace';
	}
</script>