package com.bigduu.acp.utils;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mugeng.du
 */
public class DocUtils {
    public static List<XWPFParagraph> getParagraphs(File file) throws IOException{
        FileInputStream fileInputStream = new FileInputStream(file);
        XWPFDocument document = new XWPFDocument(fileInputStream);
        return document.getParagraphs();
        
    }
    
    public static List<XWPFRun> getClearRun(List<XWPFRun> runs){
        ArrayList<XWPFRun> xwpfRuns = new ArrayList<>();
        for (XWPFRun run : runs) {
            String s = run.toString();
            if (!s.isEmpty() && !"保存".equals(s) && !" ".equals(s)){
                xwpfRuns.add(run);
            }
        }
        return xwpfRuns;
    }
}
