package ru.userpetproject.dto.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import lombok.Data;

@Data
public class EmailReq {

    @Max(100)
    @Email
    private String address;
}
