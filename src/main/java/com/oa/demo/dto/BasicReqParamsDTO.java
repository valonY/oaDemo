package com.oa.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.oa.demo.annotation.params.BasicParamsValidate;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@BasicParamsValidate
public class BasicReqParamsDTO {

	private String method;

	private String url;

	private HashMap<String, Object> payload;

}
