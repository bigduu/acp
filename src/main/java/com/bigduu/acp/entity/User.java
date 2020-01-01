package com.bigduu.acp.entity;

import com.bigduu.acp.common.baseprocesshandler.entity.BaseEntity;
import com.bigduu.acp.entity.subject.subsubject.ErrorSubject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * @author mugeng.du
 */
@Data
@Document
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    @Id
    private String id;
    
    @Indexed(unique = true)
    private String username;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    
    private Integer lastMark;
    
    private Test lastTest;
    
    
    private List<Test> history;
    
    private List<ErrorSubject> errorHistory;
    
    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }
}
