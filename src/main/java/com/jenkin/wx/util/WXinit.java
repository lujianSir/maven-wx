package com.jenkin.wx.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class WXinit implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		try {
			prop.load(WXinit.class.getResourceAsStream("/wx.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CommonUtil.APPID = prop.getProperty("app_id");
		CommonUtil.APPSECRET = prop.getProperty("app_secret");
		WeChatVerificationUtil.TOKEN = prop.getProperty("token");
		System.out.println("初始化成功:------");
	}

}
