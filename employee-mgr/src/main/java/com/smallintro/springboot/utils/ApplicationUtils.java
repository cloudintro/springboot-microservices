package com.smallintro.springboot.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ApplicationUtils {

	@Autowired
	private ResourceBundleMessageSource messageSource;

	public String getI18nMessage(String msgKey, String locale) {
		if (!StringUtils.isEmpty(locale)) {
			return messageSource.getMessage(msgKey, null, new Locale(locale));
		}
		return messageSource.getMessage(msgKey, null, LocaleContextHolder.getLocale());
	}

}
