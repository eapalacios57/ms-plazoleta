package com.pragma.plazoleta.infraestructura.out.feign.mapper;

import com.pragma.plazoleta.domain.model.Order;
import com.pragma.plazoleta.domain.model.UserDetail;
import com.pragma.plazoleta.infraestructura.out.feign.model.MessageApiRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface MessageRequestMapper {

    @Mapping(source = "order.pin", target="pin")
    @Mapping(source = "userDetail.names", target="names")
    @Mapping(source = "userDetail.phone", target="phone")
    MessageApiRequest toMessageRequest(Order order, UserDetail userDetail);
}
