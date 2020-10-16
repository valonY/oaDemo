package com.oa.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oa.demo.dto.ApiCreateDTO;
import com.oa.demo.dto.ResponseResult;
import com.oa.demo.dto.ResultCreator;
import com.oa.demo.entity.Apis;
import com.oa.demo.service.ApisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Method;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("api")
public class ApiController {

	@Autowired
	private ApisService apisService;

	@PostMapping("create")
	public ResponseResult createApi(@RequestBody @Valid ApiCreateDTO params) {

		BeanCopier beanCopier = BeanCopier.create(ApiCreateDTO.class, Apis.class, false);
		Apis apis = new Apis();
		beanCopier.copy(params, apis, null);
		return ResultCreator.SUCCESS.setData(apisService.save(apis));
	}

	@DeleteMapping("delete/{id}")
	public ResponseResult deleteApiById(@PathVariable("id") Long id) {
		Apis apis = apisService.getById(id);
		if (apis == null) return ResultCreator.FAILED_ERROR.setError("查无数据");
		return ResultCreator.SUCCESS.setData(apisService.removeById(id));
	}

	@PutMapping("update/{id}")
	public ResponseResult updateApi(@PathVariable("id") Long id, @RequestBody ApiCreateDTO params) {
		BeanCopier beanCopier = BeanCopier.create(ApiCreateDTO.class, Apis.class, true);
		Apis apis = apisService.getById(id);
		if (!Optional.ofNullable(apis).isPresent()) return ResultCreator.FAILED_ERROR.setError("查无数据");
		log.info(params.toString());
		beanCopier.copy(params, apis, (value, target, context) -> {
			if(!Optional.ofNullable(value).isPresent()) {
				String getFnName = "g" + context.toString().substring(1);
				try {
					Method ctx = apis.getClass().getDeclaredMethod(getFnName);
					Object val = ctx.invoke(apis);
					return val;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
			return value;
		});
		apis.setId(id);
		log.info(apis.toString());
		return ResultCreator.SUCCESS.setData(apisService.updateById(apis));
	}

	@GetMapping("list")
	public ResponseResult getAllApis() {
		return ResultCreator.SUCCESS.setData(apisService.list());
	}
//	@RequestMapping
//	public ResponseResult defaultHandler () {
//		return ResultCreator.UNKNOWN_ERROR.setError("url error!");
//	}
}
