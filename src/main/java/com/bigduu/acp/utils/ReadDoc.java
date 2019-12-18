package com.bigduu.acp.utils;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.*;
import java.util.List;

public class ReadDoc {
    public static List<XWPFParagraph> getParagraphs(File file) throws IOException{
        FileInputStream fileInputStream = new FileInputStream(file);
        XWPFDocument document = new XWPFDocument(fileInputStream);
        return document.getParagraphs();
    }
}
