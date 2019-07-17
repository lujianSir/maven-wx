package com.lujian.body;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.bodyanalysis.AipBodyAnalysis;

/**
 * 人体检测与属性识别
 * 
 * @author Administrator
 *
 */
public class AipBodyAnalysisUtil {
	/**
	 * 解析人体
	 * 
	 * @param client
	 * @return
	 */
	public static String sample(AipBodyAnalysis client, String imagePath) {
		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("type", "gender,age,glasses,cellphone,vehicle,is_human");

		JSONObject res = client.bodyAttr(imagePath, options);
		System.out.println(res.toString(2));
		return res.toString(2);
	}
}
