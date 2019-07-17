package com.lujian.content;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

import com.baidu.aip.contentcensor.AipContentCensor;

public class AipContentCensorUtil {

	/**
	 * 识别文本中的敏感词汇(需要对百度API增强)
	 *
	 * @param text
	 */
	public static boolean discernSensitiveWords(String text, HashMap<String, String> options) {
		String resp;
		AipContentCensor client = AipContentCensorObject.getClient();
		try {
			// 防止出现特殊符号，制造异常
			resp = client.antiSpam(URLDecoder.decode(text, "UTF-8"), options).toString();
		} catch (UnsupportedEncodingException e) {
			return false;
		}
		// 标识审核是否通过的结果所在未知
		int len = resp.lastIndexOf("m\":") + 3;
		String spam = resp.substring(len, len + 1);
		// System.out.println(spam);
		if (spam.equals("0")) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		String str = "今天天气真好";
		AipContentCensorUtil.discernSensitiveWords(str, null);
	}
}
