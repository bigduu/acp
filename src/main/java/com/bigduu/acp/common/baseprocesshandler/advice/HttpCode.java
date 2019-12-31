package com.bigduu.acp.common.baseprocesshandler.advice;

import lombok.Data;

/**
 * @author mugeng.du
 */

public enum HttpCode {
    /**
     * 返回成功
     */
    SUCCESS(0),
    SUCCESS_MSG(1),
    /**
     * 带消息的错误
     */
    FAIL(20),
    FAIL_MSG(21);
    
    
    
    private Integer code;
    
    HttpCode(Integer code){
        this.code = code;
    }
    
    public Integer getCode(){
        return this.code;
    }
    
    
    
    
}
