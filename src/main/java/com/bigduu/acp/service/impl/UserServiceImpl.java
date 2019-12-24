package com.bigduu.acp.service.impl;

import com.bigduu.acp.common.repository.BaseRepository;
import com.bigduu.acp.common.service.impl.BaseServiceImpl;
import com.bigduu.acp.entity.User;
import com.bigduu.acp.repository.UserRepository;
import com.bigduu.acp.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    
    private final UserRepository userRepository;
    
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }
}
