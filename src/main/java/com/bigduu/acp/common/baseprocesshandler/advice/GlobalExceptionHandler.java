package com.bigduu.acp.common.baseprocesshandler.advice;

import com.bigduu.acp.common.baseprocesshandler.exception.AlertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author mugeng.du
 */
@Slf4j
@RestController
@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception e){
        log.error(e.getMessage(),e);
        return new ResponseEntity<>(GlobalResponseBody.fail(HttpCode.FAIL,e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage(),ex);
        if (ex instanceof AlertException){
            body =  GlobalResponseBody.fail(body,HttpCode.FAIL_MSG,ex.getMessage());
        }
        return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
}
