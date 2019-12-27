package com.bigduu.acp.service.impl;

import com.bigduu.acp.entity.subject.subsubject.ErrorSubject;
import com.bigduu.acp.repository.subject.ErrorSubjectRepository;
import com.bigduu.acp.service.ErrorSubjectService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mugeng.du
 */
@Service
public class ErrorSubjectServiceImpl implements ErrorSubjectService {
    
    private final ErrorSubjectRepository errorSubjectRepository;
    
    public ErrorSubjectServiceImpl(ErrorSubjectRepository errorSubjectRepository) {
        this.errorSubjectRepository = errorSubjectRepository;
    }
    
    
    @Override
    public ErrorSubject addOne(ErrorSubject errorSubject) {
        return errorSubjectRepository.save(errorSubject);
    }
    
    @Override
    public List<ErrorSubject> findAllByUsername(String username) {
        return errorSubjectRepository.findAllByUsername(username);
    }
    
    @Override
    public List<ErrorSubject> addAll(List<ErrorSubject> errorSubjects) {
        return errorSubjectRepository.saveAll(errorSubjects);
    }
}
