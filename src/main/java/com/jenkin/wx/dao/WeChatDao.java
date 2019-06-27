package com.jenkin.wx.dao;

import com.jenkin.wx.pojo.AccessToken;

/**
 * @author: jenkinwang
 * @date: 2018/9/27 22:18
 * @description:
 */
public interface WeChatDao {

    int insertAccessToken(AccessToken accessToken);//插入token

    AccessToken queryLatestAccessToken();//查询最后一个token
}
