package com.bigduu.acp.controller;

import com.bigduu.acp.entity.Test;
import com.bigduu.acp.service.TestService;
import org.springframework.web.bind.annotation.*;

/**
 * @author bigduu
 * @title: TestController
 * @projectName acp
 * @description: TODO
 * @date 2019/12/2121:58
 */
@RestController
@RequestMapping("/test")
public class TestController {
    
    private final TestService testService;
    
    public TestController(TestService testService) {
        this.testService = testService;
    }
    
    @PostMapping("/subject")
    public Test save(Test test){
        return testService.save(test);
    }
    
    @PatchMapping("/subject")
    public Test update(Test test){
        return testService.update(test);
    }
    
    @GetMapping("/subject/all")
    public Test getAllTest(){
        return testService.getAllSubjectTest();
    }
    @GetMapping("/subject/default")
    public Test getDefaultTest(){
        Test defaultTest = testService.getDefaultTest();
        return defaultTest;
    }
    @GetMapping("/subject/single")
    public Test getSingleChoiceOnlyTest(){
        return testService.getSingleChoiceOnlyTest();
    }
    @GetMapping("/subject/multiple")
    public Test getMultipleChoiceOnlyTest(){
        return testService.getMultipleChoiceOnlyTest();
    }
    @GetMapping("/subject/judge")
    public Test getJudgeChoiceOnlyTest(){
        return testService.getJudgeChoiceOnlyTest();
    }
}
