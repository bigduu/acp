package com.bigduu.acp.utils;

import com.bigduu.acp.entity.subject.*;
import com.bigduu.acp.entity.subject.subsubject.*;
import com.bigduu.acp.modle.SubjectParagraph;
import lombok.Data;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHighlightColor;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectUtils {
    private SubjectType subjectType;
    private List<XWPFParagraph> paragraphList;
    private List<SubjectParagraph> subjectParagraphs = new ArrayList<>();
    
    private static String NUMBER = "^\\d+.*";
    private static String SPLIT = " ";
    
    
    public List<Subject> getSubjectList(){
        buildSubjectParagraph();
        return buildSubject();
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
    
    private void buildSubjectParagraph(XWPFParagraph question,XWPFParagraph options){
        SubjectParagraph subjectParagraph = new SubjectParagraph();
        subjectParagraph.setQuestion(question);
        subjectParagraph.setOptions(options);
        subjectParagraph.setType(this.subjectType);
        this.subjectParagraphs.add(subjectParagraph);
    }
    
    private List<Subject> buildSubject(){
        List<Subject> subjects = new ArrayList<>();
        for (SubjectParagraph subjectParagraph : this.subjectParagraphs) {
            List<Option> options = getOptions(subjectParagraph);
            List<Option> answers = getAnswers(subjectParagraph);
            SubjectType type = subjectParagraph.getType();
            getSubjectList(subjects, subjectParagraph, options, answers, type);
        }
        return subjects;
    }
    
    private void getSubjectList(List<Subject> subjects, SubjectParagraph subjectParagraph, List<Option> options, List<Option> answers,
                                SubjectType type) {
        String text = subjectParagraph.getQuestion().getText();
        String substring = text.substring(2);
        substring = removeChars(substring, "、");
        substring = removeSpace(substring);
        String trim = substring.trim();
    
        switch (type){
            case SINGLE_CHOICE:
                SingleChoiceSubject singleChoiceSubject = new SingleChoiceSubject();
                singleChoiceSubject.setMark(1);
                singleChoiceSubject.setOptions(options);
                singleChoiceSubject.setQuestion(trim);
                singleChoiceSubject.setAnswer(answers);
                subjects.add(singleChoiceSubject);
                break;
            case MULTIPLE_CHOICE:
                MultipleChoiceSubject multipleChoiceSubject = new MultipleChoiceSubject();
                multipleChoiceSubject.setMark(1);
                multipleChoiceSubject.setOptions(options);
                multipleChoiceSubject.setQuestion(trim);
                multipleChoiceSubject.setAnswer(answers);
                subjects.add(multipleChoiceSubject);
                break;
            case JUDGE:
                JudgeSubject judgeSubject = new JudgeSubject();
                judgeSubject.setMark(1);
                judgeSubject.setOptions(options);
                judgeSubject.setQuestion(trim);
                judgeSubject.setAnswer(answers);
                subjects.add(judgeSubject);
                break;
            default:
        }
    }
    
    
    private List<Option> getAnswers(SubjectParagraph subjectParagraph){
        List<XWPFRun> runs = subjectParagraph.getOptions().getRuns();
        
        List<XWPFRun> clearRun = DocUtils.getClearRun(runs);
        
        ArrayList<Option> answers = new ArrayList<>();
        
        for (XWPFRun run : clearRun) {
            STHighlightColor.Enum textHightlightColor = run.getTextHightlightColor();
            if (!textHightlightColor.toString().equals("none")){
                Option option = new Option();
                String string = run.toString();
                String removeSpace = removeSpace(string);
                String trim = removeSpace.trim();
                setOptionIndex(option, trim);
                if (option.getIndex() != null){
                    answers.add(option);
                }
            }
        }
        return answers;
    }
    
    private String removeChars(String origin,String target){
        String replace = origin;
        boolean contains = replace.contains(target);
        while (contains){
            replace = replace.replace(target, "");
            contains = replace.contains(target);
        }
        return replace;
    }
    
    private String removeSpace(String text){
        return removeChars(text,SPLIT);
    }
    
    private void setOptionIndex(Option option, String trim) {
        if (trim.isEmpty()){
            return;
        }
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
        } else if (trim.startsWith("E")){
            option.setIndex(AnswerType.E);
        }else if (trim.startsWith("F")){
            option.setIndex(AnswerType.F);
        }else if (trim.startsWith("G")){
            option.setIndex(AnswerType.G);
        }
    }
    
    private List<Option> getOptions(SubjectParagraph subjectParagraph){
        String text = subjectParagraph.getOptions().getText();
        String[] split = text.split(SPLIT);
        ArrayList<Option> options = new ArrayList<>();
        for (String s : split) {
            String trim = s.trim();
            Option option = new Option();
            setOptionIndex(option, trim);
            option.setDescription(trim);
            if (option.getIndex() != null){
                options.add(option);
            }
        }
        return options;
    }
    
    
}
