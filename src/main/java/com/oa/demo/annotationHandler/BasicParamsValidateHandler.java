package com.oa.demo.annotationHandler;

import com.oa.demo.annotation.params.BasicParamsValidate;
import com.oa.demo.dto.BasicReqParamsDTO;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BasicParamsValidateHandler implements ConstraintValidator<BasicParamsValidate, BasicReqParamsDTO> {

	private static final String URL_NOT_EMPTY = "url can not be empty!";
	private static final String PAYLOAD_NOT_EMPTY = "payload can not be empty!";

	private static boolean isEmpty(Object o) {
		if (o == null || "".equals(o)) return true;
		return false;
	}

	@Override
	public boolean isValid(BasicReqParamsDTO params, ConstraintValidatorContext ctx) {
		// 关闭默认消息
		ctx.disableDefaultConstraintViolation();
		if (isEmpty(params.getUrl())) {
			ctx.buildConstraintViolationWithTemplate(URL_NOT_EMPTY).addConstraintViolation();
			return false;
		}
//		if (isEmpty(params.getPayload())) {
//			ctx.buildConstraintViolationWithTemplate(PAYLOAD_NOT_EMPTY).addConstraintViolation();
//			return false;
//		}
		if (isEmpty(params.getMethod())) {
			params.setMethod(String.valueOf(RequestMethod.GET));
		}
		return true;
	}
}
