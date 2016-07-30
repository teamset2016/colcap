package com.teamset.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.teamset.colcap.domain.dao.DomainConfig;

@Configuration
@ComponentScan({"com.teamset.controller", "com.teamset.restc"})
@PropertySource(value = {"classpath:web.application.config.properties"})
@EnableWebMvc
@Import({HibernateConfig.class,DomainConfig.class, CoreConfig.class})
public class WebApplicationConfig extends WebMvcConfigurerAdapter {

    private final static Logger logger = LoggerFactory.getLogger(WebApplicationConfig.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/assets/resources/");
        registry.addResourceHandler("/scripts/**").addResourceLocations("/assets/scripts/");
        registry.addResourceHandler("/styles/**").addResourceLocations("/assets/styles/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/assets/fonts/");
        registry.addResourceHandler("/images/**").addResourceLocations("/assets/images/");
        registry.addResourceHandler("/views/**").addResourceLocations("/assets/views/");
        registry.addResourceHandler("/app/**").addResourceLocations("/app/");
        registry.addResourceHandler("/template/**").addResourceLocations("/template/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");
        return bean;
    }
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
 

}
