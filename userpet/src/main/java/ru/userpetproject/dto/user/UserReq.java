package ru.userpetproject.dto.user;

import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.userpetproject.dto.email.EmailReq;
import ru.userpetproject.dto.phone.PhoneReq;

import java.util.List;

@Data
public class UserReq {

    @Size(max = 50, message = "Имя пользователя не должно быть больше 50")
    private String name;
    private List<EmailReq> emails;
    private List<PhoneReq> phones;
}

