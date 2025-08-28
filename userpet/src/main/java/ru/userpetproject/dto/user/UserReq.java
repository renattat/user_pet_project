package ru.userpetproject.dto.user;

import lombok.Data;
import ru.userpetproject.dto.email.EmailReq;
import ru.userpetproject.dto.phone.PhoneReq;

import java.util.List;

@Data
public class UserReq {

    private String name;
    private List<EmailReq> emails;
    private List<PhoneReq> phones;
}
