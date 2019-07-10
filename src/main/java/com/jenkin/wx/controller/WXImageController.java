package com.jenkin.wx.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	 * 获取微信服务器图片
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
		return imgPrevPath;
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
