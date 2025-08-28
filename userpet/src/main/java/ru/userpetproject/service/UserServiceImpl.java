package ru.userpetproject.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.userpetproject.dto.user.UserReq;
import ru.userpetproject.dto.user.UserResp;
import ru.userpetproject.entity.User;
import ru.userpetproject.exception_handling.UserNotFoundException;
import ru.userpetproject.mapper.UserMapper;
import ru.userpetproject.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    public UserResp get(long id) {
        log.info("Get user by id");
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Пользователь с id " + id + " не найден"));
        return userMapper.toResponse(user);
    }

    public UserResp create(UserReq userReq) {
        User user = userMapper.toModelUser(userReq);
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }
}
