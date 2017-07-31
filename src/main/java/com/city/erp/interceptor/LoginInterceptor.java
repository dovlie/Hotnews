package com.city.erp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.city.erp.model.zqy.AdminidtratorModel;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = Logger.getLogger(LoginInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		AdminidtratorModel am = (AdminidtratorModel) request.getSession().getAttribute("user");
		System.out.println("拦截器已经开始工作");
		if (am == null) {
			System.out.println("用户没有登录");

			return false;
		} else
			return true;
	}
}
