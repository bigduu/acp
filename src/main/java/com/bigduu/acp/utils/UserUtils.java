package com.bigduu.acp.utils;

import com.bigduu.acp.common.security.userdetail.UserDetail;
import com.bigduu.acp.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author bigduu
 * @title: UserUtils
 * @projectName acp
 * @description: TODO
 * @date 2020/1/122:35
 */
public class UserUtils {
    public static User getOnlineUser(){
        UserDetail principal = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUser();
    }
}
