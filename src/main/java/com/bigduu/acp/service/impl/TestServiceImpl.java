package com.bigduu.acp.service.impl;

import com.bigduu.acp.common.baseprocesshandler.exception.AlertException;
import com.bigduu.acp.common.baseprocesshandler.service.impl.BaseServiceImpl;
import com.bigduu.acp.entity.Test;
import com.bigduu.acp.entity.User;
import com.bigduu.acp.entity.subject.*;
import com.bigduu.acp.entity.subject.subsubject.*;
import com.bigduu.acp.repository.TestRepository;
import com.bigduu.acp.service.ErrorSubjectService;
import com.bigduu.acp.service.SubjectService;
import com.bigduu.acp.service.TestService;
import com.bigduu.acp.service.UserService;
import com.bigduu.acp.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UserService userService;
    
    private final static Integer SINGLE_CHOICE_SUBJECT_NUMBER = 50;
    private final static Integer MULTIPLE_CHOICE_SUBJECT_NUMBER = 30;
    private final static Integer JUDGE_SUBJECT_NUMBER = 20;
    
    public TestServiceImpl(SubjectService subjectService, TestRepository testRepository, ErrorSubjectService errorSubjectService, UserService userService) {
        super(testRepository);
        this.subjectService = subjectService;
        this.testRepository = testRepository;
        this.errorSubjectService = errorSubjectService;
        this.userService = userService;
    }
    
    
    @Override
    public void delete(Test test) {
        testRepository.delete(test);
    }
    
    @Override
    public Test update(Test test) {
        return super.update(test);
    }
    
    @Override
    public List<Test> getAllByUser(User user) throws Exception {
        String username = user.getUsername();
        if (username.isEmpty()) {
            throw new Exception("根据用户ID获取TestList 用户ID为null");
        }
        return testRepository.findAllByUsername(username);
    }
    
    
    @Override
    public Test getAllSubjectTest() throws AlertException {
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
    public Test getDefaultTest() throws AlertException {
        return generateTest(SINGLE_CHOICE_SUBJECT_NUMBER, MULTIPLE_CHOICE_SUBJECT_NUMBER, JUDGE_SUBJECT_NUMBER);
    }
    
    
    @Override
    public Test getSingleChoiceOnlyTest() throws AlertException {
        return generateTest(300, 0, 0);
    }
    
    @Override
    public Test getMultipleChoiceOnlyTest() throws AlertException {
        return generateTest(0, 180, 0);
    }
    
    @Override
    public Test getJudgeChoiceOnlyTest() throws AlertException {
        return generateTest(0, 0, 120);
    }
    
    @Override
    public Test getErrorSubjectTest() throws AlertException {
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
    
    private Test generateTest(Integer single, Integer multiple, Integer judge) throws AlertException {
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
    
    
    
    private Test getTest(List<? extends Subject> randomSingleTypeOfSubject, List<? extends Subject> randomMultipleTypeOfSubject, List<? extends Subject> randomJudgeTypeOfSubject) throws AlertException {
        User onlineUser = UserUtils.getOnlineUser();
        Test build = Test.builder()
                .singleChoiceSubjects(randomSingleTypeOfSubject)
                .multipleChoiceSubjects(randomMultipleTypeOfSubject)
                .judgeSubjects(randomJudgeTypeOfSubject)
                .username(onlineUser.getUsername())
                .build();
        Test save = testRepository.save(build);
        userService.logTestHistory(onlineUser, save);
        return save;
    }
    
}
