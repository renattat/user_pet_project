package ru.userpetproject.service;

import ru.userpetproject.dto.UserReq;
import ru.userpetproject.dto.UserResp;

public interface UserService {

    UserResp get(long id);

    UserResp create(UserReq userReq);
}
