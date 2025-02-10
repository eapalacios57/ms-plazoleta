package com.pragma.plazoleta.infraestructura.out.feign.mapper;

import com.pragma.plazoleta.domain.model.UserDetail;
import com.pragma.plazoleta.infraestructura.out.feign.model.UserDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserDetailMapper {

    UserDetail toUserDetail(UserDetailResponse userDetailResponse);
}
