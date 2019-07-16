package com.lujian.image;

import com.baidu.aip.imageclassify.AipImageClassify;

public class ImageClassifyObject {

	// 设置APPID/AK/SK
	public static final String APP_ID = "16815493";
	public static final String API_KEY = "CDcfYWMGp0TSRF1gRnrz7537";
	public static final String SECRET_KEY = "7ykQTKcMLnsHIm9wmpY1qani0rlcs2ob";

	// 创建一个AipImageClassify对象
	private static AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);

	// 创建单例的原因是避免多次获取sdk
	public static AipImageClassify getClient() {
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
		return client;
	}
}
