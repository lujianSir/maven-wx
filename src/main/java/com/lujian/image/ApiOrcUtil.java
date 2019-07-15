package com.lujian.image;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

/**
 * 人工智能
 * 
 * @author Administrator
 *
 */
public class ApiOrcUtil {

	 //设置APPID/AK/SK
	private static String APP_ID = "16774949";
	private static String API_KEY = "psBgBHA1PYocGRNWfbt7Dbc6";
	private static String SECRET_KEY = "e03dh6oTlQPF1YW0Dfe20G51vuAMRce3";

	/**
	 * 识别身份证
	 * 
	 * @param photoPath
	 * @return
	 */
    public static String getPictureString(String photoPath){

        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        // 是否检测朝向
        options.put("detect_direction", "false");
        // 是否检测风险
        options.put("detect_risk", "false");

        // 正反面front /back
        String idCardSide = "front";

        // 参数为本地图片二进制数组
        /*byte[] file = new byte[0];
        try {
            file = Util.readFileByBytes(photoPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject res = client.idcard(file, idCardSide, options);
        System.out.println(res.toString(2));*/


        // 参数为本地图片路径
        try {
            JSONObject res = client.idcard(photoPath, idCardSide, options);
            System.out.println(res.toString(2));
            if (res != null) {
                JSONObject idCard = new JSONObject();
                JSONObject words_result = res.getJSONObject("words_result");

                idCard.put("name", words_result.getJSONObject("姓名").get("words"));
                idCard.put("nation", words_result.getJSONObject("民族").get("words"));
                idCard.put("address", words_result.getJSONObject("住址").get("words"));
                idCard.put("sex", words_result.getJSONObject("性别").get("words"));
                idCard.put("birth", words_result.getJSONObject("出生").get("words"));
                idCard.put("number", words_result.getJSONObject("公民身份号码").get("words"));
                return idCard.toString(2);
            } else {
                return "";
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

	/**
	 * 识别车牌
	 * 
	 * @param photoPath
	 * @return
	 */
	public static String getPlateLicense(String photoPath) {

		// 初始化一个AipOcr
		AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();
		// multi_detect 是否检测多张车牌
		options.put("multi_detect", "true");

		// 参数为本地图片二进制数组
		/*
		 * byte[] file = new byte[0]; try { file =
		 * Util.readFileByBytes(photoPath); } catch (IOException e) {
		 * e.printStackTrace(); } JSONObject res = client.idcard(file,
		 * idCardSide, options); System.out.println(res.toString(2));
		 */

		// 参数为本地图片路径
		try {
			JSONObject res = client.plateLicense(photoPath, options);
			System.out.println(res.toString(2));
			if (res != null) {
				JSONObject plateLicense = new JSONObject();
				JSONArray words_result1 = res.getJSONArray("words_result");
				for (int i = 0; i < words_result1.length(); i++) {
					int j = i + 1;
					JSONObject myjObject = words_result1.getJSONObject(i);
					plateLicense.put(j + "号车牌", myjObject.get("number"));
				}
				return plateLicense.toString(2);
			} else {
				return "";
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

    public static void main(String[] args) {
		System.out.println(getPictureString("D:/sfz.png"));
    }

}
