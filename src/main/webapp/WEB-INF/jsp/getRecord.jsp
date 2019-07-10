<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>录音</title>
<!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
 <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
 <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
 <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<style type="text/css">
	.row{
		  margin: 140px 40px 40px 40px;
	}
	.btn{
		font-size: 40px;
		width: 300px;
	}
</style>
</head>
<body>
	<div class="container-fluid" style="text-align: center;">
		<div class="row">
			 <button type="button" class="btn btn-primary start_btn">按住不放即录音</button>			 
		</div>
		<div class="row">
			<button type="button" class="btn btn-success play_btn">点我播放</button>	 
		</div>
		<div class="row">	
			<button type="button" class="btn btn-info translate_btn">点我翻译</button>
		</div>
		<div class="row">	
			<button type="button" class="btn btn-warning send_btn">点我保存</button>
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
		jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','startRecord','stopRecord','onVoiceRecordEnd','playVoice','stopVoice','onVoicePlayEnd','uploadVoice','translateVoice'],
      		     		    		     
	});
	wx.ready(function() {
		wx.checkJsApi({
			jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','startRecord','stopRecord','onVoiceRecordEnd','playVoice','stopVoice','onVoicePlayEnd','uploadVoice','translateVoice'],
	        success: function(res) {
	            // 以键值对的形式返回，可用的api值true，不可用为false
	            // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
	       		alert(JSON.stringify(res));
	        }
	    });
		
		//返回音频的本地ID
		var localId;
		//返回音频的服务器端ID
		var serverId;
		//录音计时,小于指定秒数(minTime = 10)则设置用户未录音
		var startTime , endTime , minTime = 2;
		//***********************************//
		
		
		//开始录音
		$('.start_btn').on('touchstart',function(e){
			e.preventDefault();
			var $this = $(this);
			$this.addClass('start_btn_in');
			startTime = new Date().getTime();
			
			//开始录音
			wx.startRecord();
		});
		//***********************************//
		//停止录音接口
		$('.start_btn').on('touchend', function(){
			var $this = $(this);
			$this.removeClass('start_btn_in');
			
			//停止录音接口
			wx.stopRecord({
				success: function (res) {
					localId = res.localId;
				}
			});
			
			endTime = new Date().getTime();
			alert((endTime - startTime) / 1000);
			if((endTime - startTime) / 1000 < minTime){
				localId = '';
				alert('录音少于' + minTime +  '秒，录音失败，请重新录音');
			}
			
		});
		//监听录音自动停止接口
		wx.onVoiceRecordEnd({
			//录音时间超过一分钟没有停止的时候会执行 complete 回调
			complete: function (res) {
				localId = res.localId;
				
				$('.start_btn').removeClass('start_btn_in');
			}
		});
		
		
		//***********************************//
		
		
		$('.play_btn').on('click',function(){
			if(!localId){
				alert('您还未录音，请录音后再点击播放');
				return;
			}
			var $this = $(this);
			if($this.hasClass('stop_btn')){
				$(this).removeClass('stop_btn').text('点我播放');
				
		//		//暂停播放接口
		//		wx.pauseVoice({
		//			//需要暂停的音频的本地ID，由 stopRecord 或 onVoiceRecordEnd 接口获得
		//			localId: localId
		//		});
		
				//停止播放接口
				wx.stopVoice({
					//需要停止的音频的本地ID，由 stopRecord 或 onVoiceRecordEnd 接口获得
					localId: localId
				});
			}else{
				$this.addClass('stop_btn').text('点我停止');
				
				//播放语音接口
				wx.playVoice({
					//需要播放的音频的本地ID，由 stopRecord 或 onVoiceRecordEnd 接口获得
					localId: localId
				});
			}
		});
		//监听语音播放完毕接口
		wx.onVoicePlayEnd({
			//需要下载的音频的服务器端ID，由uploadVoice接口获得
			serverId: localId,
			success: function (res) {
				$('.play_btn').removeClass('stop_btn').text('点我播放');
				
				//返回音频的本地ID
				//localId = res.localId;
			}
		});
		
		
		//***********************************//
		
		
		//上传语音接口
		$('.send_btn').on('click',function(){
			if(!localId){
				alert('您还未录音，请录音后再保存');
				return;
			}
			
			/* alert('上传语音,测试，并未提交保存');
			return; */
			
			//上传语音接口
			wx.uploadVoice({
				//需要上传的音频的本地ID，由 stopRecord 或 onVoiceRecordEnd 接口获得
				localId: localId, 
				//默认为1，显示进度提示
				isShowProgressTips: 1,
				success: function (res) {
					//返回音频的服务器端ID
					serverId = res.serverId;
					alert(serverId);
					$.post("<%=request.getContextPath()%>/saveVideo",{"serverId":serverId},function(res){
                        //填写你自己的业务逻辑   
                        alert(res);
                    });
				},
				fail: function (res) {
	                alertModal('语音上传失败，请重试');
	            }
			});
		});
		
		
		$('.translate_btn').on('click',function(){
			if(!localId){
				alert('您还未录音，请录音后再点击翻译');
				return;
			}
			wx.translateVoice({
			   localId:localId, // 需要识别的音频的本地Id，由录音相关接口获得
			    isShowProgressTips: 1, // 默认为1，显示进度提示
			    success: function (res) {
			        alert("翻译结果:"+res.translateResult); // 语音识别的结果
			    }
			});
		});
	
	});
	wx.error(function(res) {
		// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
		console.log(res);
		alert("调用微信jsapi返回的状态:"+res.errMsg);
	});
	
});

</script>