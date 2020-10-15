package com.oa.demo.excetption;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParamsException extends RuntimeException {
	public ParamsException(String msg) {
		super(msg);
	}
}
