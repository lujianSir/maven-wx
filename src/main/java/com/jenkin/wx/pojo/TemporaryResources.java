package com.jenkin.wx.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 临时图文
 * 
 * @author Administrator
 *
 */
public class TemporaryResources implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String media_id;// 临时ID

	private String name;// 上传的名称

	private String url;// 上传的地址

	private Date mdate;// 上传的时间

	private int mtype;// 状态 1.图片 2.语音

	private String realPath;// 真实地址

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getMdate() {
		return mdate;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}

	public int getMtype() {
		return mtype;
	}

	public void setMtype(int mtype) {
		this.mtype = mtype;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

}
