package com.bigduu.acp.repository;

import com.bigduu.acp.entity.subject.SingleChoiceSubject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SingleChoiceSubjectRepository extends MongoRepository<SingleChoiceSubject,String>{
    Optional<SingleChoiceSubject> findByQuestionLike(String question);
}
