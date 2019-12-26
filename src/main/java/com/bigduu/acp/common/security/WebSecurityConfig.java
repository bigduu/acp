package com.bigduu.acp.common.security;

import com.bigduu.acp.common.security.afterloginhandler.MyAuthenticationFailureHandler;
import com.bigduu.acp.common.security.afterloginhandler.MyAuthenticationSuccessHandler;
import com.bigduu.acp.common.security.entrypoint.MyLoginUrlAuthenticationEntryPoint;
import com.bigduu.acp.common.security.service.MyUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

/**
 * @author mugeng.du
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    private final MyUserDetailService myUserDetailService;
    
    private final MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    
    private final MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    
    public WebSecurityConfig(MyUserDetailService myUserDetailService, MyAuthenticationSuccessHandler myAuthenticationSuccessHandler, MyAuthenticationFailureHandler myAuthenticationFailureHandler) {
        this.myUserDetailService = myUserDetailService;
        this.myAuthenticationSuccessHandler = myAuthenticationSuccessHandler;
        this.myAuthenticationFailureHandler = myAuthenticationFailureHandler;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    /**
     * 设置验证器 其中设置了加密方式和UserDetailsService
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.myUserDetailService);
        return daoAuthenticationProvider;
    }
    
    /**
     * 用于屏蔽未认证情况下的URL重定向到login页面
     * 因为前后端分离，所以不需要
     */
    @Bean
    public AuthenticationEntryPoint myAuthenticationEntryPoint(){
        return new MyLoginUrlAuthenticationEntryPoint("/login");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/login","/logout","/user/logon").permitAll()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/login").successHandler(myAuthenticationSuccessHandler).failureHandler(myAuthenticationFailureHandler)
                .and()
                .logout().logoutUrl("/logout")
                .and()
                .exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint())
                .and()
                .cors(Customizer.withDefaults())
                .csrf()
                .disable();
    }
    
    @Bean
    protected CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }
    
    @Bean
    protected CorsFilter corsFilter(){
        return new CorsFilter(corsConfigurationSource());
    }
    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }
    
}
