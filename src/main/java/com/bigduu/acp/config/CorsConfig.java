package com.bigduu.acp.config;

import com.bigduu.acp.common.intercept.LogRequestIntercept;
import com.bigduu.acp.common.security.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author mugeng.du
 */
@Configuration
@EnableWebMvc
@AutoConfigureBefore(WebSecurityConfig.class)
public class CorsConfig implements WebMvcConfigurer {
    
    
    private final LogRequestIntercept logRequestIntercept;
    
    public CorsConfig(LogRequestIntercept logRequestIntercept) {
        this.logRequestIntercept = logRequestIntercept;
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*")
                .maxAge(3600);
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logRequestIntercept).addPathPatterns("/**");
    }
}
