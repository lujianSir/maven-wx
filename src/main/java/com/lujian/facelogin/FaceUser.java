package com.lujian.facelogin;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;
import com.jenkin.wx.pojo.Image;

public class FaceUser {

	public static String Faceuser(AipFace client, Image imageU) {
		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("quality_control", "NORMAL");
		options.put("liveness_control", "LOW");
		options.put("max_user_num", "1");

		String groupIdList = "你的人脸库名称";

		// 人脸搜索
		JSONObject res = client.search(imageU.getImage(), imageU.getImageType(), groupIdList, options);
		return res.toString(2);
	}

}
