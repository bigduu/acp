package com.bigduu.acp.common.baseprocesshandler.advice;

import lombok.*;

/**
 * @author mugeng.du
 */
@Data
@AllArgsConstructor
public class GlobalResponseBody<T> {
    @NonNull
    protected boolean success;
    
    private T data;
    private HttpCode httpCode;
    private String errorMsg;
    
    public static <T> GlobalResponseBody<T> success(){
        return new GlobalResponseBody<>(true,null,HttpCode.SUCCESS,null);
    }
    
    public static <T> GlobalResponseBody<T> success(T data){
        return new GlobalResponseBody<>(true,data,HttpCode.SUCCESS,null);
    }
    
    public static <T> GlobalResponseBody<T> fail(HttpCode httpCode,String errorMsg){
        return new GlobalResponseBody<T>(false,null,httpCode,errorMsg);
    }
    
    public static <T> GlobalResponseBody<T> fail(T data,HttpCode httpCode,String errorMsg){
        return new GlobalResponseBody<T>(false,data,httpCode,errorMsg);
    }
}
