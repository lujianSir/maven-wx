package com.lujian.facelogin;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import com.jenkin.wx.pojo.Image;

public class AiFaceUtil {

	/**
	 * 两张人脸照片的比较，返回比较后的字符串结果
	 * 
	 * @author Administrator
	 *
	 */
	public static String Facecomparison(AipFace client, Image imageU, Image imageC) {

		MatchRequest req1 = new MatchRequest(imageU.getImage(), imageU.getImageType());
		MatchRequest req2 = new MatchRequest(imageC.getImage(), imageC.getImageType());
		ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
		requests.add(req1);
		requests.add(req2);
		JSONObject res = client.match(requests);
		return res.toString(2);

	}

	/**
	 * //人脸检测的类
	 * 
	 * @author Administrator
	 *
	 */
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

	/**
	 * 分组
	 * 
	 * @param client
	 * @param groupId
	 */
	public static void FaceGrouption(AipFace client, String groupId) {
		JSONObject json = client.getGroupList(null);
		JSONObject result = json.getJSONObject("result");
		JSONArray jsonArray = null;
		jsonArray = result.getJSONArray("group_id_list");// 获取数组
		boolean flag = false;
		if (jsonArray.isEmpty()) {
			JSONObject message = client.groupAdd(groupId, null);
			System.out.println(message);
		} else {
			for (int i = 0; i < jsonArray.length(); i++) {
				if (jsonArray.get(i).toString().equals(groupId)) {
					flag = true;
					break;
				}

			}
		}
		if (!flag) {
			client.groupAdd(groupId, null);
		}
	}

	/**
	 * 进行人脸的注册，就是在百度的人脸库里面注册你的用户人脸库
	 * 
	 * @author Administrator
	 *
	 */
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

	/**
	 * 人脸查找
	 * 
	 * @param client
	 * @param groupId
	 * @param image
	 * @return
	 */
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

	/**
	 * 人脸搜索
	 * 
	 * @param client
	 * @param imageU
	 * @return
	 */
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
