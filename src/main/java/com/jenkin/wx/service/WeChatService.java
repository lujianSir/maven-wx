package com.jenkin.wx.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.jenkin.wx.pojo.AccessToken;

/**
 * @author: jenkinwang
 * @date: 2018/9/27 22:17
 * @description:
 */
public interface WeChatService {

    int insertAccessToken(AccessToken accessToken);

	AccessToken queryLatestAccessToken();

    String handleWeChatMessage(HttpServletRequest request);

	/**
	 * 获取accesstoken
	 * 
	 * @return
	 * @throws IOException
	 */
	String getAccessToken() throws IOException;
}
