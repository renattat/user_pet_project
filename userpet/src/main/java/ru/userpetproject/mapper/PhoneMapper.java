package ru.userpetproject.mapper;

import org.mapstruct.Mapper;
import ru.userpetproject.dto.phone.PhoneReq;
import ru.userpetproject.dto.phone.PhoneResp;
import ru.userpetproject.entity.Phone;

@Mapper(componentModel = "spring")
public interface PhoneMapper {

    Phone toModelPhone(PhoneReq phoneReq);

    PhoneResp toResponse(Phone phone);
}
