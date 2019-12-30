package com.bigduu.acp.repository.subject;

import com.bigduu.acp.common.CSRE.repository.BaseRepository;
import com.bigduu.acp.entity.subject.subsubject.JudgeSubject;

import java.util.Optional;

/**
 * @author mugeng.du
 */
public interface JudgeSubjectRepository extends BaseRepository<JudgeSubject> {
    Optional<JudgeSubject> findByQuestionLike(String question);
}
