package com.oa.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseResult {
	private Integer code;
	private String msg;
	private Object data;
}