package com.jenkin.wx.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.ParseException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.jenkin.wx.pojo.AccessToken;

/**
 * @author: jenkinwang
 * @date: 2018/9/27 09:58
 * @description:
 */
public class CommonUtil {
    // 你账号的APPID
	public static  String APPID;

    // 你账号的APPSECRET
	public static  String APPSECRET;

    // 获取access_token的URL
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APP_ID&secret=APP_SECRET";

    // 创建菜单URL
    private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	// 获取菜单URL
	private static final String GET_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

    // 删除菜单URL
    private static final String DELETE_MENU_RUL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    // 创建个性化菜单
    private static final String CREATE_INDIVIDUAL_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=ACCESS_TOKEN";

    // 删除个性化菜单
    private static final String DELETE_INDIVIDUAL_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=ACCESS_TOKEN";

	// 获取网页授权accessToken的接口
	private static final String GET_WEB_ACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

	// 获取用户信息的接口
	private static final String GET_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	// 获取用户授权
	private static final String GET_CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

    /**
     * GET、POST请求
     * @param url
     * @param method
     * @return
     * @throws IOException
     */
    public static JSONObject httpRequest(String url, String method, String postData) throws IOException {
        JSONObject json = null;
        CloseableHttpClient client = HttpClients.createDefault();

        CloseableHttpResponse response = null;

        // GET 请求
        if ("GET".equals(method)) {
            HttpGet request = new HttpGet(url);
            try {
                response = client.execute(request);
            } catch (IOException e) {
                e.printStackTrace();
            }

        // POST请求
        } else if ("POST".equals(method)) {
            HttpPost request = new HttpPost(url);
            try {
                StringEntity stringEntity = new StringEntity(postData, Charset.forName("UTF-8"));
                request.addHeader("Content-type", "application/json;charset=utf-8");
                request.setHeader("Accept", "application/json");
                request.setEntity(stringEntity);
                response = client.execute(request);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String result = EntityUtils.toString(response.getEntity());
        json = JSONObject.parseObject(result);
        return json;
    }

	public static String sendGet(String url) throws java.text.ParseException {
		String result = "";
		BufferedReader in = null;
		try {

			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 建立实际的连接
			conn.connect();
			// 获取所有响应头字段
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {

		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {

			}
		}
		return result;
	}

    /**
     * 创建菜单
     * @return
     */
    public static int createMenu(String accessToken, String menu) throws IOException {
        int result = 0;
        String createUrl = CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken);
        JSONObject json = httpRequest(createUrl, "POST", menu);
        if (json != null) {
            if (0 != json.getInteger("errcode")) {
                result = json.getInteger("errcode");
                System.out.println("创建菜单失败 errcode:" + json.get("errcode").toString() + ", errmsg:" + json.get("errmsg").toString());
            }
        }
        return result;
    }

	/**
	 * 获取菜单
	 * 
	 * @param accessToken
	 * @return
	 * @throws IOException
	 */
	public static String getMenu(String accessToken) throws IOException {
		String result = "";
		String getUrl = GET_MENU_URL.replace("ACCESS_TOKEN", accessToken);
		try {
			result = sendGet(getUrl);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

    /**
     * 删除菜单，包括个性化菜单
     * @param accessToken
     * @return
     */
    public static int deleteMenu(String accessToken) throws IOException {
        int result = 0;
        String deleteUrl = DELETE_MENU_RUL.replace("ACCESS_TOKEN", accessToken);
        JSONObject json = httpRequest(deleteUrl, "GET", null);
        if (json != null) {
            if (0 != json.getInteger("errcode")) {
                System.out.println("删除菜单失败 errcode:" + json.get("errcode").toString());
            }
        }
        return result;
    }

    /**
     * 创建个性化菜单
     * @param accessToken
     * @param menu
     * @return
     */
    public static int createIndividualMenu(String accessToken, String menu) throws IOException {
        int msgCode = -1;
        String createIndividualMenuUrl = CREATE_INDIVIDUAL_MENU_URL.replace("ACCESS_TOKEN", accessToken);
        JSONObject json = httpRequest(createIndividualMenuUrl, "POST", menu);
        if (json != null) {
            if (json.toString().contains("menuid")) {
                msgCode = 0;
                System.out.println("创建个性化菜单成功，menuid:" + json.get("menuid").toString());
            } else {
                System.out.println("创建个性化菜单失败，errcode:" + json.get("errcode").toString());
            }
        }
        return msgCode;
    }

    /**
     * 删除个性化菜单
     * @return
     */
    public static int deleteIndividualMenu(String accessToken, String menuid) throws IOException {
        int msgCode = 0;
        String deleteUrl = DELETE_INDIVIDUAL_MENU_URL.replace("ACCESS_TOKEN", accessToken);
        JSONObject json = httpRequest(deleteUrl, "POST", menuid);
        if (json != null) {
            if (0 != json.getInteger("errcode")) {
                System.out.println("删除个性化菜单失败 errcode:" + json.get("errcode").toString());
            }
        }
        return msgCode;
    }

    /**
     * 获取access_token
     * @return
     * @throws IOException
     */
    public static AccessToken getAccessToken() throws IOException {
		String access_tokenurl = ACCESS_TOKEN_URL.replace("APP_ID", APPID).replace("APP_SECRET", APPSECRET);
		JSONObject json = httpRequest(access_tokenurl, "GET", null);
        AccessToken accessToken = new AccessToken();
        accessToken.setAccess_token(json.get("access_token").toString());
        accessToken.setExpires_in(json.get("expires_in").toString());
        return accessToken;
    }

	/**
	 * 获取网页授权的AccessToken凭据
	 * 
	 * @return
	 */
	public static JSONObject getWebAccessToken(String code) {
		String webAccessToken = GET_WEB_ACCESSTOKEN_URL.replace("APPID", APPID).replace("SECRET", APPSECRET)
				.replace("CODE", code);
		String result = "";
		try {
			result = sendGet(webAccessToken);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject json = JSONObject.parseObject(result);
		return json;
	}

	/**
	 * 获取用户信息
	 *
	 */
	public static JSONObject getUserInfo(String accessToken, String openId) {
		String getUserInfo = GET_USERINFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		String result = "";
		try {
			result = sendGet(getUserInfo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject json = JSONObject.parseObject(result);
		return json;
	}

}
