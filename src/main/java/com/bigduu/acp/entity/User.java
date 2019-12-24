package com.bigduu.acp.entity;

import com.bigduu.acp.common.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class User extends BaseEntity {
    @Id
    private String id;
    
    private String name;
    
    private String password;
    
    private Integer lastMark;
    
    private Test lastTest;
    
    private List<String> history;
    
    private List<String> errorHistory;
}
