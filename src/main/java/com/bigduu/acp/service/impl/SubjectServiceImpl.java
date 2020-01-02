package com.bigduu.acp.service.impl;

import com.bigduu.acp.entity.subject.Subject;
import com.bigduu.acp.entity.subject.subsubject.SubjectType;
import com.bigduu.acp.repository.subject.JudgeSubjectRepository;
import com.bigduu.acp.repository.subject.MultipleChoiceSubjectRepository;
import com.bigduu.acp.repository.subject.SingleChoiceSubjectRepository;
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
    
    private static Map<String, List<? extends Subject>> SUBJECT_CACHE = new HashMap<>();
    private static Map<String, Long> COUNT_CACHE = new HashMap<>();
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
        switch (subjectType) {
            case SINGLE_CHOICE:
                return this.singleChoiceSubjectRepository;
            case MULTIPLE_CHOICE:
                return this.multipleChoiceSubjectRepository;
            case JUDGE:
                return judgeSubjectRepository;
            default:
                throw new Exception("没有该题型");
        }
    }
    
    private List<? extends Subject> filterSubject(List<? extends Subject> subjects, Integer number) {
        List<Integer> randomNumberSet = getRandomNumberSet(number);
        ArrayList<Subject> outList = new ArrayList<>();
        for (Integer integer : randomNumberSet) {
            outList.add(subjects.get(integer));
        }
        return outList;
    }
    
    private List<Integer> getRandomNumberSet(Integer targetSize) {
        ArrayList<Integer> integers = new ArrayList<>();
        while (integers.size() < targetSize) {
            Integer randomNumber = getRandomNumber(targetSize);
            if (integers.contains(randomNumber)){
                continue;
            }
            integers.add(randomNumber);
        }
        return integers;
    }
    
    private Integer getRandomNumber(int end) {
        return random.nextInt(end);
    }
    
    @Override
    public List<? extends Subject> getAll(SubjectType subjectType) throws Exception {
        return getSubjectTypeService(subjectType).findAll();
    }
    
    @Override
    public List<? extends Subject> getRandomTypeOfSubject(SubjectType subjectType, Integer number) throws Exception {
        MongoRepository<? extends Subject, String> subjectTypeService = getSubjectTypeService(subjectType);
        Long count = getCountCache(subjectType, subjectTypeService);
        if (number  > count) {
            log.error("生成随即题目，但是题目没有足够数量");
            throw new Exception("该题型没有足够的数量");
        } else {
            List<? extends Subject> all = getSubjectsCache(subjectType, subjectTypeService);
            return filterSubject(all, number);
        }
    }
    
    /**
     * 缓存所有题目
     * @param subjectType 缓存题目的类型
     * @param mongoRepository 缓存题目的repository
     * @return 获取题目
     */
    private List<? extends Subject> getSubjectsCache(SubjectType subjectType, MongoRepository<? extends Subject, String> mongoRepository) {
        if (SUBJECT_CACHE.get(subjectType.toString()) == null) {
            synchronized (this) {
                if (SUBJECT_CACHE.get(subjectType.toString()) == null) {
                    SUBJECT_CACHE.put(subjectType.toString(),mongoRepository.findAll());
                }
            }
        }
        return SUBJECT_CACHE.get(subjectType.toString());
    }
    
    /**
     * 缓存所有题目
     * @param subjectType 缓存题目的类型
     * @param mongoRepository 缓存题目的repository
     * @return 获取题目
     */
    private Long getCountCache(SubjectType subjectType, MongoRepository<? extends Subject, String> mongoRepository) {
        if (COUNT_CACHE.get(subjectType.toString()) == null) {
            synchronized (this) {
                if (COUNT_CACHE.get(subjectType.toString()) == null) {
                    COUNT_CACHE.put(subjectType.toString(),mongoRepository.count());
                }
            }
        }
        return COUNT_CACHE.get(subjectType.toString());
    }
}
