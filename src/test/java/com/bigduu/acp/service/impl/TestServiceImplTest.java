package com.bigduu.acp.service.impl;

import com.bigduu.acp.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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
    void getAllSubjectTest() {
        com.bigduu.acp.entity.Test allSubjectTest = testService.getAllSubjectTest();
        System.out.println(allSubjectTest);
    }
    
    @Test
    void getOneDefaultTest() {
        com.bigduu.acp.entity.Test oneDefaultTest = testService.getOneDefaultTest();
        System.out.println(oneDefaultTest);
    }
    
    @Test
    void getSingleChoiceOnlyTest() {
        com.bigduu.acp.entity.Test singleChoiceOnlyTest = testService.getSingleChoiceOnlyTest();
        System.out.println(singleChoiceOnlyTest);
    }
    
    @Test
    void getMultipleChoiceOnlyTest() {
        com.bigduu.acp.entity.Test multipleChoiceOnlyTest = testService.getMultipleChoiceOnlyTest();
        System.out.println(multipleChoiceOnlyTest);
    }
    
    @Test
    void getJudgeChoiceOnlyTest() {
        com.bigduu.acp.entity.Test judgeChoiceOnlyTest = testService.getJudgeChoiceOnlyTest();
        System.out.println(judgeChoiceOnlyTest);
    }
}