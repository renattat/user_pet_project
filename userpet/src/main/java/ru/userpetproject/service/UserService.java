package ru.userpetproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.userpetproject.entity.User;
import ru.userpetproject.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getUser(long id) {
        return userRepository.findById(id);
    }

    public User addNewUser(User user){
        return userRepository.save(user);
    }
}
