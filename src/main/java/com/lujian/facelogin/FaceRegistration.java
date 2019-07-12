package com.lujian.facelogin;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;
import com.jenkin.wx.pojo.Image;

/**
 * 进行人脸的注册，就是在百度的人脸库里面注册你的用户人脸库
 * 
 * @author Administrator
 *
 */
public class FaceRegistration {

	public static String Faceregistrtion(AipFace client, String groupId, String userId, Image image) {
		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("user_info", "user's info");
		options.put("quality_control", "NORMAL");
		options.put("liveness_control", "LOW");
		// 人脸注册
		JSONObject res = client.addUser(image.getImage(), image.getImageType(), groupId, userId, options);

		return res.toString(2);
	}

}
