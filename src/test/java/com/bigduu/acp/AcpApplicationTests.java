package com.bigduu.acp;

import com.bigduu.acp.entity.subject.*;
import com.bigduu.acp.repository.JudgeSubjectRepository;
import com.bigduu.acp.repository.MultipleChoiceSubjectRepository;
import com.bigduu.acp.repository.SingleChoiceSubjectRepository;
import com.bigduu.acp.utils.DocUtils;
import com.bigduu.acp.utils.SubjectUtils;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.jupiter.api.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHighlightColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class AcpApplicationTests {
    
    @Autowired
    private SingleChoiceSubjectRepository singleChoiceSubjectRepository;
    
    @Autowired
    private MultipleChoiceSubjectRepository multipleChoiceSubjectRepository;
    
    @Autowired
    private JudgeSubjectRepository judgeSubjectRepository;
    
    @Test
    void contextLoads() throws IOException, OpenXML4JException {
        String path = "C:\\Users\\mugeng.du\\IdeaProjects\\acp\\src\\main\\resources\\ACP弹性云计算600道题带答案.docx";
        File file = new File(path);
        List<XWPFParagraph> paragraphs = DocUtils.getParagraphs(file);
        SubjectUtils subjectUtils = new SubjectUtils();
        subjectUtils.setParagraphList(paragraphs);
        List<Subject> subjectList = subjectUtils.getSubjectList();
        for (Subject subject : subjectList) {
            if (subject instanceof SingleChoiceSubject){
                SingleChoiceSubject subject1 = (SingleChoiceSubject) subject;
                singleChoiceSubjectRepository.save(subject1);
            }
            if (subject instanceof MultipleChoiceSubject){
                MultipleChoiceSubject subject1 = (MultipleChoiceSubject) subject;
                multipleChoiceSubjectRepository.save(subject1);
            }
            if (subject instanceof JudgeSubject){
                JudgeSubject subject1 = (JudgeSubject) subject;
                judgeSubjectRepository.save(subject1);
            }
        }
        
    }
    
    @Test
    void test(){
        SingleChoiceSubject singleChoiceSubject = new SingleChoiceSubject();
        singleChoiceSubject.setQuestion("1111");
        singleChoiceSubject.setMark(1);
        Option option = new Option();
        option.setIndex(AnswerType.A);
        option.setDescription("test");
        List<Option> options = Arrays.asList(option);
        singleChoiceSubject.setOptions(options);
        singleChoiceSubjectRepository.save(singleChoiceSubject);
    }
    
}
