package com.bigduu.acp.repository;

import com.bigduu.acp.common.repository.BaseRepository;
import com.bigduu.acp.entity.subject.subsubject.JudgeSubject;

import java.util.Optional;

public interface JudgeSubjectRepository extends BaseRepository<JudgeSubject> {
    Optional<JudgeSubject> findByQuestionLike(String question);
}
