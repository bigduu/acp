package com.bigduu.acp.common.annotation.impl;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author mugeng.du
 */
@Slf4j
@Aspect
@Component
public class TimerImpl {
    @Pointcut("@annotation(com.bigduu.acp.common.annotation.Timer)")
    public void pointcut(){}
    
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        long start = System.currentTimeMillis();
        String name = point.getSignature().getName();
        log.info("开始执行{}方法",name);
        Object proceed = point.proceed();
        log.info("执行结束{}方法,耗时:{}",name,System.currentTimeMillis()-start);
        return proceed;
    }
}
