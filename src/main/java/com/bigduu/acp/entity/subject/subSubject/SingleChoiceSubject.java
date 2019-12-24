package com.bigduu.acp.entity.subject.subSubject;

import com.bigduu.acp.entity.subject.Subject;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class SingleChoiceSubject  extends Subject {
    @Id
    private String id;
    
    
    private transient String type = "single";
    
}
