package com.bigduu.acp.common.baseprocesshandler.exception;


import javax.annotation.sql.DataSourceDefinitions;

/**
 * @author mugeng.du
 */
public class AlertException extends Exception{
    private String message;
    public AlertException(String message){
        super();
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
