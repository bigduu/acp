package com.bigduu.acp;

import com.bigduu.acp.entity.subject.*;
import com.bigduu.acp.entity.subject.subsubject.*;
import com.bigduu.acp.repository.subject.JudgeSubjectRepository;
import com.bigduu.acp.repository.subject.MultipleChoiceSubjectRepository;
import com.bigduu.acp.repository.subject.SingleChoiceSubjectRepository;
import com.bigduu.acp.service.SubjectService;
import com.bigduu.acp.utils.DocUtils;
import com.bigduu.acp.utils.SubjectUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
class AcpApplicationTests {
    
    @Autowired
    private SingleChoiceSubjectRepository singleChoiceSubjectRepository;
    
    @Autowired
    private MultipleChoiceSubjectRepository multipleChoiceSubjectRepository;
    
    @Autowired
    private JudgeSubjectRepository judgeSubjectRepository;
    
    @Autowired
    private SubjectService subjectService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void contextLoads() throws Exception {
        String path = "src/main/resources/static/doc/ACP弹性云计算600道题带答案.docx";
//        String path = "C:\\Users\\mugeng.du\\IdeaProjects\\acp\\src\\main\\resources\\static\\doc\\ACP弹性云计算600道题带答案.docx";
        File file = new File(path);
        List<XWPFParagraph> paragraphs = DocUtils.getParagraphs(file);
        SubjectUtils subjectUtils = new SubjectUtils();
        subjectUtils.setParagraphList(paragraphs);
        List<Subject> subjectList = subjectUtils.getSubjectList();
        List<SingleChoiceSubject> singles = new ArrayList<>();
        List<MultipleChoiceSubject> multiples = new ArrayList<>();
        List<JudgeSubject> judges = new ArrayList<>();
        for (Subject subject : subjectList) {
            if (subject.getAnswer().size() == 0) {
                log.error(subject.getQuestion());
                throw new Exception("判断题无答案");
            }
            
            if (subject instanceof SingleChoiceSubject) {
                SingleChoiceSubject single = (SingleChoiceSubject) subject;
                singles.add(single);
            }
            
            if (subject instanceof MultipleChoiceSubject) {
                MultipleChoiceSubject multiple = (MultipleChoiceSubject) subject;
                String question = multiple.getQuestion();
                int indexOf = question.indexOf("正确答案的数量");
                int indexOf1 = question.indexOf("正确答案数量");
                if (indexOf == -1 && indexOf1 == -1) {
                    System.out.println(question);
                } else {
                    String trim = question.trim();
                    String substring = trim.substring(trim.length() - 3, trim.length() - 2);
                    int size = subject.getAnswer().size();
                    if (!substring.equals(Integer.toString(size))) {
                        log.error(subject.getQuestion());
                        throw new Exception("有答案数目不同");
                    }
                    multiples.add(multiple);
                }
                
            }
            if (subject instanceof JudgeSubject) {
                JudgeSubject judge = (JudgeSubject) subject;
                judges.add(judge);
            }
        }
        singleChoiceSubjectRepository.saveAll(singles);
        multipleChoiceSubjectRepository.saveAll(multiples);
        judgeSubjectRepository.saveAll(judges);
        
    }
    
    @Test
    void test() {
        List<JudgeSubject> all = judgeSubjectRepository.findAll();
        for (JudgeSubject singleChoiceSubject : all) {
            List<Option> answer = singleChoiceSubject.getAnswer();
            if (answer == null || answer.isEmpty()) {
                System.out.println("==============================");
                System.out.println(singleChoiceSubject.getQuestion());
                System.out.println("==============================");
            }
        }
    }
    
    @Test
    void test1() {
        String ex = "用户通过网络使用软件,无需购买软硬件、建设机房等,而改用向提供商租用基于Web的软件,来管理企业经营活动,且无需对软件进行维护,服";
        Optional<SingleChoiceSubject> byQuestionLike = singleChoiceSubjectRepository.findByQuestionLike(ex);
        if (byQuestionLike.isPresent()) {
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
    
    @Test
    void jackSonTest() throws JsonProcessingException {
        String string = "{\"@type\":\"SingleChoiceSubject\",\"mark\":1,\"question\":\"阿里云对象存储OSS是阿里云对外提供的海量、安全、低成本、高可靠的云存储服务。用OSS管理的文件可以很方便地对外提供分享," +
                "分享前点击文件后面的“获取地址”文字链接即可得到当前文件的地址,这个分享使用的是________应用层（七层）协议。 保存\",\"options\":[{\"id\":null,\"index\":\"A\",\"description\":\"A、HTTP\"},{\"id\":null,\"index\":\"B\",\"description\":\"B、TCP\"},{\"id\":null,\"index\":\"C\",\"description\":\"C、FTP\"},{\"id\":null,\"index\":\"D\",\"description\":\"D、SMTP\"}],\"answer\":[{\"id\":null,\"index\":\"A\",\"description\":null}],\"solution\":[{\"index\":\"D\"}],\"did\":true,\"id\":\"5dfb89faea6e9b6a04bb3681\",\"type\":\"single\",\"right\":false,\"tmp\":\"D\",\"errorLog\":true}";
        
        ErrorSubject errorSubject = objectMapper.readValue(string, ErrorSubject.class);
        System.out.println(errorSubject);
        
    }
    
}
