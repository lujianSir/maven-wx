package com.jenkin.wx.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
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
import com.jenkin.wx.util.ApiOrcUtil;
import com.jenkin.wx.util.CommonUtil;

@Controller
public class ArtificialIntelligenceController {

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
	@RequestMapping("/inintWXImage")
	public ModelAndView getImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		String accessToken = weChatService.getAccessToken();
		Map<String, Object> map = CommonUtil.getJsapiConfig(request, accessToken);
		modelAndView.addObject("wxsign", map);
		modelAndView.setViewName("imagerecognition");
		return modelAndView;
	}

	/**
	 * 获取微信服务器图片(一张)
	 * 
	 * @param mediaId
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/uploadPicture", method = RequestMethod.POST)
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
	 * 识别图片身份证
	 * 
	 * @param mediaId
	 * @return
	 */
	@RequestMapping(value = "/distinguishImage", method = RequestMethod.POST, produces = {
			"application/text;charset=UTF-8" })
	@ResponseBody
	public String distinguishImage(String mediaId, String mtype) {

		String message = "";
		TemporaryResources temporaryResources = weChatService.queryTById(mediaId);
		String realPah = temporaryResources.getRealPath();
		File f = new File(realPah);
		if (f.exists()) {
			if (mtype.equals("1") || mtype.endsWith("身份证")) {
				message = ApiOrcUtil.getPictureString(realPah);
			} else {
				message = ApiOrcUtil.getPlateLicense(realPah);
			}

		} else {
			message = "图片不存在";
		}
		System.out.println(message);
		return message;
	}

}
