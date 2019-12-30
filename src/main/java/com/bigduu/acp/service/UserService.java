package com.bigduu.acp.service;


import com.bigduu.acp.common.CSRE.service.BaseService;
import com.bigduu.acp.entity.User;

public interface UserService extends BaseService<User> {
    User findByName(String username);
}
