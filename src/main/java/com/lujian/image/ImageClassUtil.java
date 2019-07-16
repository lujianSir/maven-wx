package com.lujian.image;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.imageclassify.AipImageClassify;

/**
 * 图像识别 分析图像内容
 * 
 * @author Administrator
 *
 */
public class ImageClassUtil {


	// 通用物体识别接口
	public static String sample(AipImageClassify client, String imagepath) {

		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("baike_num", "5");

		JSONObject res = client.advancedGeneral(imagepath, options);
		System.out.println(res.toString(2));
		return res.toString(2);
	}

	/**
	 * logo识别
	 * 
	 * @param client
	 * @param imagepath
	 * @return
	 */
	public static String logoSearch(AipImageClassify client, String imagepath) {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("custom_lib", "false");

		JSONObject res = client.logoSearch(imagepath, options);
		return res.toString(2);

	}

	/**
	 * 动物分析
	 * 
	 * @param client
	 * @param imagepath
	 * @return
	 */
	public static String animalDetect(AipImageClassify client, String imagepath) {
		HashMap<String, String> options = new HashMap<String, String>();
		/*
		 * options.put("baike_num", "5"); options.put("top_num", "1");
		 */
		JSONObject res = client.animalDetect(imagepath, options);
		return res.toString(2);

	}

}
