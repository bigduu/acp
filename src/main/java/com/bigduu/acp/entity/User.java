package com.bigduu.acp.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class User {
    @Id
    private String id;
    
    private Integer lastMark;
    
    private Test lastTest;
    
    private List<String> history;
    
    private List<String> errorHistory;
}
