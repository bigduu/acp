package com.bigduu.acp.entity;

import com.bigduu.acp.common.baseprocesshandler.entity.BaseEntity;
import com.bigduu.acp.entity.subject.Subject;
import com.bigduu.acp.entity.subject.subsubject.ErrorSubject;
import com.bigduu.acp.entity.subject.subsubject.JudgeSubject;
import com.bigduu.acp.entity.subject.subsubject.MultipleChoiceSubject;
import com.bigduu.acp.entity.subject.subsubject.SingleChoiceSubject;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author mugeng.du
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
@EqualsAndHashCode(callSuper = true)
public class Test extends BaseEntity {
    @Id
    private String id;
    
    private String username;
    
    private List<? extends Subject> singleChoiceSubjects;
    
    private List<? extends Subject> multipleChoiceSubjects;
    
    private List<? extends Subject> judgeSubjects;
    
    private Long time;
    
    private Integer mark;
    
    private List<ErrorSubject> errorSubject;
    
}
