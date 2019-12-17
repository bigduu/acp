package com.bigduu.acp.utils;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ReadDoc {
    public void testReadByDoc(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        HWPFDocument document = new HWPFDocument(fileInputStream);
        Range range = document.getRange();
        int num = range.numParagraphs();
        for (int i = 0 ; i < num ; i ++){
            System.out.println(range.getParagraph(i).text());
        }
    }
}
