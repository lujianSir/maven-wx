package com.lujian.facelogin;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;
import com.jenkin.wx.pojo.Image;

public class FaceSearch {

	public static String Faceregistrtion(AipFace client, String groupId, Image image) {// 从指定的group中进行查找
																						// 用逗号分隔s
		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("quality_control", "NORMAL");
		options.put("liveness_control", "LOW");
		// 人脸注册
		JSONObject res = client.search(image.getImage(), image.getImageType(), groupId, options);
		return res.toString(2);
	}
}
