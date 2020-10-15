package com.oa.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ApiCreateDTO {
	private String groupName;

	/**
	 * env name
	 */
	private String envName;

	/**
	 * api address
	 */
	@NotNull
	@NotBlank
	private String apiUrl;

	/**
	 * api name
	 */
	private String apiName;

	/**
	 * http method
	 */
	private String httpMethod;
}
