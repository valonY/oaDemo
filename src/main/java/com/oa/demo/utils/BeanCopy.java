package com.oa.demo.utils;

import org.springframework.cglib.beans.BeanCopier;

import java.lang.reflect.Method;
import java.util.Optional;

public class BeanCopy {
	private volatile static Class FROM;
	private volatile static Class TO;
	public static synchronized void create(Class from, Class to) {
		FROM = from;
		TO = to;
	}
	private static class SimpleBeanCopyHolder {
		static BeanCopier beanCopier = BeanCopier.create(FROM, TO, false);
	}
	private static class IgnoreNullBeanHolder {
		static BeanCopier beanCopier = BeanCopier.create(FROM, TO, true);
	}

	public static synchronized void copy(Object from, Object to) {
		FROM = from.getClass();
		TO = to.getClass();
		SimpleBeanCopyHolder.beanCopier.copy(from, to, null);
	}

	public static synchronized void copyIgnoreNull(Object from, Object to) {
		FROM = from.getClass();
		TO = to.getClass();
		IgnoreNullBeanHolder.beanCopier.copy(from, to, (value, target, context) -> {
			if(!Optional.ofNullable(value).isPresent()) {
				String getFnName = "g" + context.toString().substring(1);
				try {
					Method ctx = TO.getDeclaredMethod(getFnName);
					Object val = ctx.invoke(to);
					return val;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
			return value;
		});
	}
}
