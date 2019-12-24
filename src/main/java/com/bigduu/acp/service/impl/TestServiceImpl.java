package com.bigduu.acp.service.impl;

import com.bigduu.acp.common.service.impl.BaseServiceImpl;
import com.bigduu.acp.entity.Test;
import com.bigduu.acp.entity.User;
import com.bigduu.acp.entity.subject.*;
import com.bigduu.acp.entity.subject.subSubject.JudgeSubject;
import com.bigduu.acp.entity.subject.subSubject.MultipleChoiceSubject;
import com.bigduu.acp.entity.subject.subSubject.SingleChoiceSubject;
import com.bigduu.acp.entity.subject.subSubject.SubjectType;
import com.bigduu.acp.repository.TestRepository;
import com.bigduu.acp.service.SubjectService;
import com.bigduu.acp.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bigduu
 * @title: TestServiceImpl
 * @projectName acp
 * @description: TODO
 * @date 2019/12/2119:40
 */
@Service
@Slf4j
public class TestServiceImpl extends BaseServiceImpl<Test> implements TestService {
    
    private final SubjectService subjectService;
    private final TestRepository testRepository;
    
    private final static Integer singleChoiceSubjectNumber = 50;
    private final static Integer multipleChoiceSubjectNumber = 30;
    private final static Integer judgeSubjectNumber = 20;
    
    public TestServiceImpl(SubjectService subjectService, TestRepository testRepository) {
        super();
        super.repository = testRepository;
        this.subjectService = subjectService;
        this.testRepository = testRepository;
    }
    
    
    @Override
    public void delete(Test test) {
        testRepository.delete(test);
    }
    
    @Override
    public Test update(Test test) {
        return testRepository.save(test);
    }
    
    @Override
    public List<Test> getAllByUser(User user) throws Exception {
        String id = user.getId();
        if (id == null){
            throw new Exception("根据用户ID获取TestList 用户ID为null");
        }
        return testRepository.findAllByUserId(id);
    }
    
    
    @Override
    public Test getAllSubjectTest() {
        List<? extends Subject> allSingleTypeOfSubject = null;
        List<? extends Subject> allMultipleTypeOfSubject = null;
        List<? extends Subject> allJudgeTypeOfSubject = null;
        try{
            allSingleTypeOfSubject = subjectService.getAll(SubjectType.SINGLE_CHOICE);
            allMultipleTypeOfSubject = subjectService.getAll(SubjectType.SINGLE_CHOICE);
            allJudgeTypeOfSubject = subjectService.getAll(SubjectType.SINGLE_CHOICE);
            
        }catch (Exception e){
            log.error("生成全部题目时发生错误");
        }
    
        return Test.builder()
                .singleChoiceSubjects((List<SingleChoiceSubject>) allSingleTypeOfSubject)
                .multipleChoiceSubjects((List<MultipleChoiceSubject>) allMultipleTypeOfSubject)
                .judgeSubjects((List<JudgeSubject>) allJudgeTypeOfSubject)
                .build();
    }
    
    @Override
    public Test getDefaultTest() {
        return generateTest(singleChoiceSubjectNumber,multipleChoiceSubjectNumber,judgeSubjectNumber);
    }
    
    
    @Override
    public Test getSingleChoiceOnlyTest() {
        return generateTest(100,0,0);
    }
    
    @Override
    public Test getMultipleChoiceOnlyTest() {
        return generateTest(0,100,0);
    }
    
    @Override
    public Test getJudgeChoiceOnlyTest() {
        return generateTest(0,0,100);
    }
    
    private Test generateTest(Integer single,Integer multiple,Integer judge){
        List<? extends Subject> randomSingleTypeOfSubject = null;
        List<? extends Subject> randomMultipleTypeOfSubject = null;
        List<? extends Subject> randomJudgeTypeOfSubject = null;
        try {
            randomSingleTypeOfSubject = subjectService.getRandomTypeOfSubject(SubjectType.SINGLE_CHOICE, single);
            randomMultipleTypeOfSubject = subjectService.getRandomTypeOfSubject(SubjectType.MULTIPLE_CHOICE,multiple);
            randomJudgeTypeOfSubject = subjectService.getRandomTypeOfSubject(SubjectType.JUDGE,judge);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Test.builder()
                .singleChoiceSubjects((List<SingleChoiceSubject>) randomSingleTypeOfSubject)
                .multipleChoiceSubjects((List<MultipleChoiceSubject>) randomMultipleTypeOfSubject)
                .judgeSubjects((List<JudgeSubject>) randomJudgeTypeOfSubject)
                .build();
    }
}
