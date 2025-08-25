package ru.userpetproject.dto;

import lombok.Data;
import ru.userpetproject.entity.Email;
import ru.userpetproject.entity.Phone;

import java.util.List;

@Data
public class UserReq {

    private String name;
    private List<Email> emails;
    private List<Phone> phones;
}
