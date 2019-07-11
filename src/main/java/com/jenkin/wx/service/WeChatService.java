package com.jenkin.wx.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jenkin.wx.pojo.AccessToken;
import com.jenkin.wx.pojo.TemporaryResources;

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

	/**
	 * 临时图片添加
	 * 
	 * @param temporaryResources
	 * @return
	 */
	int insertTemporaryResources(TemporaryResources temporaryResources);

	/**
	 * 查询三天内的数据
	 * 
	 * @return
	 */
	List<TemporaryResources> queryTemporaryImages(TemporaryResources temporaryResources);
}
