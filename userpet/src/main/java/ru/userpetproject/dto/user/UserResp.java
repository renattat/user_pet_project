package ru.userpetproject.dto.user;

import lombok.Data;
import ru.userpetproject.dto.email.EmailResp;
import ru.userpetproject.dto.phone.PhoneResp;

import java.util.List;

@Data
public class UserResp {

    private Long id;
    private String name;
    private List<EmailResp> emails;
    private List<PhoneResp> phones;
}
