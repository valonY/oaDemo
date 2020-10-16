package com.oa.demo.controller.advice;

import com.oa.demo.dto.ResponseResult;
import com.oa.demo.dto.ResultCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice("com.oa.demo.controller")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseResult paramsMissingHandler(MissingServletRequestParameterException e) {
		return ResultCreator.PARAMS_ERROR.setError("The field of '" + e.getParameterName() + "' can not be empty!");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseResult paramsBodyMissingHandler(HttpMessageNotReadableException e) {
		return ResultCreator.PARAMS_ERROR.setError("request body error!");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseResult paramsErrorHandler(MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();
		ResultCreator errorHandler = ResultCreator.PARAMS_ERROR;
		if (bindingResult.hasErrors()) {
			String errMsg = bindingResult
				.getAllErrors()
				.stream()
				.map(ObjectError::getDefaultMessage)
				.reduce((m1, m2) -> m1 + ";" + m2)
				.orElse("parameters error!");
			log.error("error: " + errMsg);
			return errorHandler.setError(errMsg);
		}
		return errorHandler.getResult();
	}

//	public ResponseResult notFoundHandler(Exception e) {
//		log.info("23333" + e.toString());
//		log.info((e instanceof NoHandlerFoundException) + "");
//		return ResultCreator.FAILED_ERROR.setError("request not found!");
//	}

}
