package com.bigduu.acp.utils;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.*;
import java.util.List;

public class ReadDoc {
    public void testReadByDoc(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
    
        XWPFDocument document = new XWPFDocument(fileInputStream);
        List<XWPFParagraph> paragraphs = document.getParagraphs();
        for (XWPFParagraph paragraph : paragraphs) {
            String text = paragraph.getText();
            System.out.println("===================================");
            System.out.println(text);
            System.out.println("===================================");
        }
        
    }
}
