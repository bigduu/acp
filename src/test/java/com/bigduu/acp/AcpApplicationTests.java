package com.bigduu.acp;

import com.bigduu.acp.entity.subject.*;
import com.bigduu.acp.entity.subject.subsubject.*;
import com.bigduu.acp.repository.subject.JudgeSubjectRepository;
import com.bigduu.acp.repository.subject.MultipleChoiceSubjectRepository;
import com.bigduu.acp.repository.subject.SingleChoiceSubjectRepository;
import com.bigduu.acp.service.SubjectService;
import com.bigduu.acp.utils.DocUtils;
import com.bigduu.acp.utils.SubjectUtils;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class AcpApplicationTests {
    
    @Autowired
    private SingleChoiceSubjectRepository singleChoiceSubjectRepository;
    
    @Autowired
    private MultipleChoiceSubjectRepository multipleChoiceSubjectRepository;
    
    @Autowired
    private JudgeSubjectRepository judgeSubjectRepository;
    
    @Autowired
    private SubjectService subjectService;
    
    @Test
    void contextLoads() throws IOException, OpenXML4JException {
        String path = "src/main/resources/ACP弹性云计算600道题带答案.docx";
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
        List<JudgeSubject> all = judgeSubjectRepository.findAll();
        for (JudgeSubject singleChoiceSubject : all) {
            List<Option> answer = singleChoiceSubject.getAnswer();
            if (answer == null || answer.isEmpty()){
                System.out.println("==============================");
                System.out.println(singleChoiceSubject.getQuestion());
                System.out.println("==============================");
            }
        }
    }
    
    @Test
    void test1(){
        String ex = "用户通过网络使用软件,无需购买软硬件、建设机房等,而改用向提供商租用基于Web的软件,来管理企业经营活动,且无需对软件进行维护,服";
        Optional<SingleChoiceSubject> byQuestionLike = singleChoiceSubjectRepository.findByQuestionLike(ex);
        if (byQuestionLike.isPresent()){
            SingleChoiceSubject singleChoiceSubject = byQuestionLike.get();
            Option option = new Option();
            option.setIndex(AnswerType.A);
            List<Option> options = Arrays.asList(option);
            singleChoiceSubject.setAnswer(options);
            singleChoiceSubjectRepository.save(singleChoiceSubject);
        }
    }
    
    private String getStringTime(double gamingTime) {
        double tmp = gamingTime / (60 * 60);
        int hour = (int) tmp;
        int min = (int) (gamingTime / 60 % 60);
        return String.format("%02d:%02d", hour, min);
    }
    
}
