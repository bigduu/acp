package com.bigduu.acp.utils;

import com.bigduu.acp.entiry.SingleChoiceSubject;
import com.bigduu.acp.entiry.Subject;
import lombok.Data;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author bigduu
 * @title: SubjectUtils
 * @projectName acp
 * @description: TODO
 * @date 2019/12/1722:12
 */
@Data
public class SubjectUtils {
    private static String SINGLE_CHOICE = "单选题";
    private static String MULTIPLE_CHOICE = "多选题";
    private static String JUDGE = "判断题";
    
    private static String NUMBER = "^\\d+.*";
    private static String SPLIT = " ";
    
    private Subject.SubjectBuilder builder;
    private Boolean finish;
    private String tmpQuestion;
    
    
    
    public void judgeSubject(XWPFParagraph paragraph){
        if (isQuestion(paragraph)){
            this.tmpQuestion = paragraph.getText();
        }
        String text = paragraph.getText();
        setBuilder(text, SINGLE_CHOICE);
        setBuilder(text, MULTIPLE_CHOICE);
        setBuilder(text, JUDGE);
        buildSubject(text);
    }
    
    private void buildSubject(String text) {
            this.builder.question(this.tmpQuestion);
            List<String> options = getOptions(text);
            this.builder.options(options);
    }
    
    private void setBuilder(String text, String singleChoice) {
        if (text.contains(singleChoice)) {
            this.builder = SingleChoiceSubject.builder();
        }
    }
    
    private List<String> getOptions(String text){
        String[] s = text.split(SPLIT);
        return Arrays.asList(s);
    
    }
    
    
    /**
     * 判断题号 来判断是否是问题
     * @param paragraph poi 框架的段落
     * @return 是问题 返回true 不是 就返回false
     */
    private Boolean isQuestion(XWPFParagraph paragraph){
        return Pattern.matches(NUMBER, paragraph.getText());
    }
    
}
