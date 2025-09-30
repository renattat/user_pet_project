package ru.userpetproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.userpetproject.dto.user.UserReq;
import ru.userpetproject.dto.user.UserResp;
import ru.userpetproject.exception.UserValidationException;
import ru.userpetproject.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResp> get(@PathVariable long id) {
        return new ResponseEntity<>(userService.get(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserResp> create(@RequestBody @Valid UserReq userReq, BindingResult result) {
        if(result.hasErrors()) {
            throw new UserValidationException(result.getFieldError());
        }
        return  new ResponseEntity<>(userService.create(userReq), HttpStatus.CREATED);
    }
}
