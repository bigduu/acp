package com.bigduu.acp.repository;

import com.bigduu.acp.common.repository.BaseRepository;
import com.bigduu.acp.entity.subject.subSubject.SingleChoiceSubject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface SingleChoiceSubjectRepository extends BaseRepository<SingleChoiceSubject> {
    Optional<SingleChoiceSubject> findByQuestionLike(String question);
}
