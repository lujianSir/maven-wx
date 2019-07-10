package com.jenkin.wx.dao;

import org.apache.ibatis.annotations.Param;

import com.jenkin.wx.pojo.AccessToken;
import com.jenkin.wx.pojo.TemporaryResources;

/**
 * @author: jenkinwang
 * @date: 2018/9/27 22:18
 * @description:
 */
public interface WeChatDao {

    int insertAccessToken(AccessToken accessToken);//插入token

    AccessToken queryLatestAccessToken();//查询最后一个token

	/**
	 * 临时图片添加
	 * 
	 * @param temporaryResources
	 * @return
	 */
	int insertTemporaryResources(@Param("temporaryResources") TemporaryResources temporaryResources);
}
