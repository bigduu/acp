package com.bigduu.acp.entity;

import com.bigduu.acp.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Data
@Document
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    @Id
    private String id;
    
    private String username;
    
    @JsonIgnore
    private String password;
    
    private Integer lastMark;
    
    private Test lastTest;
    
    private List<String> history;
    
    private List<String> errorHistory;
    
    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }
}
