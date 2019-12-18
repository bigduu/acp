package com.bigduu.acp;

import com.bigduu.acp.utils.DocUtils;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.jupiter.api.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHighlightColor;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class AcpApplicationTests {
    
    @Test
    void contextLoads() throws IOException, OpenXML4JException {
        String path = "/Users/bigduu/Documents/git/acp/src/main/resources/ACP弹性云计算600道题带答案.docx";
        File file = new File(path);
        List<XWPFParagraph> paragraphs = DocUtils.getParagraphs(file);
        for (XWPFParagraph paragraph : paragraphs) {
            List<XWPFRun> runs = paragraph.getRuns();
            List<XWPFRun> clearRun = DocUtils.getClearRun(runs);
            
        }
    }
    
}
