package com.simpletalk.config;

import lombok.extern.slf4j.Slf4j;
import org.directwebremoting.servlet.DwrServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DwrConfig {


    @Bean
    public ServletRegistrationBean<?> dwrServlet() {
        try {
            DwrServlet servlet = new DwrServlet();
            ServletRegistrationBean<DwrServlet> reg = new ServletRegistrationBean<>(servlet, "/dwr/*");
            reg.setName("dwrServlet");
//            reg.addInitParameter("debug", "true");
            reg.addInitParameter("activeReverseAjaxEnabled", "true");
            reg.addInitParameter("initApplicationScopeCreatorsAtStartup", "true");
            log.info("[SimpleTalk] DWR Spring Servlet (DwrSpringServlet) configured with debug mode.");
            reg.addInitParameter("classes", "com.simpletalk.servlet.JavaChat");
            reg.addInitParameter("class", "org.directwebremoting.spring.SpringConfigurator"); // << 關鍵


            return reg;
        } catch (Exception e) {
            log.error("[SimpleTalk] DWR Servlet 無法載入", e);
            return null;
        }
    }
}
