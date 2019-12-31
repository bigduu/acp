package com.bigduu.acp.common.baseprocesshandler.advice;

import javafx.util.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author mugeng.du
 */
@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
    static Builder<String> globalResponseBodyBuilder = () -> "com.bigduu.acp.common.baseprocesshandler.controller.advice.GlobalResponseBody";
    static Builder<String> responseEntityBuilder = () -> "org.springframework.http.ResponseEntity";
    
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        final String name = returnType.getParameterType().getName();
        return !globalResponseBodyBuilder.build().equals(name) && !responseEntityBuilder.build().equals(name);
    }
    
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        final String name = returnType.getParameterType().getName();
        Builder<String> stringBuilder = () -> "void";
        if (stringBuilder.build().equals(name)){
            return GlobalResponseBody.success();
        }
        //如果返回体里面不是json格式数据 则直接返回body内容
        if (!selectedContentType.includes(MediaType.APPLICATION_JSON)){
            return body;
        }
        
        return GlobalResponseBody.success(body);
    }
}
