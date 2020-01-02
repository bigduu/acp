package com.bigduu.acp.service.impl;

import com.bigduu.acp.common.baseprocesshandler.exception.AlertException;
import com.bigduu.acp.common.baseprocesshandler.service.impl.BaseServiceImpl;
import com.bigduu.acp.entity.Test;
import com.bigduu.acp.entity.User;
import com.bigduu.acp.repository.UserRepository;
import com.bigduu.acp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    
    private final UserRepository userRepository;
    
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }
    
    
    @Override
    public User findByName(String username) {
        return userRepository.findByUsername(username);
    }
    
    @Override
    public void logTestHistory(User user, Test test) throws AlertException {
        if (user.getId() == null) {
            throw new AlertException("请登录");
        }
        User byName = userRepository.findByUsername(user.getUsername());
        List<Test> history = byName.getHistory();
        if (history == null) {
            history = new ArrayList<>();
        }
        history.add(test);
        user.setHistory(history);
        user.setLastTest(test);
        super.update(user);
    }
    
    @Override
    public User addOne(User instance) {
        instance.setActive(true);
        return super.addOne(instance);
    }
}
