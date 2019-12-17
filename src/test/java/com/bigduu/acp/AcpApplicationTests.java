package com.bigduu.acp;

import com.bigduu.acp.utils.ReadDoc;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

@SpringBootTest
class AcpApplicationTests {
    
    @Test
    void contextLoads() throws IOException {
        String path = "/Users/bigduu/Documents/git/acp/src/main/resources/ACP弹性云计算600道题带答案.docx";
        File file = new File(path);
        new ReadDoc().testReadByDoc(file);
    }
    
}
