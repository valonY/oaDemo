package com.oa.demo.dto;

import com.oa.demo.vo.ResponseResult;

public enum ResultCreator {
	SUCCESS(0, ""),
	PARAMS_ERROR(1, "参数错误"),
	UNKNOWN_ERROR(2, "未知错误"),
	FAILED_ERROR(3, "数据错误");

	private Integer code;
	private String msg;
	private ResponseResult result;

	ResultCreator(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
		result = new ResponseResult(code, msg, null);
	}

	public ResponseResult getResult() {
		return result;
	}

	public ResponseResult setData(Object data) {
		result.setData(data);
		return result;
	}

	public ResponseResult setError(String error) {
		result.setMsg(error);
		return result;
	}

}
