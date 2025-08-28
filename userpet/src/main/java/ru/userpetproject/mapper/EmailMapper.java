package ru.userpetproject.mapper;

import org.mapstruct.Mapper;
import ru.userpetproject.dto.email.EmailReq;
import ru.userpetproject.dto.email.EmailResp;
import ru.userpetproject.entity.Email;

@Mapper(componentModel = "spring")
public interface EmailMapper {

    Email toModelEmail(EmailReq emailReq);

    EmailResp toResponse(Email email);
}
