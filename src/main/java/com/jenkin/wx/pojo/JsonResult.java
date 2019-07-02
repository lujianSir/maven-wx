package com.jenkin.wx.pojo;

public class JsonResult {
	private String status; // 状态
	private String message; // 状态描述
	private Object result; // 结果
	
	public static final String SUCCESS_CODE = "SUCCESS"; // 成功的状态码
	public static final String FAILED_CODE = "FAILED"; // 失败的状态码

	public JsonResult(String status, String message, Object result) {
		super();
		this.status = status;
		this.message = message;
		this.result = result;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}

}
