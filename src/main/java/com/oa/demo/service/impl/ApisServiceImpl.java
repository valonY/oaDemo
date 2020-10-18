package com.oa.demo.service.impl;

import com.oa.demo.dto.ApiCreateDTO;
import com.oa.demo.dto.ResultCreator;
import com.oa.demo.entity.Apis;
import com.oa.demo.mapper.ApisMapper;
import com.oa.demo.service.ApisService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oa.demo.utils.BeanCopy;
import com.oa.demo.vo.ResponseResult;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author valonYe
 * @since 2020-10-15
 */
@Service
public class ApisServiceImpl extends ServiceImpl<ApisMapper, Apis> implements ApisService {
	@Override
	public boolean createApi(ApiCreateDTO params) {
		Apis apis = new Apis();
		BeanCopy.copy(params, apis);
		return this.save(apis);
	}

	@Override
	public ResponseResult updateApi(Long id, ApiCreateDTO params) {
		Apis apis = this.getById(id);
		if (!Optional.ofNullable(apis).isPresent()) return ResultCreator.FAILED_ERROR.setError("查无数据");
		BeanCopy.copyIgnoreNull(params, apis);
		apis.setId(id);
		return ResultCreator.SUCCESS.setData(this.updateById(apis));
	}
}
