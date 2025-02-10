package com.pragma.plazoleta.domain.utils;

import com.pragma.plazoleta.domain.model.UserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class UserUtils {

    private static final String PENDING = "PENDING";

    public static Long getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        return userDetail.getId();
    }

}
