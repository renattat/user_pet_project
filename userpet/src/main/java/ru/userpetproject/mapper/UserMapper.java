package ru.userpetproject.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import ru.userpetproject.dto.user.UserReq;
import ru.userpetproject.dto.user.UserResp;
import ru.userpetproject.entity.User;

@Mapper(componentModel = "spring", uses = {EmailMapper.class, PhoneMapper.class},
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface UserMapper {

    User toModelUser(UserReq userReq);

    UserResp toResponse(User user);
}
