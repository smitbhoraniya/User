package com.swiggy.user.controllers;

import com.swiggy.user.enums.Role;
import com.swiggy.user.models.requestModels.UserRequestModel;
import com.swiggy.user.models.responseModels.UserResponseModel;
import com.swiggy.user.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/delivery-partners")
public class DeliveryPartnersController {
    @Autowired
    private IUserService userService;

    @PostMapping("")
    public ResponseEntity<UserResponseModel> register(@RequestBody UserRequestModel userRequestModel) {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.DELIVERY_PARTNER);
        return new ResponseEntity<>(userService.register(userRequestModel, roles), HttpStatus.CREATED);
    }
}