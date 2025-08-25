package ru.userpetproject.dto;

import lombok.*;
import ru.userpetproject.entity.Email;
import ru.userpetproject.entity.Phone;

import java.util.List;

@Data
public class UserResp {

    private Long id;
    private String name;
    private List<Email> emails;
    private List<Phone> phones;
}
