package ru.userpetproject.service;

import ru.userpetproject.dto.user.UserReq;
import ru.userpetproject.dto.user.UserResp;

public interface UserService {

    UserResp get(long id);

    UserResp create(UserReq userReq);

}
