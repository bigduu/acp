package com.bigduu.acp.entity.subject.subSubject;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Data
@Document
public class Option {
    @Id
    private String id;
    
    private AnswerType index;
    
    private String description;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return index == option.index;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
