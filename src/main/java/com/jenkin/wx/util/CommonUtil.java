package com.jenkin.wx.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.ParseException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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

	// 2.获取getJsapiTicket的接口地址,有效期为7200秒
	private static final String GET_JSAPITICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

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

	/**
	 * GET、POST请求
	 * 
	 * @param url
	 * @param method
	 * @return
	 * @throws IOException
	 */
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
	 * GET、POST请求
	 * 
	 * @param url
	 * @param method
	 * @return
	 * @throws IOException
	 */
	public static JSONObject doGet(String url) throws Exception {
		JSONObject json = null;
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
		json = JSONObject.parseObject(result);
		return json;
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

	/**
	 * @desc ：2.获取JsapiTicket
	 * 
	 * @param accessToken
	 *            有效凭证
	 * @return
	 * @throws Exception
	 *             String
	 */
	public static String getJsapiTicket(String accessToken) throws Exception {
		// 1.获取请求url
		String url = GET_JSAPITICKET_URL.replace("ACCESS_TOKEN", accessToken);

		// 2.发起GET请求，获取返回结果
		JSONObject jsonObject = CommonUtil.doGet(url);
		// 3.解析结果，获取accessToken
		String jsapiTicket = "";
		if (null != jsonObject) {
			// 4.错误消息处理
			if (jsonObject.getInteger("errcode") != null && 0 != jsonObject.getInteger("errcode")) {
				int errCode = jsonObject.getInteger("errcode");
				String errMsg = jsonObject.getString("errmsg");
				throw new Exception("error code:" + errCode + ", error message:" + errMsg);
				// 5.成功获取jsapiTicket
			} else {
				jsapiTicket = jsonObject.getString("ticket");
			}
		}

		return jsapiTicket;
	}

	/**
	 * @desc ： 4.1 生成签名的函数
	 * 
	 * @param ticket
	 *            jsticket
	 * @param nonceStr
	 *            随机串，自己定义
	 * @param timeStamp
	 *            生成签名用的时间戳
	 * @param url
	 *            需要进行免登鉴权的页面地址，也就是执行dd.config的页面地址
	 * @return
	 * @throws Exception
	 *             String
	 */

	public static String getSign(String jsTicket, String nonceStr, Long timeStamp, String url) throws Exception {
		String plainTex = "jsapi_ticket=" + jsTicket + "&noncestr=" + nonceStr + "&timestamp=" + timeStamp + "&url="
				+ url;
		System.out.println(plainTex);
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(plainTex.getBytes("UTF-8"));
			return byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new Exception(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * @desc ：4.2 将bytes类型的数据转化为16进制类型
	 * 
	 * @param hash
	 * @return String
	 */
	private static String byteToHex(byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", new Object[] { Byte.valueOf(b) });
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}


	/**
	 * 获取时间戳(秒)
	 */
	public static String getTimestamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

	/**
	 * @desc ：4.获取前端jsapi需要的配置参数
	 * 
	 * @param request
	 * @return String
	 */

	public static Map<String, Object> getJsapiConfig(HttpServletRequest request, String token) {

		// 1.准备好参与签名的字段 // 1.1 url

		// 以http://localhost/test.do?a=b&c=d为例
		// request.getRequestURL的结果是http://localhost/test.do
		// request.getQueryString的返回值是a=b&c=d

		String urlString = request.getRequestURL().toString();
		String queryString = request.getQueryString();
		String queryStringEncode = null;
		String url;
		if (queryString != null) {
			queryStringEncode = URLDecoder.decode(queryString);
			url = urlString + "?" + queryStringEncode;
		} else {
			url = urlString;
		}

		// 1.2 noncestr
		String nonceStr = UUID.randomUUID().toString(); // 随机数 //
		// 1.3 timestamp

		// 时间戳参数
		long timeStamp = System.currentTimeMillis() / 1000; //
		String signedUrl = url;

		String accessToken = null;
		String ticket = null;

		String signature = null; // 签名

		try { // 1.4 jsapi_ticket

			accessToken = token;
			ticket = CommonUtil.getJsapiTicket(accessToken);

			// 2.进行签名，获取signature
			signature = CommonUtil.getSign(ticket, nonceStr, timeStamp, signedUrl);

		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("accessToken", accessToken);
		map.put("appId", CommonUtil.APPID);
		map.put("ticket", ticket);
		map.put("nonceStr", nonceStr);
		map.put("timestamp", timeStamp);
		map.put("signature", signature);
		return map;
	}

	/**
	 * @desc ：4.获取前端jsapi需要的配置参数
	 * 
	 * @param request
	 * @return String
	 */
	// public static String getJsapiConfig(HttpServletRequest request, String
	// token) {
	//
	// // 1.准备好参与签名的字段
	// // 1.1 url
	// /*
	// * 以http://localhost/test.do?a=b&c=d为例
	// * request.getRequestURL的结果是http://localhost/test.do
	// * request.getQueryString的返回值是a=b&c=d
	// */
	// String urlString = request.getRequestURL().toString();
	// String queryString = request.getQueryString();
	// String queryStringEncode = null;
	// String url;
	// if (queryString != null) {
	// queryStringEncode = URLDecoder.decode(queryString);
	// url = urlString + "?" + queryStringEncode;
	// } else {
	// url = urlString;
	// }
	//
	// // 1.2 noncestr
	// String nonceStr = UUID.randomUUID().toString(); // 随机数
	// // 1.3 timestamp
	// long timeStamp = System.currentTimeMillis() / 1000; // 时间戳参数
	//
	// String signedUrl = url;
	//
	// String accessToken = null;
	// String ticket = null;
	//
	// String signature = null; // 签名
	//
	// try {
	// // 1.4 jsapi_ticket
	//
	// accessToken = token;
	// ticket = CommonUtil.getJsapiTicket(accessToken);
	//
	// // 2.进行签名，获取signature
	// signature = CommonUtil.getSign(ticket, nonceStr, timeStamp, signedUrl);
	//
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// String configValue = "{signature:'" + signature + "',noncestr:'" +
	// nonceStr + "',timestamp:'" + timeStamp
	// + "',appId:'" + CommonUtil.APPID + "'}";
	// return configValue;
	// }

	/**
	 * 获取临时素材
	 */
	private static InputStream getMediaStream(String mediaId, String access_token) throws IOException {
		String url = "https://api.weixin.qq.com/cgi-bin/media/get";
		// Map map = WxAccessToken.getSavedAccessToken();
		// String access_token = (String)map.get("access_token");
		String params = "access_token=" + access_token + "&media_id=" + mediaId;
		InputStream is = null;
		try {
			String urlNameString = url + "?" + params;
			URL urlGet = new URL(urlNameString);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			http.connect();
			// 获取文件转化为byte流
			is = http.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

	/**
	 * 保存图片至服务器
	 * 
	 * @param mediaId
	 * @return 文件名
	 */
	public static String saveImageToDisk(String mediaId, String access_token, HttpServletRequest request)
			throws IOException {
		 String filename = "";
		String spath = "";
	        InputStream inputStream = getMediaStream(mediaId,access_token);
	        byte[] data = new byte[1024];
	        int len ;
	        FileOutputStream fileOutputStream = null;
	        try {
			// 服务器存图路径
			String path = request.getSession().getServletContext().getRealPath("") + "image/";
			judeFileExists(path);
			filename = System.currentTimeMillis() + ".jpg";
			String realpath = path + filename;
			spath = "image/" + filename;
			System.out.println(spath);
			fileOutputStream = new FileOutputStream(realpath);
	            while ((len = inputStream.read(data)) != -1) {
	                fileOutputStream.write(data, 0, len);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (inputStream != null) {
	                try {
	                    inputStream.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (fileOutputStream != null) {
	                try {
	                    fileOutputStream.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		return spath;
	}

	/**
	 * 保存音频到服务器
	 * 
	 * @param accessToken
	 * @param mediaId
	 * @param picName
	 * @param picPath
	 * @throws Exception
	 */
	public static void saveVideoToDisk(String accessToken, String mediaId, HttpServletRequest request)
			throws Exception {
		InputStream inputStream = getMediaStream(accessToken, mediaId);
		byte[] data = new byte[10240];
		int len = 0;
		FileOutputStream fileOutputStream = null;
		// 服务器存图路径
		String path = request.getSession().getServletContext().getRealPath("") + "video/";
		judeFileExists(path);
		String filename = System.currentTimeMillis() + ".amr";
		String realpath = path + filename;
		try {
			fileOutputStream = new FileOutputStream(realpath);
			while ((len = inputStream.read(data)) != -1) {
				fileOutputStream.write(data, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 判断文件是否存在
	public static void judeFileExists(String filename) {
		File file = new File(filename);
		// 如果文件夹不存在则创建
		if (!file.exists()) {
			file.mkdir();
		}
	}

	/**
	 * 微信保存临时素材方法
	 * 
	 * @param fileurl
	 *            文件路径
	 * @param type
	 *            文件类型
	 * @param token
	 *            微信令牌
	 * @return 文件ID
	 */
	public static JSONObject addMaterialEver(String fileurl, String type, String token) {
		try {
			// 创建文件
			File file = new File(fileurl);
			if (!file.exists()) {
				return null;
			}
			// 微信接口地址
			String path = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=" + token + "&type=" + type;
			// 调用工具类进行上传
			String result = connectHttpsByPost(path, file);
			System.out.println("转换前:" + result);
			// 替换字符串中不需要的字符
			result = result.replaceAll("[\\\\]", "");
			System.out.println("result:" + result);
			// 将字符串转换成json对象
			JSONObject resultJSON = JSONObject.parseObject(result);
			// 判断是否有ID返回
			if (resultJSON != null) {
				if (resultJSON.get("media_id") != null) {
					System.out.println("上传" + type + "临时素材成功");
					return resultJSON;
				} else {
					System.out.println("上传" + type + "临时素材失败");
				}
			}
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String connectHttpsByPost(String path, File file)
			throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
		URL urlObj = new URL(path);
		// 连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		String result = null;
		con.setDoInput(true);

		con.setDoOutput(true);
		// post方式不能使用缓存
		con.setUseCaches(false);

		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");
		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

		// 请求正文信息
		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // 必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"media\";filelength=\"" + file.length() + "\";filename=\""

				+ file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		byte[] head = sb.toString().getBytes("utf-8");
		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head);

		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();
		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
		out.write(foot);
		out.flush();
		out.close();
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return result;
	}

}

