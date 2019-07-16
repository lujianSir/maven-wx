package com.jenkin.wx.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.imageclassify.AipImageClassify;
import com.jenkin.wx.pojo.Image;
import com.jenkin.wx.pojo.TemporaryResources;
import com.jenkin.wx.service.WeChatService;
import com.jenkin.wx.util.CommonUtil;
import com.lujian.facelogin.AiFaceObject;
import com.lujian.facelogin.AiFaceUtil;
import com.lujian.facelogin.Base64Convert;
import com.lujian.image.ImageClassUtil;
import com.lujian.image.ImageClassifyObject;
import com.lujian.orc.ApiOrcUtil;

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
	 * 识别图片身份证、车牌
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

	/**
	 * 人脸上传
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/inintFace")
	public ModelAndView inintFace(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		String accessToken = weChatService.getAccessToken();
		Map<String, Object> map = CommonUtil.getJsapiConfig(request, accessToken);
		modelAndView.addObject("wxsign", map);
		modelAndView.setViewName("face");
		return modelAndView;
	}

	/**
	 * 注册人脸
	 * 
	 * @param mediaId
	 * @param mtype
	 * @return
	 */
	@RequestMapping(value = "/faceRegist", method = RequestMethod.POST, produces = {
			"application/text;charset=UTF-8" })
	@ResponseBody
	public String faceRegist(String mediaId) {
		String message = "";
		TemporaryResources temporaryResources = weChatService.queryTById(mediaId);
		String realPah = temporaryResources.getRealPath();
		Image image = new Image();
		String img = Base64Convert.GetImageStr(realPah);
		image.setImage(img);
		image.setImageType("BASE64");
		AipFace client = AiFaceObject.getClient();
		String groupId = "ceshi";
		AiFaceUtil.FaceGrouption(client, groupId);// 查询是否存在数组
		String userId = Base64Convert.getUUID32();
		File f = new File(realPah);
		if (f.exists()) {
			message = AiFaceUtil.Facedetection(client, image);// 检测照片是否是对的
			if (!message.equals("照片不对，需要生活照")) {// 对的 上传到百度云
				message = AiFaceUtil.Faceregistrtion(client, groupId, userId, image);
			} else {
				JSONObject jsobj1 = new JSONObject();
				jsobj1.put("message", "照片不对，需要生活照");
				message = jsobj1.toString();
			}
		} else {
			message = "{'message':'照片不存在'}";
		}
		System.out.println(message);
		return message;
	}

	/**
	 * 验证人脸图片
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/inintsearchFace")
	public ModelAndView inintsearchFace(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		String accessToken = weChatService.getAccessToken();
		Map<String, Object> map = CommonUtil.getJsapiConfig(request, accessToken);
		modelAndView.addObject("wxsign", map);
		modelAndView.setViewName("searchface");
		return modelAndView;
	}

	/**
	 * 人脸识别
	 * 
	 * @param mediaId
	 * @return
	 */
	@RequestMapping(value = "/faceSearch", method = RequestMethod.POST, produces = { "application/text;charset=UTF-8" })
	@ResponseBody
	public String faceSearch(String mediaId) {
		String message = "";
		TemporaryResources temporaryResources = weChatService.queryTById(mediaId);
		String realPah = temporaryResources.getRealPath();
		Image image = new Image();
		String img = Base64Convert.GetImageStr(realPah);
		image.setImage(img);
		image.setImageType("BASE64");
		AipFace client = AiFaceObject.getClient();
		String groupId = "ceshi";
		String userId = Base64Convert.getUUID32();
		File f = new File(realPah);
		if (f.exists()) {
			message = AiFaceUtil.Facedetection(client, image);// 检测照片是否是对的
			if (!message.equals("照片不对，需要生活照")) {// 对的 与百度云上面进行验证
				message = AiFaceUtil.Faceregistrtion(client, groupId, image);
			} else {
				JSONObject jsobj1 = new JSONObject();
				jsobj1.put("message", "照片不对，需要生活照");
				message = jsobj1.toString();
			}

		} else {
			message = "{'message':'照片不存在'}";
		}
		System.out.println(message);
		return message;
	}

	/**
	 * 图像分析
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/inintImageClassify")
	public ModelAndView inintImageClassify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		String accessToken = weChatService.getAccessToken();
		Map<String, Object> map = CommonUtil.getJsapiConfig(request, accessToken);
		modelAndView.addObject("wxsign", map);
		modelAndView.setViewName("inintImageClassify");
		return modelAndView;
	}

	/**
	 * 分析图像的内容
	 * 
	 * @param mediaId
	 * @return
	 */
	@RequestMapping(value = "/imageClassify", method = RequestMethod.POST, produces = {
			"application/text;charset=UTF-8" })
	@ResponseBody
	public String imageClassify(String mediaId) {
		String message = "";
		TemporaryResources temporaryResources = weChatService.queryTById(mediaId);
		String realPah = temporaryResources.getRealPath();
		AipImageClassify client = ImageClassifyObject.getClient();
		message = ImageClassUtil.animalDetect(client, realPah);
		System.out.println(message);
		return message;
	}

}
