package com.bigduu.acp;

import com.bigduu.acp.utils.ReadDoc;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.jupiter.api.Test;
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
        List<XWPFParagraph> paragraphs = ReadDoc.getParagraphs(file);
    }
    
}
