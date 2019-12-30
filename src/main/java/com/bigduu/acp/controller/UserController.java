package com.bigduu.acp.controller;

import com.bigduu.acp.common.CSRE.controller.BaseController;
import com.bigduu.acp.entity.User;
import com.bigduu.acp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author bigduu
 * @title: UserController
 * @projectName acp
 * @description: TODO
 * @date 2019/12/2422:19
 */
@RequestMapping("/user")
@RestController
public class UserController extends BaseController<User> {
    
    private final UserService userService;
    
    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }
    
    @PostMapping("/logon")
    public User logon(@RequestBody User user){
        return userService.addOne(user);
    }
    
    @GetMapping("/online")
    public String onlineUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
