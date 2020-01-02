package com.bigduu.acp.entity.subject;


import com.bigduu.acp.entity.subject.subsubject.Comment;
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
    
    private String type;
    
    /**
     * 用户答案
     */
    private List<Option> solution;
    
    private List<Comment> comments;
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private transient Boolean did;
    
    
}
