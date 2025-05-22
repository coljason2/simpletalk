package com.simpletalk.config;

import org.directwebremoting.servlet.DwrServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<DwrServlet> dwrServletRegistration() {
        ServletRegistrationBean<DwrServlet> registrationBean =
                new ServletRegistrationBean<>(new DwrServlet(), "/dwr/*");
        registrationBean.setName("dwr-invoker");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }
}
