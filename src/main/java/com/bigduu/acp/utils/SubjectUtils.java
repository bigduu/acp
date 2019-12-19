package com.bigduu.acp.utils;

import com.bigduu.acp.entity.subject.AnswerType;
import com.bigduu.acp.entity.subject.Option;
import com.bigduu.acp.entity.subject.SubjectType;
import com.bigduu.acp.modle.SubjectParagraph;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;
import lombok.Data;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectUtils {
    private SubjectType subjectType;
    private List<XWPFParagraph> paragraphList;
    private List<SubjectParagraph> subjectParagraphs = new ArrayList<>();
    
    private static String NUMBER = "^\\d+.*";
    private static String SPLIT = " ";
    
    
    public List<SubjectParagraph> doSave(){
        buildSubjectParagraph();
        
        return this.subjectParagraphs;
    }
    
    
    private void buildSubjectParagraph() {
        for (int i = 0; i < this.paragraphList.size(); i++) {
            XWPFParagraph xwpfParagraph = this.paragraphList.get(i);
            String paragraphText = xwpfParagraph.getText();
            if (paragraphText.contains("单选题")){
                this.subjectType = SubjectType.SINGLE_CHOICE;
            } else if (paragraphText.contains("多选题")){
                this.subjectType = SubjectType.MULTIPLE_CHOICE;
            } else if (paragraphText.contains("判断题")){
                this.subjectType = SubjectType.JUDGE;
            } else {
                boolean matches = paragraphText.matches(NUMBER);
                if (!paragraphText.isEmpty() && matches){
                    XWPFParagraph xwpfParagraph1 = this.paragraphList.get(i + 1);
                    buildSubjectParagraph(xwpfParagraph,xwpfParagraph1);
                    i++;
                }
            }
        }
    }
    
    public void buildSubjectParagraph(XWPFParagraph question,XWPFParagraph options){
        SubjectParagraph subjectParagraph = new SubjectParagraph();
        subjectParagraph.setQuestion(question);
        subjectParagraph.setOptions(options);
        subjectParagraph.setType(this.subjectType);
        this.subjectParagraphs.add(subjectParagraph);
    }
    
    public void buildSubject(){
        for (SubjectParagraph subjectParagraph : this.subjectParagraphs) {
            List<Option> options = getOptions(subjectParagraph);
            
        }
    }
    
    
    
    public List<Option> getOptions(SubjectParagraph subjectParagraph){
        String text = subjectParagraph.getOptions().getText();
        String[] split = text.split(SPLIT);
        ArrayList<Option> options = new ArrayList<>();
        for (String s : split) {
            String trim = s.trim();
            Option option = new Option();
            if (trim.startsWith("是")){
                option.setIndex(AnswerType.TRUE);
            }else if (trim.startsWith("否")){
                option.setIndex(AnswerType.FALSE);
            }else if (trim.startsWith("A")){
                option.setIndex(AnswerType.A);
            }else if (trim.startsWith("B")){
                option.setIndex(AnswerType.B);
            }else if (trim.startsWith("C")){
                option.setIndex(AnswerType.C);
            } else if (trim.startsWith("D")){
                option.setIndex(AnswerType.D);
            }
            option.setDescription(trim);
            options.add(option);
        }
        return options;
    }
    
    
}
