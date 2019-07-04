package com.jenkin.wx.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jenkin.wx.util.CommonUtil;

@Controller
@RequestMapping("/code")
public class WXcodeController {

	@RequestMapping("/getWXCode")
	public void getWXCode(HttpServletRequest request, HttpServletResponse response, String backUrl)
			throws ServletException, IOException {
		// 第一步：引导用户进入授权页面同意授权，获取code

		// 回调地址
		// String backUrl = "http://suliu.free.ngrok.cc/WxAuth/callBack";
		// //第1种情况使用
		// backUrl =
		// "http://8uuuv6.natappfree.cc/WeChatOfficialAccount/user/getperson";//
		// 第2种情况使用，这里是web.xml中的路径

		// 授权页面地址
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + CommonUtil.APPID + "&redirect_uri="
				+ URLEncoder.encode(backUrl) + "&response_type=code" + "&scope=snsapi_userinfo"
				+ "&state=STATE#wechat_redirect";

		// 重定向到授权页面
		response.sendRedirect(url);
	}
}
