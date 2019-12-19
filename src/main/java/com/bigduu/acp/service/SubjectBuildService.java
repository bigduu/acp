package com.bigduu.acp.service;

import com.bigduu.acp.entity.subject.Subject;
import com.bigduu.acp.entity.subject.SubjectType;

public interface SubjectBuildService<T extends Subject> {
    T doBuild(SubjectType type);
}
