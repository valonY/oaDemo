package com.oa.demo.controller;

import com.oa.demo.dto.ApiCreateDTO;
import com.oa.demo.vo.ResponseResult;
import com.oa.demo.dto.ResultCreator;
import com.oa.demo.entity.Apis;
import com.oa.demo.service.ApisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("api")
public class ApiController {

	@Autowired
	private ApisService apisService;

	@PostMapping("create")
	public ResponseResult createApi(@RequestBody @Valid ApiCreateDTO params) {
		return ResultCreator.SUCCESS.setData(apisService.createApi(params));
	}

	@DeleteMapping("delete/{id}")
	public ResponseResult deleteApiById(@PathVariable("id") Long id) {
		Apis apis = apisService.getById(id);
		if (apis == null) return ResultCreator.FAILED_ERROR.setError("查无数据");
		return ResultCreator.SUCCESS.setData(apisService.removeById(id));
	}

	@PutMapping("update/{id}")
	public ResponseResult updateApi(@PathVariable("id") Long id, @RequestBody ApiCreateDTO params) {
		return apisService.updateApi(id, params);
	}

	@GetMapping("list")
	public ResponseResult getAllApis() {
		return ResultCreator.SUCCESS.setData(apisService.list());
	}
}
