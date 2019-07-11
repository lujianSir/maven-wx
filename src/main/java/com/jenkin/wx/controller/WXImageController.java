package com.jenkin.wx.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jenkin.wx.pojo.TemporaryResources;
import com.jenkin.wx.service.WeChatService;
import com.jenkin.wx.util.CommonUtil;

@Controller
public class WXImageController {

	@Autowired
	private WeChatService weChatService;

	/**
	 * 上传图片
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getImage")
	public ModelAndView getImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		String accessToken = weChatService.getAccessToken();
		Map<String, Object> map = CommonUtil.getJsapiConfig(request, accessToken);
		modelAndView.addObject("wxsign", map);
		modelAndView.setViewName("uploadImg");
		return modelAndView;
	}

	// @RequestMapping("/getImage")
	// @ResponseBody
	// public JSONObject getImage(HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// String accessToken = weChatService.getAccessToken();
	// String result = CommonUtil.getJsapiConfig(request, accessToken);
	// JSONObject json = JSONObject.parseObject(result);
	// return json;
	// }
	/**
	 * 获取微信服务器图片(一张)
	 * 
	 * @param mediaId
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/savePicture", method = RequestMethod.POST)
	@ResponseBody
	public String savePicture(String mediaId, HttpServletRequest request) throws IOException {
		String accessToken = weChatService.getAccessToken();
		String imgPrevPath = CommonUtil.saveImageToDisk(mediaId, accessToken, request);
		String name = System.currentTimeMillis() + "";
		TemporaryResources temporaryResources = new TemporaryResources();
		temporaryResources.setMedia_id(mediaId);
		temporaryResources.setUrl(imgPrevPath);
		temporaryResources.setName(name);
		temporaryResources.setMdate(new Date());
		temporaryResources.setMtype(1);
		String rootPath = request.getSession().getServletContext().getRealPath("/").replace("\\", "/");
		temporaryResources.setRealPath(rootPath + imgPrevPath);
		int num = weChatService.insertTemporaryResources(temporaryResources);
		if (num > 0) {
			return imgPrevPath;
		} else {
			return null;
		}

	}

	/**
	 * 获取微信服务器图片(多张)
	 * 
	 * @param mediaId
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/savePictures", method = RequestMethod.POST)
	@ResponseBody
	public String savePictures(String mediaIds, HttpServletRequest request) throws IOException {
		String accessToken = weChatService.getAccessToken();
		String imgPrevPaths = "";
		if (mediaIds != null && !("").equals(mediaIds)) {
			String[] smediaId = mediaIds.split(",");
			for (int i = 0; i < smediaId.length; i++) {
				String mediaId = smediaId[i];
				imgPrevPaths += CommonUtil.saveImageToDisk(mediaId, accessToken, request);
				if (i < smediaId.length - 1) {
					imgPrevPaths += ",";
				}
			}
		}

		return imgPrevPaths;
	}

	/**
	 * 查询临时图片
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/queryTemporaryImages", method = RequestMethod.POST)
	@ResponseBody
	public List<TemporaryResources> queryTemporaryImages() throws IOException {
		TemporaryResources temporaryResources = new TemporaryResources();
		temporaryResources.setMtype(1);
		List<TemporaryResources> list = weChatService.queryTemporaryImages(temporaryResources);
		// 获得令牌
		/*
		 * String accessToken = weChatService.getAccessToken(); for (int i = 0;
		 * i < list.size(); i++) { // 调用上传文件方法 url文件本地保存路径 voice文件类型
		 * accessToken微信令牌 JSONObject JSONObject jsonObject =
		 * CommonUtil.addMaterialEver(list.get(i).getRealPath(), "image",
		 * accessToken); if (jsonObject != null &&
		 * jsonObject.getString("media_id") != null) { // 获得保存到微信服务器返回的ID String
		 * mediaId = jsonObject.getString("media_id");
		 * list.get(i).setMedia_id(mediaId); } }
		 */
		return list;
	}

	/**
	 * 获取地理位置
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getlocation")
	public ModelAndView getlocation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		String accessToken = weChatService.getAccessToken();
		Map<String, Object> map = CommonUtil.getJsapiConfig(request, accessToken);
		modelAndView.addObject("wxsign", map);
		modelAndView.setViewName("getlocation");
		return modelAndView;
	}

	/**
	 * 录音
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getRecord")
	public ModelAndView getRecord(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		String accessToken = weChatService.getAccessToken();
		Map<String, Object> map = CommonUtil.getJsapiConfig(request, accessToken);
		modelAndView.addObject("wxsign", map);
		modelAndView.setViewName("getRecord");
		return modelAndView;
	}

	/**
	 * 获取微信服务器上的录音
	 * 
	 * @param mediaId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveVideo", method = RequestMethod.POST)
	@ResponseBody
	public String saveVideo(String serverId, HttpServletRequest request) throws Exception {
		String accessToken = weChatService.getAccessToken();
		CommonUtil.saveVideoToDisk(serverId, accessToken, request);
		return "success";
	}

	/**
	 * 分享功能
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/share")
	public ModelAndView share(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		String accessToken = weChatService.getAccessToken();
		Map<String, Object> map = CommonUtil.getJsapiConfig(request, accessToken);
		modelAndView.addObject("wxsign", map);
		modelAndView.setViewName("share");
		return modelAndView;
	}

}
