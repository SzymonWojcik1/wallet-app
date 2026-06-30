package com.szymonwojcik.wallet_app.controller;

import com.szymonwojcik.wallet_app.dto.request.CreateUserRequest;
import com.szymonwojcik.wallet_app.dto.response.UserResponse;
import com.szymonwojcik.wallet_app.mapper.UserMapper;
import com.szymonwojcik.wallet_app.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse create(@RequestBody CreateUserRequest request){
        return UserMapper.toResponse(userService.create(request));
    }

    @GetMapping
    public List<UserResponse> getAll(){
        return userService.getAll()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id){
        return UserMapper.toResponse(userService.getById(id));
    }
}
