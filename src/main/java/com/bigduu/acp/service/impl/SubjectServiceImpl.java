package com.bigduu.acp.service.impl;

import com.bigduu.acp.entity.subject.Subject;
import com.bigduu.acp.entity.subject.subSubject.SubjectType;
import com.bigduu.acp.repository.JudgeSubjectRepository;
import com.bigduu.acp.repository.MultipleChoiceSubjectRepository;
import com.bigduu.acp.repository.SingleChoiceSubjectRepository;
import com.bigduu.acp.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author bigduu
 * @title: SubjectServiceImpl
 * @projectName acp
 * @description: TODO
 * @date 2019/12/2119:39
 */
@Service
@Slf4j
public class SubjectServiceImpl implements SubjectService {
    private final SingleChoiceSubjectRepository singleChoiceSubjectRepository;
    private final MultipleChoiceSubjectRepository multipleChoiceSubjectRepository;
    private final JudgeSubjectRepository judgeSubjectRepository;
    private static Random random = new Random();
    
    
    public SubjectServiceImpl(SingleChoiceSubjectRepository singleChoiceSubjectRepository, MultipleChoiceSubjectRepository multipleChoiceSubjectRepository, JudgeSubjectRepository judgeSubjectRepository) {
        this.singleChoiceSubjectRepository = singleChoiceSubjectRepository;
        this.multipleChoiceSubjectRepository = multipleChoiceSubjectRepository;
        this.judgeSubjectRepository = judgeSubjectRepository;
    }
    
    private MongoRepository<? extends Subject, String> getSubjectTypeService(SubjectType subjectType) throws Exception {
        switch (subjectType){
            case SINGLE_CHOICE: return this.singleChoiceSubjectRepository;
            case MULTIPLE_CHOICE: return this.multipleChoiceSubjectRepository;
            case JUDGE: return judgeSubjectRepository;
        }
        throw new Exception("没有该题型");
    }
    
    private List<? extends Subject> filterSubject(List<? extends Subject> subjects,Integer number){
        Set<Integer> randomNumberSet = getRandomNumberSet(number);
        ArrayList<Subject> outList = new ArrayList<>();
        for (Integer integer : randomNumberSet) {
            outList.add(subjects.get(integer));
        }
        return outList;
    }
    
    private Set<Integer> getRandomNumberSet(Integer targetSize){
        HashSet<Integer> integers = new HashSet<>();
        while (integers.size() != targetSize){
            Integer randomNumber = getRandomNumber(targetSize);
            integers.add(randomNumber);
        }
        return integers;
    }
    
    private Integer getRandomNumber(int end){
        return random.nextInt(end +1);
    
    }
    
    @Override
    public List<? extends Subject> getAll(SubjectType subjectType) throws Exception {
        return getSubjectTypeService(subjectType).findAll();
    }
    
    @Override
    public List<? extends Subject> getRandomTypeOfSubject(SubjectType subjectType, Integer number) throws Exception {
        MongoRepository<? extends Subject, String> subjectTypeService = getSubjectTypeService(subjectType);
        long count = subjectTypeService.count();
        if (number > count){
            log.error("生成随即题目，但是题目没有足够数量");
            throw new Exception("该题型没有足够的数量");
        }else{
            List<? extends Subject> all = subjectTypeService.findAll();
            return filterSubject(all, number);
        }
    }
}
