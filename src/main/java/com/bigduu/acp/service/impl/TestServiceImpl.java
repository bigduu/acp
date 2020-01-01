package com.bigduu.acp.service.impl;

import com.bigduu.acp.common.baseprocesshandler.service.impl.BaseServiceImpl;
import com.bigduu.acp.entity.Test;
import com.bigduu.acp.entity.User;
import com.bigduu.acp.entity.subject.*;
import com.bigduu.acp.entity.subject.subsubject.*;
import com.bigduu.acp.repository.TestRepository;
import com.bigduu.acp.service.ErrorSubjectService;
import com.bigduu.acp.service.SubjectService;
import com.bigduu.acp.service.TestService;
import com.bigduu.acp.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private final ErrorSubjectService errorSubjectService;
    
    private final static Integer SINGLE_CHOICE_SUBJECT_NUMBER = 50;
    private final static Integer MULTIPLE_CHOICE_SUBJECT_NUMBER = 30;
    private final static Integer JUDGE_SUBJECT_NUMBER = 20;
    
    public TestServiceImpl(SubjectService subjectService, TestRepository testRepository, ErrorSubjectService errorSubjectService) {
        super(testRepository);
        this.subjectService = subjectService;
        this.testRepository = testRepository;
        this.errorSubjectService = errorSubjectService;
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
        if (id == null) {
            throw new Exception("根据用户ID获取TestList 用户ID为null");
        }
        return testRepository.findAllByUserId(id);
    }
    
    
    @Override
    public Test getAllSubjectTest() {
        List<? extends Subject> allSingleTypeOfSubject = null;
        List<? extends Subject> allMultipleTypeOfSubject = null;
        List<? extends Subject> allJudgeTypeOfSubject = null;
        try {
            allSingleTypeOfSubject = subjectService.getAll(SubjectType.SINGLE_CHOICE);
            allMultipleTypeOfSubject = subjectService.getAll(SubjectType.SINGLE_CHOICE);
            allJudgeTypeOfSubject = subjectService.getAll(SubjectType.SINGLE_CHOICE);
            
        } catch (Exception e) {
            log.error("生成全部题目时发生错误");
        }
        
        return getTest(allSingleTypeOfSubject, allMultipleTypeOfSubject, allJudgeTypeOfSubject);
    }
    
    @Override
    public Test getDefaultTest() {
        return generateTest(SINGLE_CHOICE_SUBJECT_NUMBER, MULTIPLE_CHOICE_SUBJECT_NUMBER, JUDGE_SUBJECT_NUMBER);
    }
    
    
    @Override
    public Test getSingleChoiceOnlyTest() {
        return generateTest(300, 0, 0);
    }
    
    @Override
    public Test getMultipleChoiceOnlyTest() {
        return generateTest(0, 180, 0);
    }
    
    @Override
    public Test getJudgeChoiceOnlyTest() {
        return generateTest(0, 0, 120);
    }
    
    @Override
    public Test getErrorSubjectTest() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        List<ErrorSubject> allByUsername = errorSubjectService.findAllByUsername(name);
        List<ErrorSubject> errorSingleTypeOfSubject = new ArrayList<>();
        List<ErrorSubject> errorMultipleTypeOfSubject = new ArrayList<>();
        List<ErrorSubject> errorJudgeTypeOfSubject = new ArrayList<>();
        for (ErrorSubject errorSubject : allByUsername) {
            if ("single".equals(errorSubject.getType())) {
                errorSingleTypeOfSubject.add(errorSubject);
            } else if ("multiple".equals(errorSubject.getType())) {
                errorMultipleTypeOfSubject.add(errorSubject);
            } else if ("judge".equals(errorSubject.getType())) {
                errorJudgeTypeOfSubject.add(errorSubject);
            }
        }
        return getTest(errorSingleTypeOfSubject, errorMultipleTypeOfSubject, errorJudgeTypeOfSubject);
    }
    
    private Test generateTest(Integer single, Integer multiple, Integer judge) {
        List<? extends Subject> randomSingleTypeOfSubject = null;
        List<? extends Subject> randomMultipleTypeOfSubject = null;
        List<? extends Subject> randomJudgeTypeOfSubject = null;
        try {
            randomSingleTypeOfSubject = subjectService.getRandomTypeOfSubject(SubjectType.SINGLE_CHOICE, single);
            randomMultipleTypeOfSubject = subjectService.getRandomTypeOfSubject(SubjectType.MULTIPLE_CHOICE, multiple);
            randomJudgeTypeOfSubject = subjectService.getRandomTypeOfSubject(SubjectType.JUDGE, judge);
            
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        
        return getTest(randomSingleTypeOfSubject, randomMultipleTypeOfSubject, randomJudgeTypeOfSubject);
    }
    
    private Test getTest(List<? extends Subject> randomSingleTypeOfSubject, List<? extends Subject> randomMultipleTypeOfSubject, List<? extends Subject> randomJudgeTypeOfSubject) {
        Test build = Test.builder()
                .singleChoiceSubjects(randomSingleTypeOfSubject)
                .multipleChoiceSubjects(randomMultipleTypeOfSubject)
                .judgeSubjects(randomJudgeTypeOfSubject)
                .user(UserUtils.getOnlineUser())
                .build();
        return testRepository.save(build);
    }
    
    
}
