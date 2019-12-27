package com.bigduu.acp.common.intercept;

import com.bigduu.acp.entity.Operation;
import com.bigduu.acp.service.OperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * @author mugeng.du
 */
@Slf4j
@Component
public class LogRequestIntercept implements HandlerInterceptor {
    
    private final OperationService operationService;
    
    private static String START_TIME = "StartTime";
    
    public LogRequestIntercept(OperationService operationService) {
        this.operationService = operationService;
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("===============请求日志=================");
        request.setAttribute(START_TIME,System.currentTimeMillis());
        return true;
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long  start = Long.parseLong(request.getAttribute(START_TIME).toString());
        Operation operation = new Operation();
        if (request.getUserPrincipal() != null){
            String name = request.getUserPrincipal().getName();
            operation.setUser(name);
            log.info("请求人： {}",name);
        }
        operation.setPath(request.getRequestURI());
        operation.setMethod(request.getMethod());
        operation.setState(response.getStatus());
        log.info("请求路径: {}",request.getRequestURI());
        log.info("请求方法: {}",request.getMethod());
        log.info("返回状态: {}",response.getStatus());
        long l = System.currentTimeMillis() - start;
        operation.setTime(l);
        operationService.addOne(operation);
        log.info("请求耗时: {} ms",l);
        log.info("=======================================");
    
    }
}
