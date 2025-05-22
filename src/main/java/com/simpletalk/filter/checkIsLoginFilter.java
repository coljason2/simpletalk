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

import lombok.extern.slf4j.Slf4j;
import org.directwebremoting.ui.dwr.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class checkIsLoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();

        log.info("CheckIsLoginFilter: request URI = {}", uri);

        // 排除不需要檢查登入的路徑，例如登入頁面、靜態資源等
        if (uri.startsWith(contextPath + "/login") ||
                uri.startsWith(contextPath + "/css") ||
                uri.startsWith(contextPath + "/js") ||
                uri.startsWith(contextPath + "/dwr") ||
                uri.startsWith(contextPath + "/images") ||
                uri.equals(contextPath + "/") ||
                uri.startsWith(contextPath + "/register") // 如果首頁不需要登入也排除
        ) {
            chain.doFilter(req, res);
            return;
        }

        if (session == null || session.getAttribute("username") == null) {
            log.info("User not logged in, redirect to login page.");
            response.sendRedirect(contextPath + "/"); // 改成你登入頁面路徑
            return;
        }

        chain.doFilter(req, res);

    }
}
