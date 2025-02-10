package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.UserDetail;

public interface IApiUserServicePort {
    void validateUser(Long userId);

    UserDetail getUserDetail(Long userId);

}
