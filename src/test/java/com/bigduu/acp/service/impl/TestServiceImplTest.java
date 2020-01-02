package com.bigduu.acp.service.impl;

import com.bigduu.acp.common.baseprocesshandler.exception.AlertException;
import com.bigduu.acp.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author bigduu
 * @title: TestServiceImplTest
 * @projectName acp
 * @description: TODO
 * @date 2019/12/2121:53
 */
@SpringBootTest
class TestServiceImplTest {
    
    @Autowired
    private TestService testService;
    
    @Test
    void getAllSubjectTest() throws AlertException {
        com.bigduu.acp.entity.Test allSubjectTest = testService.getAllSubjectTest();
        System.out.println(allSubjectTest);
    }
    
    @Test
    void getOneDefaultTest() throws AlertException {
        com.bigduu.acp.entity.Test oneDefaultTest = testService.getDefaultTest();
        System.out.println(oneDefaultTest);
    }
    
    @Test
    void getSingleChoiceOnlyTest() throws AlertException {
        com.bigduu.acp.entity.Test singleChoiceOnlyTest = testService.getSingleChoiceOnlyTest();
        System.out.println(singleChoiceOnlyTest);
    }
    
    @Test
    void getMultipleChoiceOnlyTest() throws AlertException {
        com.bigduu.acp.entity.Test multipleChoiceOnlyTest = testService.getMultipleChoiceOnlyTest();
        System.out.println(multipleChoiceOnlyTest);
    }
    
    @Test
    void getJudgeChoiceOnlyTest() throws AlertException {
        com.bigduu.acp.entity.Test judgeChoiceOnlyTest = testService.getJudgeChoiceOnlyTest();
        System.out.println(judgeChoiceOnlyTest);
    }
}