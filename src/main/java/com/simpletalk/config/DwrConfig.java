package com.simpletalk.config;

import org.directwebremoting.servlet.DwrServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DwrConfig {
    @Bean
    public ServletRegistrationBean<DwrServlet> dwrServlet() {
        ServletRegistrationBean<DwrServlet> reg = new ServletRegistrationBean<>(new DwrServlet(), "/dwr/*");
        reg.setLoadOnStartup(1);
        reg.setName("dwr-invoker");
        reg.addInitParameter("debug", "true");
        return reg;
    }
}
