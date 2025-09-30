package ru.userpetproject.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.userpetproject.dto.user.UserReq;
import ru.userpetproject.dto.user.UserResp;
import ru.userpetproject.entity.User;
import ru.userpetproject.enums.UserStatus;
import ru.userpetproject.exception.UserNotFoundException;
import ru.userpetproject.mapper.UserMapper;
import ru.userpetproject.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final UserEventServiceImpl userEventServiceImpl;


    private final UserEventServiceImpl userEventService;

    public UserResp get(long id) {
        log.info("Get user by id");
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.toResponse(user);
    }

    @Transactional
    public UserResp create(UserReq userReq) {
        User user = userMapper.toModelUser(userReq);
        user.setStatus(UserStatus.CREATED);
        User savedUser = userRepository.save(user);
        userEventServiceImpl.saveUserEvent(savedUser, UserStatus.CREATED);
        return userMapper.toResponse(savedUser);
    }
}
