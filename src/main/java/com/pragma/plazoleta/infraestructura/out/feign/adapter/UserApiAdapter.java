package com.pragma.plazoleta.infraestructura.out.feign.adapter;

import com.pragma.plazoleta.domain.api.IApiUserServicePort;
import com.pragma.plazoleta.domain.model.UserDetail;
import com.pragma.plazoleta.infraestructura.exception.NotFoundException;
import com.pragma.plazoleta.infraestructura.out.feign.rest.UserFeignClient;
import com.pragma.plazoleta.infraestructura.out.feign.mapper.UserDetailMapper;
import lombok.AllArgsConstructor;



@AllArgsConstructor
public class UserApiAdapter implements IApiUserServicePort {

    private final UserFeignClient userFeignClient;
    private final UserDetailMapper userDetailMapper;


    @Override
    public void validateUser(Long userId) {
        UserDetail userDetail = userDetailMapper.toUserDetail(userFeignClient.getUserDetailById(userId));
        if (userDetail == null) {
            throw new NotFoundException("User Not Found.");
        }
    }

    @Override
    public UserDetail getUserDetail(Long userId) {
        return userDetailMapper.toUserDetail(userFeignClient.getUserDetailById(userId));
    }
}
