package com.bigduu.acp.entity.subject.subsubject;

import com.bigduu.acp.entity.subject.Subject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author mugeng.du
 */
@Data
@Document
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JudgeSubject extends Subject {
    @Id
    private String id;
    
    private String type = "judge";
}
