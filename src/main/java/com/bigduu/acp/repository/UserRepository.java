package com.bigduu.acp.repository;

import com.bigduu.acp.common.baseprocesshandler.repository.BaseRepository;
import com.bigduu.acp.entity.User;

public interface UserRepository extends BaseRepository<User> {
    User findByUsername(String username);
}
