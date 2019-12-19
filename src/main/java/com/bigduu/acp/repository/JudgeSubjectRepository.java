package com.bigduu.acp.repository;

import com.bigduu.acp.entity.subject.JudgeSubject;
import com.bigduu.acp.entity.subject.MultipleChoiceSubject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JudgeSubjectRepository extends MongoRepository<JudgeSubject,String> {
    Optional<JudgeSubject> findByQuestionLike(String question);
}
