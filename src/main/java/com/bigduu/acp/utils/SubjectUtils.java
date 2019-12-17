package com.bigduu.acp.utils;

import com.bigduu.acp.entiry.SingleChoiceSubject;
import com.bigduu.acp.entiry.Subject;
import lombok.Data;

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
    
    private static String NUMBER = "^\\d";
    private static String CHAR = "";
    
    
    private Boolean singleChoiceFlag;
    private Boolean MultipleChoiceFlag;
    private Boolean JudgeFlag;
    private Boolean finish;
    private SingleChoiceSubject singleChoiceSubject = null;
    private String tmp;
    
    
    /**
     * @param paragraph 传入段落
     * @return
     */
    public Subject judgeSubject(String paragraph){
        boolean startWithNumber = Pattern.matches(NUMBER, paragraph);
        if (startWithNumber){
            tmp = paragraph;
        } else {
            if (paragraph.contains(SINGLE_CHOICE)){
        
            }
        }
        return null;
    }
    
    private Subject getSingleChoice(String question,String options){
            singleChoiceSubject = new SingleChoiceSubject();
            singleChoiceSubject.setQuestion(question);
            String[] split = options.split(" ");
            List<String> strings = Arrays.asList(split);
            singleChoiceSubject.setAnswer(strings);
            return singleChoiceSubject;
    }
    
    
}
