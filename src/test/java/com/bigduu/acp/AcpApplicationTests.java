package com.bigduu.acp;

import com.bigduu.acp.utils.ReadDoc;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class AcpApplicationTests {
    
    @Test
    void contextLoads() throws IOException {
        String path = "C:\\Users\\mugeng.du\\Downloads\\ACP弹性云计算600道题带答案.docx";
        new ReadDoc().testReadByDoc(path);
    }
    
}
