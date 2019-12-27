package com.bigduu.acp.entity.subject;


import com.bigduu.acp.entity.subject.subsubject.ErrorSubject;
import com.bigduu.acp.entity.subject.subsubject.Option;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author mugeng.du
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSubTypes({
        @JsonSubTypes.Type(value = ErrorSubject.class,name = "error")
})
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
    private List<Option> options;
    
    /**
     * 问题答案
     */
    private List<Option> answer;
    
    /**
     * 用户答案
     */
    private List<Option> solution;
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private transient Boolean did;
    
    /**
     * @return 判断该题是否正确
     */
    public Boolean isRight(){
        List<Option> answer = this.getAnswer();
        List<Option> solution = this.getSolution();
        return answer.equals(solution);
    }
    
    
}
