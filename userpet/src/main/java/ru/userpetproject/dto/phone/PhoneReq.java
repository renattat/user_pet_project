package ru.userpetproject.dto.phone;

import jakarta.validation.constraints.Max;
import lombok.Data;

@Data
public class PhoneReq {

    @Max(15)
    private String number;
    @Max(100)
    private String type;
}
