package com.swiggy.user.controllers;

import com.swiggy.user.models.requestModels.UserRequestModel;
import com.swiggy.user.models.responseModels.UserResponseModel;
import com.swiggy.user.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    @Autowired
    private IUserService userService;

    @PostMapping("")
    public ResponseEntity<UserResponseModel> register(@RequestBody UserRequestModel userRequestModel) {
        return new ResponseEntity<>(userService.register(userRequestModel), HttpStatus.CREATED);
    }
}
