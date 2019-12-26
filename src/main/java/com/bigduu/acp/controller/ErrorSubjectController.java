package com.bigduu.acp.controller;

import com.bigduu.acp.entity.subject.subsubject.ErrorSubject;
import com.bigduu.acp.service.ErrorSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ErrorSubject save(ErrorSubject errorSubject){
        return errorSubjectService.addOne(errorSubject);
    }
    
    @PostMapping("/all")
    public List<ErrorSubject> saveAll(List<ErrorSubject> errorSubjects) {
        return errorSubjectService.addAll(errorSubjects);
    }
    
    
}
