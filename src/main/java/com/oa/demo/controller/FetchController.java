package com.oa.demo.controller;

import com.oa.demo.dto.BasicReqParamsDTO;
import com.oa.demo.dto.ResponseResult;
import com.oa.demo.utils.Fetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
public class FetchController {

	@PostMapping("fetch")
	public ResponseResult fetch(@RequestBody @Valid BasicReqParamsDTO params) {
		log.info("test: " + params.toString());
		return Fetcher.request(params);
	}
}
