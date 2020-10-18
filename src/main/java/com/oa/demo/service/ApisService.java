package com.oa.demo.service;

import com.oa.demo.dto.ApiCreateDTO;
import com.oa.demo.entity.Apis;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oa.demo.vo.ResponseResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author valonYe
 * @since 2020-10-15
 */
public interface ApisService extends IService<Apis> {
	boolean createApi(ApiCreateDTO params);
	ResponseResult updateApi(Long id, ApiCreateDTO params);
}
