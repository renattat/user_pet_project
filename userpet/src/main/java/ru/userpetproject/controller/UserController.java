package ru.userpetproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.userpetproject.dto.UserReq;
import ru.userpetproject.dto.UserResp;
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
    public ResponseEntity<UserResp> create(@RequestBody UserReq userReq) {
        return  new ResponseEntity<>(userService.create(userReq), HttpStatus.CREATED);
    }
}
