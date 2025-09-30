package ru.userpetproject.mapper;

import org.mapstruct.Mapper;
import ru.userpetproject.dto.phone.PhoneReq;
import ru.userpetproject.dto.phone.PhoneResp;
import ru.userpetproject.entity.Phone;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhoneMapper {

    Phone toModel(PhoneReq phoneReq);

    PhoneResp toResponse(Phone phone);

    List<Phone> toModel(List<PhoneReq> phoneReqs);
}
