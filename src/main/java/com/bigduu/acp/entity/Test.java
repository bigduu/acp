package com.bigduu.acp.entity;

import com.bigduu.acp.common.entity.BaseEntity;
import com.bigduu.acp.entity.subject.subSubject.ErrorSubject;
import com.bigduu.acp.entity.subject.subSubject.JudgeSubject;
import com.bigduu.acp.entity.subject.subSubject.MultipleChoiceSubject;
import com.bigduu.acp.entity.subject.subSubject.SingleChoiceSubject;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@Builder
public class Test extends BaseEntity {
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
