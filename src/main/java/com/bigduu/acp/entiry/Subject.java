package com.bigduu.acp.entiry;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public abstract class Subject<T> {
    /**
     * 分数
     */
    private Integer mark;
    
    /**
     * 题干
     */
    private String question;
    
    /**
     * 选项
     */
    private List<String> options;
    
    /**
     * 问题答案
     */
    private List<String> answer;
    
    /**
     * 用户答案
     */
    private List<String> solution;
    
    /**
     * @return 判断该题是否正确
     */
    public Boolean isRight(){
        List<String> answer = this.getAnswer();
        List<String> solution = this.getSolution();
        return answer.equals(solution);
    }
    
    public T build(String question,List<String> options){
        this.question = question;
        this.options = options;
        return (T) this;
    }
    
}
