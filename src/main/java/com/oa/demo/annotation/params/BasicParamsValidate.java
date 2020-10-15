package com.oa.demo.annotation.params;

import com.oa.demo.annotationHandler.BasicParamsValidateHandler;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { BasicParamsValidateHandler.class })
public @interface BasicParamsValidate {

	String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
