package com.bigduu.acp.common.security.service;

import com.bigduu.acp.common.security.userdetail.UserDetail;
import com.bigduu.acp.entity.User;
import com.bigduu.acp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * TODO 还需要一些其他的错误类型
 */
@Slf4j
@Service
public class MyUserDetailService implements UserDetailsService {
    
    private final UserService userService;
    
    public MyUserDetailService(UserService userService) {
        this.userService = userService;
    }
    
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByName(s);
        if (user == null){
            log.error("不存在该用户");
            throw new UsernameNotFoundException("不存在该用户");
        }
        return new UserDetail(user);
    }
}
