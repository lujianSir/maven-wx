package com.lujian.facelogin;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;
import com.jenkin.wx.pojo.Image;

/**
 * //人脸检测的类
 * 
 * @author Administrator
 *
 */
public class FaceDetection {

	public static String Facedetection(AipFace client, Image image) {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("face_field", "age");// 返回的人脸信息
		options.put("max_face_num", "1");// 最大人脸识别数1
		options.put("face_type", "LIVE");// 照骗类型 生活照
		JSONObject res = client.detect(image.getImage(), image.getImageType(), options);
		String result = res.get("result").toString();
		if (result == null || result.equals("null")) {
			return "照片不对，需要生活照";
		} else {
			return res.toString(2);
		}

	}

}
