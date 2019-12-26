package com.bigduu.acp.entity.subject.subsubject;

import com.bigduu.acp.entity.subject.Subject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author mugeng.du
 */
@Data
@Document
@EqualsAndHashCode(callSuper = true)
public class MultipleChoiceSubject extends Subject {
    @Id
    private String id;
    
    private String type = "multiple";
    
}
