package com.jenkin.wx.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.jenkin.wx.util.CommonUtil;

@Controller
@RequestMapping("/user")
public class UserInfoController {

	@RequestMapping("/getperson")
	public String getperson(String code, Model model) {
		if (code != null) {
			// 1.通过code来换取access_token
			JSONObject json = CommonUtil.getWebAccessToken(code);
			// 获取网页授权access_token凭据
			String webAccessToken = json.getString("access_token");
			// 获取用户openid
			String openid = json.getString("openid");
			// 2.通过access_token和openid拉取用户信息
			JSONObject userInfo = CommonUtil.getUserInfo(webAccessToken, openid);
			// 获取json对象中的键值对集合
			Set<Map.Entry<String, Object>> entries = userInfo.entrySet();
			for (Map.Entry<String, Object> entry : entries) {
				// 把键值对作为属性设置到model中
				model.addAttribute(entry.getKey(), entry.getValue());
			}
		}
		return "person";
	}

}
