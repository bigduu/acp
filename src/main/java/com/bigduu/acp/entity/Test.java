package com.bigduu.acp.entity;

import com.bigduu.acp.entity.subject.ErrorSubject;
import com.bigduu.acp.entity.subject.JudgeSubject;
import com.bigduu.acp.entity.subject.MultipleChoiceSubject;
import com.bigduu.acp.entity.subject.SingleChoiceSubject;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Document
@Data
@Builder
public class Test {
    @Id
    private String id;
    
    private String userId;
    
    private List<SingleChoiceSubject> singleChoiceSubjects;
    
    private List<MultipleChoiceSubject> multipleChoiceSubjects;
    
    private List<JudgeSubject> judgeSubjects;
    
    private Long time;
    
    private Integer mark;
    
    private List<ErrorSubject> errorSubject;
}
