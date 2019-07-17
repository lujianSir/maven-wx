package com.lujian.content;

import com.baidu.aip.contentcensor.AipContentCensor;

public class AipContentCensorObject {

	// 设置APPID/AK/SK
	public static final String APP_ID = "16822768";
	public static final String API_KEY = "bQkzXtc1igfFYIoxaII0teE9";
	public static final String SECRET_KEY = "GHMdS30OVkn5GPcsuT9f69t3qPuYVoEQ";

	/** 图片内容审核客户端 */
	private static AipContentCensor contentCensorClient;

	/**
	 * 初始化Client
	 */
	public static AipContentCensor getClient() {

		// 初始化图片审核客户端
		contentCensorClient = new AipContentCensor(APP_ID, API_KEY, SECRET_KEY);
		contentCensorClient.setConnectionTimeoutInMillis(2000);
		contentCensorClient.setSocketTimeoutInMillis(60000);
		return contentCensorClient;
	}

}
