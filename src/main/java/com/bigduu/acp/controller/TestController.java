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
    
    private static Test allTest = null;
    
    public TestController(TestService testService) {
        this.testService = testService;
    }
    
    @PostMapping("/subject")
    public Test save(Test test){
        return testService.addOne(test);
    }
    
    @PatchMapping("/subject")
    public Test update(Test test){
        return testService.update(test);
    }
    
    @GetMapping("/subject/all")
    public Test getAllTest(){
        if (allTest == null){
            allTest = testService.getAllSubjectTest();
        }
        return allTest;
    }
    @GetMapping("/subject/default")
    public Test getDefaultTest(){
        return testService.getDefaultTest();
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
