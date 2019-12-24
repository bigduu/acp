package com.bigduu.acp.controller;

import com.bigduu.acp.common.controller.BaseController;
import com.bigduu.acp.entity.User;
import com.bigduu.acp.service.UserService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bigduu
 * @title: UserController
 * @projectName acp
 * @description: TODO
 * @date 2019/12/2422:19
 */
@RestController("/user")
public class UserController extends BaseController<User> {
    
    private final UserService userService;
    
    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }
    
    
    
    
}
