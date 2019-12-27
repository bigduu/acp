package com.bigduu.acp.controller;

import com.bigduu.acp.entity.subject.subsubject.ErrorSubject;
import com.bigduu.acp.service.ErrorSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mugeng.du
 */
@RestController
@RequestMapping("/errorSubject")
public class ErrorSubjectController {
    
    private final ErrorSubjectService errorSubjectService;
    
    public ErrorSubjectController(ErrorSubjectService errorSubjectService) {
        this.errorSubjectService = errorSubjectService;
    }
    
    @GetMapping
    public List<ErrorSubject> findByUsername(String username){
        return errorSubjectService.findAllByUsername(username);
    }
    
    @PostMapping
    public ErrorSubject save(@RequestBody ErrorSubject errorSubject){
        return errorSubjectService.addOne(errorSubject);
    }
    
    @PostMapping("/all")
    public List<ErrorSubject> saveAll(@RequestBody List<ErrorSubject> errorSubjects) {
        return errorSubjectService.addAll(errorSubjects);
    }
    
    
}
