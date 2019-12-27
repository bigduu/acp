package com.bigduu.acp.entity.subject.subsubject;

import com.bigduu.acp.entity.subject.Subject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author mugeng.du
 */
@Data
@Document
@JsonTypeName(value = "error")
@EqualsAndHashCode(callSuper = true)
public class ErrorSubject extends Subject {
    @Id
    private String id;
    private String type = "single";
    private String username;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean right;
}
