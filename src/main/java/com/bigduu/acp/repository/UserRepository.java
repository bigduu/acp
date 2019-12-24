package com.bigduu.acp.repository;

import com.bigduu.acp.common.repository.BaseRepository;
import com.bigduu.acp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends BaseRepository<User> {
}
