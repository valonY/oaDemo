package com.oa.demo.utils;

import com.oa.demo.dto.BasicReqParamsDTO;
import com.oa.demo.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import com.oa.demo.dto.ResultCreator;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

@Slf4j
public class Fetcher {
	private static final RestTemplate restTemplate = new RestTemplate();

	private static final HashMap<String, String> restTmplFnMap = new HashMap(){
		{
			put(String.valueOf(RequestMethod.GET), "getForObject");
			put(String.valueOf(RequestMethod.POST), "postForObject");
			put(String.valueOf(RequestMethod.DELETE), "delete");
			put(String.valueOf(RequestMethod.PUT), "put");
		}
	};

	public static ResponseResult request(BasicReqParamsDTO params) {
		log.info(params.toString());
		try {
			String method = restTmplFnMap.get(params.getMethod());
			Method fetch = RestTemplate.class.getMethod(method, String.class, Object.class.getClass(), Map.class);
			Callable<ResponseResult> callable = () -> {
				Object res =  fetch.invoke(restTemplate, params.getUrl(), Object.class, params.getPayload());
				return ResultCreator.SUCCESS.setData(res);
			};
			FutureTask<ResponseResult> task = new FutureTask(callable);
			new Thread(task).start();
			return task.get();
		} catch(Exception e) {
			e.printStackTrace();
			return ResultCreator.UNKNOWN_ERROR.setError(e.getMessage());
		}
	}
}
