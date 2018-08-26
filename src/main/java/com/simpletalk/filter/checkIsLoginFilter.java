package com.simpletalk.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.directwebremoting.ui.dwr.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName = "checkIsLoginFilter", urlPatterns = "*.jsp")
public class checkIsLoginFilter implements Filter {
	Logger Log = LoggerFactory.getLogger(checkIsLoginFilter.class);

	public checkIsLoginFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// Log.info("=======doFilter========");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		// 取得根目录所对应的绝对路径:
		String currentURL = request.getRequestURI();
		String contextPath = request.getContextPath();

		Log.info("currentURL = {} , contextPath = {}", new Object[] { currentURL, contextPath });
		currentURL = currentURL.replace(contextPath, "");
		String targetURL;
		if ("/".equals(currentURL)) {
			targetURL = currentURL;
		} else {
			targetURL = currentURL.substring(currentURL.indexOf("/"), currentURL.length());
			//Log.info(" targetURL = {} ", new Object[] { targetURL });
		}
		if (!"/".equals(targetURL)) {// 判断当前页是否是重定向以后的登录页面页面，如果是就不做session的判断，防止出现死循环
			if (session == null || session.getAttribute("username") == null) {
				// 用户登录以后需手动添加session
				Log.info("request.getContextPath()=" + request.getContextPath());
				// 如果session为空表示用户没有登录就重定向到login.jsp页面
				response.sendRedirect(request.getContextPath() + "/");
				return;
			}
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		Log.info("[init]");
	}

}
