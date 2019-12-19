package com.bigduu.acp.entity.subject;

import com.bigduu.acp.entity.subject.Subject;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class MultipleChoiceSubject extends Subject {
    @Id
    private String id;
}
