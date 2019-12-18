package com.bigduu.acp.entiry;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject {
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
    
    
}
