package com.lujian.facelogin;

import org.json.JSONArray;
import org.json.JSONObject;

import com.baidu.aip.face.AipFace;

public class FaceGroup {

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

}
