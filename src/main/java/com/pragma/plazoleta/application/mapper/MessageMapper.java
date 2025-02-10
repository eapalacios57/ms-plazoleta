package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.domain.model.Message;
import com.pragma.plazoleta.domain.model.Order;
import com.pragma.plazoleta.domain.model.UserDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {

    @Mapping(source = "order.pin", target="pin")
    @Mapping(source = "userDetail.names", target="names")
    @Mapping(source = "userDetail.phone", target="phone")
    Message toMessage(Order order, UserDetail userDetail);
}
