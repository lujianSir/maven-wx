package com.lujian.body;

import com.baidu.aip.bodyanalysis.AipBodyAnalysis;

public class AipBodyAnalysisObject {

	// 设置APPID/AK/SK
	public static final String APP_ID = "16823005";
	public static final String API_KEY = "XHPUHlkIh3rrM8DjC3U01P8m";
	public static final String SECRET_KEY = "dkxR1WLXNPMACxkjcTdVI9HS4oliUt0G";

	// 创建一个aipface对象
	private static AipBodyAnalysis client = new AipBodyAnalysis(APP_ID, API_KEY, SECRET_KEY);

	// 创建单例的原因是避免多次获取sdk
	public static AipBodyAnalysis getClient() {
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
		return client;
	}

}
