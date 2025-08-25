package ru.userpetproject.mapper;

import org.mapstruct.Mapper;
import ru.userpetproject.dto.UserReq;
import ru.userpetproject.dto.UserResp;
import ru.userpetproject.entity.User;

@Mapper(componentModel = "spring")
//        ,
//        uses = {MailMapper.class, PhoneMapper.class})
public interface UserMapper {

    UserResp toResponse(User user);

    User toModelUser(UserReq userReq);
}
