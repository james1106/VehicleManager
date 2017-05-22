package com.xpizza.core.component.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xpizza.core.lang.exception.NotAuthorizedException;
import com.xpizza.vclemgr.constant.Constant;
import com.xpizza.vclemgr.domain.User;

/**
 * 
 * @ClassName: LawlessAuthInterceptor
 * @Description:非法登录拦截器(单点登录时注册用户信息)
 * @author: Xpizza
 * @date: Nov 2, 2016 12:20:03 AM
 */
public class LawlessAuthInterceptor extends HandlerInterceptorAdapter {

	static final Logger logger = LoggerFactory.getLogger(LawlessAuthInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		if (url.replace(contextPath, "").replace("/", "").trim().equals("")) {
			return true;
		}
		/* 1.例外访问方式 */
		if (isExceptionalUrl(url)) {
			logger.debug("例外路径,不做PC端权限拦截");
			return true;
		}
		User user = (User) request.getSession().getAttribute(Constant.SESSION_USER);
		if (user == null) {
			throw new NotAuthorizedException("无权限访问");
		}
		return true;
	}

	/** 判断是否是例外路径:1.移动请求(以.action为后缀的请求);2.错误页面;3.登录页面 */
	private boolean isExceptionalUrl(String url) {
		return url.contains("auth");
	}

}
