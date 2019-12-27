package com.bigduu.acp.entity;

import com.bigduu.acp.common.entity.BaseEntity;
import com.bigduu.acp.entity.subject.subsubject.ErrorSubject;
import com.bigduu.acp.entity.subject.subsubject.JudgeSubject;
import com.bigduu.acp.entity.subject.subsubject.MultipleChoiceSubject;
import com.bigduu.acp.entity.subject.subsubject.SingleChoiceSubject;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author mugeng.du
 */
@Data
@Builder
@Document
@EqualsAndHashCode(callSuper = true)
public class Test extends BaseEntity {
    @Id
    private String id;
    
    private String userId;
    
    @DBRef
    private List<SingleChoiceSubject> singleChoiceSubjects;
    
    @DBRef
    private List<MultipleChoiceSubject> multipleChoiceSubjects;
    
    @DBRef
    private List<JudgeSubject> judgeSubjects;
    
    private Long time;
    
    private Integer mark;
    
    private List<ErrorSubject> errorSubject;
}
