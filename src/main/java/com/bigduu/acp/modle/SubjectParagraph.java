package com.bigduu.acp.modle;

import com.bigduu.acp.entity.subject.subSubject.SubjectType;
import lombok.Data;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

@Data
public class SubjectParagraph {
    private SubjectType type;
    private XWPFParagraph question;
    private XWPFParagraph options;
    
}
