package ru.userpetproject.mapper;

import org.mapstruct.Mapper;
import ru.userpetproject.dto.email.EmailReq;
import ru.userpetproject.dto.email.EmailResp;
import ru.userpetproject.entity.Email;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmailMapper {

    Email toModel(EmailReq emailReq);

    EmailResp toResponse(Email email);

    List<Email> toModel(List<EmailReq> emailReqs);
}
