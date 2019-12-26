package com.bigduu.acp.common.intercept;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * @author mugeng.du
 */
@Service
@Slf4j
public class LogRequestIntercept implements HandlerInterceptor {
    
    private static String START_TIME = "StartTime";
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("===============请求日志=================");
        request.setAttribute(START_TIME,System.currentTimeMillis());
        return true;
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long  start = Long.parseLong(request.getAttribute(START_TIME).toString());
        if (request.getUserPrincipal() != null){
            log.info("请求人： {}",request.getUserPrincipal().getName());
        }
        log.info("请求路径: {}",request.getRequestURI());
        log.info("请求方法: {}",request.getMethod());
        log.info("返回状态: {}",response.getStatus());
        log.info("请求耗时: {} ms",System.currentTimeMillis()-start);
        log.info("=======================================");
    }
}
