package com.bigduu.acp.entiry;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class MultipleChoiceSubject extends Subject{
    @Id
    private String id;
}
